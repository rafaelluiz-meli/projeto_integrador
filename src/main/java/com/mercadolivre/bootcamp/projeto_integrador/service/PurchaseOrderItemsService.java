package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewPurchaseOrderItensDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;

import java.util.List;

public interface PurchaseOrderItemsService {
    PurchaseOrderItems addPurchaseOrderItems(NewPurchaseOrderItensDTO purchaseOrderItemsDTO);
    List<PurchaseOrderItems> findAll();
    List<PurchaseOrderItems> getAllPurchaseOrderItems();
    PurchaseOrderItems findById(Long purchaseOrderItemsId);
    PurchaseOrderItems updatePurchaseOrderItems(PurchaseOrderItems purchaseOrderItems);
    void deletePurchaseOrderItems(Long purchaseOrderItemsId);
}
