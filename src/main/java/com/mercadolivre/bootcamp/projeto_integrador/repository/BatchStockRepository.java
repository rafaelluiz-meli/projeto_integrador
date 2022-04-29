package com.mercadolivre.bootcamp.projeto_integrador.repository;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

public interface BatchStockRepository extends JpaRepository<BatchStock, Long> {

    List<BatchStock> findAllByProduct_Id(Long productId);
}
