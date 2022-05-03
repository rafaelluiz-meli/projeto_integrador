package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.exception.batch_stock.orderByNotValidException;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.exception.product.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BatchStockRepository;
import lombok.AllArgsConstructor;
import org.hibernate.engine.jdbc.batch.spi.Batch;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static com.fasterxml.jackson.databind.type.LogicalType.Map;
import static java.util.stream.Collectors.groupingBy;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


@AllArgsConstructor
@Service
public class BatchStockServiceImpl implements BatchStockService {

    private final BatchStockRepository batchStockRepository;

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

    @Override
    public List<BatchStock> orderBatchStockList(String orderBy, List<BatchStock> beforeOrderingList) {
        if(orderBy.equals("L")) return beforeOrderingList.stream()
                .sorted(Comparator.comparing(BatchStock::getBatchNumber)).collect(Collectors.toList());

        if(orderBy.equals("C")) return beforeOrderingList.stream()
                .sorted(Comparator.comparing(BatchStock::getCurrentQuantity)).collect(Collectors.toList());

        if(orderBy.equals("F")) return beforeOrderingList.stream()
                .sorted(Comparator.comparing(BatchStock::getDueDate)).collect(Collectors.toList());

        else throw new orderByNotValidException(orderBy);
    }

    @Override
    public List groupByWarehouse(Long productId) {
        return batchStockRepository.findProductInAllWarehouse(productId);
    }

    @Override
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

    public Boolean availableStockQuantity(Long productId, int requestedQuantity) {

        List<BatchStock> productBatchStock = findAllByProductId(productId);

        Integer totalQuantityBatchStock = productBatchStock.stream().map(BatchStock::getCurrentQuantity).reduce(0, Integer::sum);

        return totalQuantityBatchStock >= requestedQuantity;
    }

    public Boolean availableStockQuantity(Long productId, int requestedQuantity, List<BatchStock> filtredProductList){

        Integer totalQuantityBatchStock = filtredProductList.stream().map(BatchStock::getCurrentQuantity).reduce(0, Integer::sum);
        return totalQuantityBatchStock >= requestedQuantity;
    }

    public  Boolean isProductWithValidatedDueDateAndQuantity(Long productId, int requestedQuantity) {

        List<BatchStock> filteredProduct = batchStockRepository.findByDueDateIsGreaterThanEqual(LocalDate.now().plusDays(21));

        if(availableStockQuantity(productId, requestedQuantity, filteredProduct)) return true;
        throw new EmptyListException();
    }
}
