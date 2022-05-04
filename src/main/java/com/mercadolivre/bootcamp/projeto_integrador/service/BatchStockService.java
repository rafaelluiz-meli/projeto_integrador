package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BatchStockService {
    BatchStock create(BatchStock batchStock);
    List<BatchStock> findAll();
    BatchStock findById(Long id);
    List<BatchStock> findAllByProductId(Long productId);
    BatchStock update(BatchStock batchStock);
    void remove(Long id);
    BigDecimal calculateTotalVolume(BatchStock batchStock);
    List<BatchStock> orderBatchStockList(String orderBy, List<BatchStock> beforeOrderingList);
    List groupByWarehouse(Long productId);
    List<BatchStock> isProductWithValidatedDueDateAndQuantity(Long productId, Integer requestedQuantity);
    List<BatchStock> orderBatchStockList(List<BatchStock> unorderedList);
    List<BatchStock> findAllBySectionIdAndDueDate(int daysFromToday, long sectionId);
    List<BatchStock> findAllByDueDateAndProductCategory(int daysFromToday, Category fresh);
    List<Section> findSectionListByProductId(Long productId);
    List<BatchStock> findBatchStockListByProductIdAndSectionId(Long productId, Long sectionId);

    Boolean hasEnoughStockAvailable(Long productId, int requestedQuantity, List<BatchStock> filteredProductList);

    BatchStock selectBatchStock(PurchaseOrderItems purchaseOrderItems);
}
