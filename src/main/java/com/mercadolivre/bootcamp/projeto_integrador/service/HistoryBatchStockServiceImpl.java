package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.HistoryBatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.repository.HistoryBatchStockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HistoryBatchStockServiceImpl implements HistoryBatchStockService{

    private final HistoryBatchStockRepository repository;

    @Override
    public HistoryBatchStock createNewHistory(HistoryBatchStock historyBatchStock) {
        return repository.save(historyBatchStock);
    }

    @Override
    public List<HistoryBatchStock> listAllHistoryByBatchStock(Long id) {
        return repository.findAllHistoryByBatchStockId(id);
    }
}
