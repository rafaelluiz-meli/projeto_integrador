package com.mercadolivre.bootcamp.projeto_integrador.repository;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface BatchStockRepository extends JpaRepository<BatchStock, Long> {

    List<BatchStock> findAllByProduct_Id(Long productId);
    List<BatchStock> findAllByDueDate(LocalDate dueDate);
    List<BatchStock> findAllByProduct_IdAndAndDueDate(Long productId, LocalDate dueDate);
    @Query("select b from BatchStock b where b.dueDate >= ?1")
    List<BatchStock> findByDueDateIsGreaterThanEqual(LocalDate dueDate);
}
