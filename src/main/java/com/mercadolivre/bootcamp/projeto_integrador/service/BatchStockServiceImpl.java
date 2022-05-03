package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.exception.batch_stock.orderByNotValidException;
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

@AllArgsConstructor
@Service
public class BatchStockServiceImpl implements BatchStockService{

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
        List<BatchStock> batchStockList = repository.findByProduct_Id(id);
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
        return repository.save(updatedBatchStock);
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
    public List<BatchStock> orderBatchStockList(String orderBy, List<BatchStock> beforeOrderingList) {
        if(orderBy.equals("L")) return beforeOrderingList.stream()
                .sorted(Comparator.comparing(BatchStock::getBatchNumber)).collect(Collectors.toList());

        if(orderBy.equals("C")) return beforeOrderingList.stream()
                .sorted(Comparator.comparing(BatchStock::getCurrentQuantity)).collect(Collectors.toList());

        if(orderBy.equals("F")) return beforeOrderingList.stream()
                .sorted(Comparator.comparing(BatchStock::getDueDate)).collect(Collectors.toList());

        else throw new orderByNotValidException(orderBy);
    };

    @Override
    public List<BatchStock> groupByWarehouse(List<BatchStock> batchStockListList) {
        Map<BatchStock::getSection::getWarehouse.getId , List<Integer>> totalQuatityByWarehouse =
        batchStockListList.stream()
                .collect(
                  Collectors.groupingBy(
                      BatchStock::getSection::getWarehouse.getId,
                      Collectors.reducing(0, BatchStock::getCurrentQuantity, Integer::sum)
                      )
                );
        return null;
    }
}
