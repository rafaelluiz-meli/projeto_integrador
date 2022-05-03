package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BatchStockService {
    BatchStock create(BatchStock batchStock);
    List<BatchStock> findAll();
    BatchStock findById(Long id);
    List<BatchStock> findAllByProductId(Long productId);
    BatchStock update(BatchStock batchStock);
    void remove(Long id);
    BigDecimal calculateTotalVolume(BatchStock batchStock);
    List<BatchStock> orderBatchStockList(String orderBy, List<BatchStock> beforeOrderingList);
    List<BatchStock> groupByWarehouse(List<BatchStock> batchStockListList);
    List<BatchStock> orderBatchStockList(List<BatchStock> unorderedList);
    List<BatchStock> findAllBySectionIdAndDueDate(int daysFromToday, long sectionId);
    List<BatchStock> findAllByDueDateAndProductCategory(int daysFromToday, Category fresh);
    List<BatchStock> findAllByDueDate(LocalDate dueDate);
    List<BatchStock> findaAllProductIdAndDueDate(Long productId, LocalDate dueDate);
}
