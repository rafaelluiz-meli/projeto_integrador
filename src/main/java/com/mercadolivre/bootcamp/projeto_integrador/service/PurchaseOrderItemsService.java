package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order_items.NewPurchaseOrderItemsDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;

import java.util.List;

public interface PurchaseOrderItemsService {
    PurchaseOrderItems addPurchaseOrderItems(NewPurchaseOrderItemsDTO purchaseOrderItemsDTO);
    List<PurchaseOrderItems> getAllPurchaseOrderItems();
    PurchaseOrderItems updatePurchaseOrderItems(PurchaseOrderItems purchaseOrderItems);
    void deletePurchaseOrderItems(Long purchaseOrderItemsId);
    List<PurchaseOrderItems> findAll();
    PurchaseOrderItems findById(Long purchaseOrderItemsId);
}
