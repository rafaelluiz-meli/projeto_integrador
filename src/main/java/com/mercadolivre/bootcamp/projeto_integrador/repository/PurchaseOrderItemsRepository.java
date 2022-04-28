package com.mercadolivre.bootcamp.projeto_integrador.repository;

import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderItemsRepository extends JpaRepository<PurchaseOrderItems, Long> {
}
