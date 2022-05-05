package com.mercadolivre.bootcamp.projeto_integrador.repository;

import com.mercadolivre.bootcamp.projeto_integrador.entity.HistoryBatchStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryBatchStockRepository extends JpaRepository<HistoryBatchStock, Long> {

    @Query("SELECT h FROM HistoryBatchStock h WHERE h.batchStock.batchNumber = ?1")
    List<HistoryBatchStock> findAllHistoryByBatchStockId(Long id);
}
