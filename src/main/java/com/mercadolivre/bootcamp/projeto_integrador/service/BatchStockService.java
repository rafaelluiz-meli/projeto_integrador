package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;

import java.util.List;
import java.util.Optional;

public interface BatchStockService {
    BatchStock create(BatchStock batchStock);
    List<BatchStock> list();
    BatchStock findById(Long id);
    BatchStock update(BatchStock batchStock);
    List<BatchStock> findAllByProductId(String id);
    void remove(Long id);

}
