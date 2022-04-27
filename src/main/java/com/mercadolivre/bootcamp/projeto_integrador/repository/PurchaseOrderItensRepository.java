package com.mercadolivre.bootcamp.projeto_integrador.repository;

import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderItensRepository extends JpaRepository<PurchaseOrderItens, String> {
}
