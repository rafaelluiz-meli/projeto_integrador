package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.HistoryBatchStock;

import java.util.List;

public interface HistoryBatchStockService {

    HistoryBatchStock createNewHistory(HistoryBatchStock historyBatchStock);

    List<HistoryBatchStock> listAllHistoryByBatchStock(Long id);
}
