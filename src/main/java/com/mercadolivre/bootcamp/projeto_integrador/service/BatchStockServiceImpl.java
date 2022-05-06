package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.warehouse.WarehouseDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.exception.batch_stock.orderByNotValidException;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;
import com.mercadolivre.bootcamp.projeto_integrador.exception.product.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BatchStockRepository;
import lombok.AllArgsConstructor;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static java.util.stream.Collectors.groupingBy;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 * This class is the service implementation of batchstock entity.
 */

@AllArgsConstructor
@Service
public class BatchStockServiceImpl implements BatchStockService {

    private final BatchStockRepository batchStockRepository;

    /**
     * Is persistence in database by repository
     * @param batchStock Batchstock object
     * @return a BatchStock object
     */
    @Override
    public BatchStock create(BatchStock batchStock) {
        return batchStockRepository.save(batchStock);
    }

    /**
     * Get from database all Batchstocks.
     * @return a BatchStock list.
     * @exception EmptyListException if Batchstock list is empty.
     */
    @Override
    public List<BatchStock> findAll() {
        List<BatchStock> batchStockList = batchStockRepository.findAll();
        if (batchStockList.isEmpty()) throw new EmptyListException();
        return batchStockRepository.findAll();
    }

    /**
     * Find a Batchstock by Id.
     * @param id Batchstock id
     * @return a Batchstock.
     * @exception IdNotFoundException if id doesn't found.
     */
    @Override
    public BatchStock findById(Long id) {
        return batchStockRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    /**
     * Check if Batchstock id exists with {@link #findById(Long) findById} method. <br>
     * If exists then update Batchstock attributes.
     * @param batchStock Batchstock object.
     * @return a updated Batchstock
     *
     */
    @Override
    public BatchStock update(BatchStock batchStock) {
        BatchStock updatedBatchStock = findById(batchStock.getBatchNumber());
        updatedBatchStock.setCurrentQuantity(batchStock.getCurrentQuantity());
        updatedBatchStock.setPrice(batchStock.getPrice());
        updatedBatchStock.setDueDate(batchStock.getDueDate());
        updatedBatchStock.setManufacturingDate(batchStock.getManufacturingDate());
        updatedBatchStock.setManufacturingTime(batchStock.getManufacturingTime());

        return batchStockRepository.save(batchStock);
    }

    /**
     * Find all Batchstocks by product id.
     * @param id Product id
     * @return a Batchstock list.
     * @exception InvalidProductException if product id is invalid.
     */
    @Override
    public List<BatchStock> findAllByProductId(Long id) {
        List<BatchStock> batchStockList = batchStockRepository.findAllByProduct_Id(id);
        if (batchStockList.isEmpty()) throw new InvalidProductException(id);
        return batchStockList;
    }

    /**
     * Check if Batchstock id exists with {@link #findById(Long) findById} method. <br>
     * If exists then remove the Batchstock.
     * @param id Batchstock id
     * @return void.
     *
     */
    @Override
    public void remove(Long id) {
        BatchStock batchStock = findById(id);
        batchStockRepository.delete(batchStock);
    }

    /**
     * Calculate total volume from a Batchsotck. <br>
     * Multiply currentQuantity per volumePerProduct to calculate batch total volume
     * @param batchStock  Batchstock object
     * @return a Bigdecimal value that represents the total volume calculated.
     */
    @Override
    public BigDecimal calculateTotalVolume(BatchStock batchStock) {
        Integer productQuantity = batchStock.getCurrentQuantity();
        BigDecimal volumePerProduct = batchStock.getProduct().getVolume();
        return volumePerProduct.multiply(BigDecimal.valueOf(productQuantity));
    }

    /**
     * Order the Batchsotck list by three ways: <br>
     * F - due date <br>
     * C - current quantity <br>
     * L - batchstock number <br>
     * The 'L' way is default
     * @param orderBy Sorter String
     * @param beforeOrderingList Batchstock list not sorted
     * @return the ordinated list
     */
    @Override
    public List<BatchStock> orderBatchStockList(String orderBy, List<BatchStock> beforeOrderingList) {
        if (orderBy.equals("L")) return beforeOrderingList.stream()
                .sorted(Comparator.comparing(BatchStock::getBatchNumber)).collect(Collectors.toList());

        if (orderBy.equals("C")) return beforeOrderingList.stream()
                .sorted(Comparator.comparing(BatchStock::getCurrentQuantity)).collect(Collectors.toList());

        if (orderBy.equals("F")) return beforeOrderingList.stream()
                .sorted(Comparator.comparing(BatchStock::getDueDate)).collect(Collectors.toList());

        else throw new orderByNotValidException(orderBy);
    }

    /**
     * Find the Warehouses that have the product id and sum the total quantity of products. <br>
     * @param productId Product id
     * @return a list of Warehouses
     */
    @Override
    public List<WarehouseDTO> groupByWarehouse(Long productId) {
        return batchStockRepository.findProductInAllWarehouse(productId);
    }


    /**
     * ARRUMAR
     * Get a Batchstock list filtered by sectionId and due date  daysFromToday
     * @param daysFromToday Quantity of days to sum
     * @param sectionId Id of the section
     * @return a Batchstock list
     */
    @Override
    public List<BatchStock> findAllBySectionIdAndDueDate(int daysFromToday, long sectionId) {
        LocalDate limitDueDate = LocalDate.now().plusDays(daysFromToday);
        List<BatchStock> filteredBatchStockList = batchStockRepository.findByDueDateIsLessThanEqualAndSection_SectionId(limitDueDate, sectionId);
        filteredBatchStockList = this.orderBatchStockList(filteredBatchStockList);
        return filteredBatchStockList;
    }

    /**
     * Get a Batchstock list filtered by due date and product category. <br>
     * @param daysFromToday Quantity of days to sum
     * @param category Category object
     * @return a Batchstock list.
     */
    @Override
    public List<BatchStock> findAllByDueDateAndProductCategory(int daysFromToday, Category category) {
        LocalDate limitDueDate = LocalDate.now().plusDays(daysFromToday);
        List<BatchStock> filteredBatchStockList = batchStockRepository.findByDueDateLessThanEqualAndProduct_Category(limitDueDate, category);
        filteredBatchStockList = this.orderBatchStockList(filteredBatchStockList);
        return filteredBatchStockList;
    }

    /**
     * Get a Section list filtered by product id. <br>
     * If some duplications are found, this method remove them. <br>
     * @param productId Product id
     * @return a Section list.
     */
    @Override
    public List<Section> findSectionListByProductId(Long productId) {
        List<BatchStock> batchStockList = this.findAllByProductId(productId);
        List<Section> sectionList = batchStockList.stream().map(batchStock -> batchStock.getSection()).collect(Collectors.toList());
        List<Section> removeDuplicate = new ArrayList<>(new LinkedHashSet<>(sectionList));
        return removeDuplicate;
    }

    /**
     * Get a Batchstock list filtered by product id and section id. <br>
     * @param productId Product id
     * @param sectionId Section id
     * @return a batchstock list.
     */
    @Override
    public List<BatchStock> findBatchStockListByProductIdAndSectionId(Long productId, Long sectionId) {
        return batchStockRepository.findBySection_SectionIdAndProduct_Id(sectionId, productId);
    }

    /**
     * Check if there are available stock.
     * @param productId Product id
     * @param requestedQuantity int Requested quantity
     * @param filteredProductList Batchstock list filtered
     * @return true if the quantity is enough or false if does not.
     */
    @Override
    public Boolean hasEnoughStockAvailable(Long productId, int requestedQuantity, List<BatchStock> filteredProductList) {

        Integer totalQuantityBatchStock = filteredProductList.stream().map(BatchStock::getCurrentQuantity).reduce(0, Integer::sum);
        return totalQuantityBatchStock >= requestedQuantity;
    }

    /**
     * Receive a purchaseOrderItem and get a Batchstock of the
     * product that has requested quantity and due date is longer then 21 days.
     * @param purchaseOrderItems PurchaseOrderitems object
     * @return a unique Batchstock
     * @exception  EmptyListException if no Batchstock is found.
     */
    @Override
    public BatchStock selectBatchStock(PurchaseOrderItems purchaseOrderItems) {
        Long productId = purchaseOrderItems.getProductId();
        Integer requestedQuantity = purchaseOrderItems.getQuantity();
        LocalDate maxDueDate = LocalDate.now().plusDays(21);

        BatchStock foundBatchStock = batchStockRepository
                .findByCurrentQuantityIsGreaterThanEqualAndProduct_IdAndDueDateIsGreaterThanEqual(requestedQuantity, productId, maxDueDate);
        if (foundBatchStock == null) throw new EmptyListException();

        return foundBatchStock;
    }

    /**
     * Get a Batchstock list filtered by due date that's longer then 21 days and requested quantity
     * @param productId Product id
     * @param requestedQuantity Requested quantity
     * @return a Batchstock list
     * @exception  EmptyListException if no Batchstock is found.
     */
    @Override
    public List<BatchStock> isProductWithValidatedDueDateAndQuantity(Long productId, Integer requestedQuantity) {

        List<BatchStock> filteredProduct = batchStockRepository.findByDueDateIsGreaterThanEqual(LocalDate.now().plusDays(21));

        if (hasEnoughStockAvailable(productId, requestedQuantity, filteredProduct)) return filteredProduct;
        throw new EmptyListException();
    }

    /**
     * Sorts the BatchStockList by dueDate
     * @param batchStockList List of Batchstocks
     * @return the sorted Batchstock list
     * @exception EmptyListException if the BatchstockList is empty
     */
    @Override
    public List<BatchStock> orderBatchStockList(List<BatchStock> batchStockList) {
        if (batchStockList == null || batchStockList.isEmpty()) throw new EmptyListException();
        batchStockList.sort(Comparator.comparing(BatchStock::getDueDate));
        return batchStockList;
    }
}
