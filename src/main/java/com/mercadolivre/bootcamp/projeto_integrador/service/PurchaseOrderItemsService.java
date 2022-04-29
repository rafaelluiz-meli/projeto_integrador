package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order_items.NewPurchaseOrderItemsDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;

import java.util.List;

public interface PurchaseOrderItemsService {
    PurchaseOrderItems addPurchaseOrderItens(NewPurchaseOrderItemsDTO purchaseOrderItensDTO);
    List<PurchaseOrderItems> getAllPurchaseOrderItens();
    PurchaseOrderItems updatePurchaseOrderItens(PurchaseOrderItems purchaseOrderItems);
    void deletePurchaseOrderItens(Long purchaseOrderItensId);
    List<PurchaseOrderItems> findAll();
    PurchaseOrderItems findById(Long purchaseOrderItensId);
}
