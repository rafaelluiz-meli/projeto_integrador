package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.exception.product.InvalidProductException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.BatchStockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class BatchStockServiceImpl implements BatchStockService {

    private final BatchStockRepository repository;

    @Override
    public BatchStock create(BatchStock batchStock) {
        // Todo: Verify if Product exists in Entity Product
        return repository.save(batchStock);
    }

    @Override
    public List<BatchStock> findAll() {
        List<BatchStock> batchStockList = repository.findAll();
        if (batchStockList.isEmpty()) throw new EmptyListException();
        return repository.findAll();
    }

    @Override
    public BatchStock findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    @Override
    public List<BatchStock> findAllByProductId(Long id) {
        List<BatchStock> batchStockList = repository.findAllByProduct_Id(id);
        if (batchStockList.isEmpty()) throw new InvalidProductException(id);
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
        return repository.save(batchStock);
    }

    @Override
    public void remove(Long id) {
        BatchStock batchStock = findById(id);
        repository.delete(batchStock);
    }

    @Override
    public BigDecimal calculateTotalVolume(BatchStock batchStock) {
        Integer productQuantity = batchStock.getCurrentQuantity();
        BigDecimal volumePerProduct = batchStock.getProduct().getVolume();
        // Multiply currentQuantity per volumePerProduct to calculate batch total volume
        return volumePerProduct.multiply(BigDecimal.valueOf(productQuantity));
    }

    @Override
    public List<BatchStock> findAllByDueDate(LocalDate dueDate) {
        return repository.findAllByDueDate(dueDate);
    }

    @Override
    public List<BatchStock> findaAllProductIdAndDueDate(Long productId, LocalDate dueDate) {
        return repository.findAllByProduct_IdAndAndDueDate(productId, dueDate);
    }

    public Boolean availableStockQuantity(Long productId, int requestedQuantity) {

        List<BatchStock> productBatchstock = findAllByProductId(productId);

        Integer totalQuantityBatchsotck = productBatchstock.stream().map(BatchStock::getCurrentQuantity).reduce(0, Integer::sum);

        return totalQuantityBatchsotck >= requestedQuantity;
    }

    public Boolean availableStockQuantity(Long productId, int requestedQuantity, List<BatchStock> filtredProductList){

        Integer totalQuantityBatchsotck = filtredProductList.stream().map(BatchStock::getCurrentQuantity).reduce(0, Integer::sum);
        return totalQuantityBatchsotck >= requestedQuantity;

    }

    public  Boolean returnListProductWithValidatedDueDateAndQuantity(Long productId, int requestedQuantity) {

        List<BatchStock> filtredProduct = repository.findByDueDateIsGreaterThanEqual(LocalDate.now().plusDays(21));

        if(availableStockQuantity(productId, requestedQuantity, filtredProduct)){
            return true;
        }
        throw new EmptyListException();
    }
}
