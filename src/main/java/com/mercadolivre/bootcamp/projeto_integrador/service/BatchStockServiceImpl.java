package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;
import com.mercadolivre.bootcamp.projeto_integrador.exception.product.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BatchStockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
@Service
public class BatchStockServiceImpl implements BatchStockService {

    private final BatchStockRepository batchStockRepository;
    private static final int MINIMUM_DAYS_UNTIL_PRODUCT_IS_DUE = 21;

    @Override
    public BatchStock create(BatchStock batchStock) {
        // Todo: Verify if Product exists in Entity Product
        return batchStockRepository.save(batchStock);
    }

    @Override
    public List<BatchStock> findAll() {
        List<BatchStock> batchStockList = batchStockRepository.findAll();
        if (batchStockList.isEmpty()) throw new EmptyListException();
        return batchStockRepository.findAll();
    }

    @Override
    public BatchStock findById(Long id) {
        return batchStockRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    @Override
    public List<BatchStock> findAllByProductId(Long id) {
        List<BatchStock> batchStockList = batchStockRepository.findAllByProduct_Id(id);
        if(batchStockList.isEmpty()) throw new InvalidProductException(id);
        return batchStockList;
    }

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

    @Override
    public void remove(Long id) {
        BatchStock batchStock = findById(id);
        batchStockRepository.delete(batchStock);
    }

    @Override
    public BigDecimal calculateTotalVolume(BatchStock batchStock) {
        Integer productQuantity = batchStock.getCurrentQuantity();
        BigDecimal volumePerProduct = batchStock.getProduct().getVolume();
        // Multiply currentQuantity per volumePerProduct to calculate batch total volume
        return volumePerProduct.multiply(BigDecimal.valueOf(productQuantity));
    }

    public List<BatchStock> orderBatchStockList(List<BatchStock> batchStockList) throws EmptyListException {
        if (batchStockList == null || batchStockList.isEmpty()) throw new EmptyListException();
        // Sorts BatchStockList by dueDate
        batchStockList.sort(Comparator.comparing(BatchStock::getDueDate));
        return batchStockList;
    }

    @Override
    public List<BatchStock> findAllBySectionIdAndDueDate(int daysFromToday, long sectionId) {
        LocalDate limitDueDate = LocalDate.now().plusDays(daysFromToday);
        List<BatchStock> filteredBatchStockList = batchStockRepository.findByDueDateIsLessThanEqualAndSection_SectionId(limitDueDate, sectionId);
        filteredBatchStockList = this.orderBatchStockList(filteredBatchStockList);
        return filteredBatchStockList;
    }

    @Override
    public List<BatchStock> findAllByDueDateAndProductCategory(int daysFromToday, Category category) {
        LocalDate limitDueDate = LocalDate.now().plusDays(daysFromToday);
        List<BatchStock> filteredBatchStockList = batchStockRepository.findByDueDateLessThanEqualAndProduct_Category(limitDueDate, category);
        filteredBatchStockList = this.orderBatchStockList(filteredBatchStockList);
        return filteredBatchStockList;
    }

    @Override
    public List<BatchStock> findAllByDueDate(LocalDate dueDate) {
        return batchStockRepository.findAllByDueDate(dueDate);
    }

    @Override
    public List<BatchStock> findaAllProductIdAndDueDate(Long productId, LocalDate dueDate) {
        return batchStockRepository.findAllByProduct_IdAndAndDueDate(productId, dueDate);
    }

    @Override
    public BatchStock selectBatchStock(PurchaseOrderItems purchaseOrderItems) {

        Long productId = purchaseOrderItems.getProductId();
        Integer requestedQuantity = purchaseOrderItems.getQuantity();
        LocalDate maxDueDate = LocalDate.now().plusDays(MINIMUM_DAYS_UNTIL_PRODUCT_IS_DUE);

        BatchStock foundBatchStock = batchStockRepository
                .findByCurrentQuantityIsGreaterThanEqualAndProduct_IdAndDueDateIsGreaterThanEqual(requestedQuantity, productId , maxDueDate);
        if (foundBatchStock == null) throw new EmptyListException(); // TODO: 03/05/22 REPLACE WITH PROPER EXCEPTION

        return foundBatchStock;
    }

    @Override
    public Boolean hasEnoughStockAvailable(Long productId, int requestedQuantity, List<BatchStock> filteredProductList){

        Integer totalQuantityBatchStock = filteredProductList.stream().map(BatchStock::getCurrentQuantity).reduce(0, Integer::sum);
        return totalQuantityBatchStock >= requestedQuantity;

    }

    @Override
    public List<BatchStock> isProductWithValidatedDueDateAndQuantity(Long productId, Integer requestedQuantity) {

        List<BatchStock> filteredProduct = batchStockRepository.findByDueDateIsGreaterThanEqual(LocalDate.now().plusDays(21));

        if(hasEnoughStockAvailable(productId, requestedQuantity, filteredProduct)) return filteredProduct;
        throw new EmptyListException();
    }
}
