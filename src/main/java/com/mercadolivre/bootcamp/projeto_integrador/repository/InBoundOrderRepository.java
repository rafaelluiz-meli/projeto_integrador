package com.mercadolivre.bootcamp.projeto_integrador.repository;

import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InBoundOrderRepository extends JpaRepository<InBoundOrder, Long> {
    @Query("select i from InBoundOrder i where i.batchStock.product.id = ?1")


    List<InBoundOrder> findByBatchStock_Product_Id(Long id);
    /*
    List<result> resultadoFodaPraCaralho = LISTA TOPPER

    resultadoDaQuery.forEach(
     () -> {
        // filtrar por warehouse
        // adicionar ao DTO de resultado a warehouse e a quantidade
     }
    )
     */

}
