package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order_items.NewPurchaseOrderItemsDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItens;

import java.util.List;

public interface PurchaseOrderItensService {
    PurchaseOrderItens addPurchaseOrderItens(NewPurchaseOrderItemsDTO purchaseOrderItensDTO);
    List<PurchaseOrderItens> getAllPurchaseOrderItens();
    PurchaseOrderItens updatePurchaseOrderItens(PurchaseOrderItens purchaseOrderItens);
    void deletePurchaseOrderItens(Long purchaseOrderItensId);
    List<PurchaseOrderItens> findAll();
    PurchaseOrderItens findById(Long purchaseOrderItensId);
}
