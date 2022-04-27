package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewPurchaseOrderItensDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItens;

import java.util.List;

public interface PurchaseOrderItensService {
    PurchaseOrderItens addPurchaseOrderItens(NewPurchaseOrderItensDTO purchaseOrderItensDTO);
    List<PurchaseOrderItens> getAllPurchaseOrderItens();
    PurchaseOrderItens updatePurchaseOrderItens(PurchaseOrderItens purchaseOrderItens);
    void deletePurchaseOrderItens(String purchaseOrderItensId);
    List<PurchaseOrderItens> findAll();
    PurchaseOrderItens findById(String purchaseOrderItensId);
}
