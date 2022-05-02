package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order_items.NewPurchaseOrderItemsDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItens;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.PurchaseOrderItensRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PurchaseOrderItensServiceImpl implements PurchaseOrderItensService{
    private final PurchaseOrderItensRepository purchaseOrderItensRepository;

    @Override
    public PurchaseOrderItens addPurchaseOrderItens(NewPurchaseOrderItemsDTO purchaseOrderItensDTO) {
        PurchaseOrderItens purchaseOrderItens = PurchaseOrderItens.builder()
                //.purchaseOrderNumber(purchaseOrderItensDTO.getPurchaseOrderNumber())
                .productId(purchaseOrderItensDTO.getProductId())
                .quantity(purchaseOrderItensDTO.getQuantity())
                .build();

        return purchaseOrderItensRepository.save(purchaseOrderItens);
    }

    @Override
    public List<PurchaseOrderItens> getAllPurchaseOrderItens() {
        return findAll();
    }

    @Override
    public PurchaseOrderItens updatePurchaseOrderItens(PurchaseOrderItens purchaseOrderItens) {
        PurchaseOrderItens updatePurchaseOrderItens = findById(purchaseOrderItens.getPurchaseOrderItensId());

        //updatePurchaseOrderItens.setPurchaseOrderNumber(purchaseOrderItens.getPurchaseOrderNumber());
        updatePurchaseOrderItens.setProductId(purchaseOrderItens.getProductId());
        updatePurchaseOrderItens.setQuantity(purchaseOrderItens.getQuantity());

        return purchaseOrderItensRepository.save(updatePurchaseOrderItens);
    }

    @Override
    public void deletePurchaseOrderItens(Long purchaseOrderItensId) {
        PurchaseOrderItens purchaseOrderItens = findById(purchaseOrderItensId);
        purchaseOrderItensRepository.delete(purchaseOrderItens);
    }

    @Override
    public List<PurchaseOrderItens> findAll() {
        List<PurchaseOrderItens> purchaseOrderItensList = purchaseOrderItensRepository.findAll();
        if (purchaseOrderItensList.isEmpty()) throw new EmptyListException();
        return purchaseOrderItensList;
    }

    @Override
    public PurchaseOrderItens findById(Long purchaseOrderItensId) {
        return purchaseOrderItensRepository.findById(purchaseOrderItensId).orElseThrow(()
                -> new IdNotFoundException(purchaseOrderItensId));
    }


}
