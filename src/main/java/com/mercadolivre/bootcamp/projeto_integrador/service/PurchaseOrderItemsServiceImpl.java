package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order_items.NewPurchaseOrderItemsDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.PurchaseOrderItemsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PurchaseOrderItemsServiceImpl implements PurchaseOrderItemsService {
    private final PurchaseOrderItemsRepository purchaseOrderItemsRepository;

    @Override
    public PurchaseOrderItems addPurchaseOrderItens(NewPurchaseOrderItemsDTO purchaseOrderItensDTO) {
        PurchaseOrderItems purchaseOrderItems = PurchaseOrderItems.builder()
                .productId(purchaseOrderItensDTO.getProductId())
                .quantity(purchaseOrderItensDTO.getQuantity())
                .build();

        return purchaseOrderItemsRepository.save(purchaseOrderItems);
    }

    @Override
    public List<PurchaseOrderItems> getAllPurchaseOrderItens() {
        return findAll();
    }

    @Override
    public PurchaseOrderItems updatePurchaseOrderItens(PurchaseOrderItems purchaseOrderItems) {
        PurchaseOrderItems updatePurchaseOrderItems = findById(purchaseOrderItems.getPurchaseOrderItensId());

        updatePurchaseOrderItems.setPurchaseOrderNumber(purchaseOrderItems.getPurchaseOrderNumber());
        updatePurchaseOrderItems.setProductId(purchaseOrderItems.getProductId());
        updatePurchaseOrderItems.setQuantity(purchaseOrderItems.getQuantity());

        return purchaseOrderItemsRepository.save(updatePurchaseOrderItems);
    }

    @Override
    public void deletePurchaseOrderItens(Long purchaseOrderItensId) {
        PurchaseOrderItems purchaseOrderItems = findById(purchaseOrderItensId);
        purchaseOrderItemsRepository.delete(purchaseOrderItems);
    }

    @Override
    public List<PurchaseOrderItems> findAll() {
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        if (purchaseOrderItemsList.isEmpty()) throw new EmptyListException();
        return purchaseOrderItemsList;
    }

    @Override
    public PurchaseOrderItems findById(Long purchaseOrderItensId) {
        return purchaseOrderItemsRepository.findById(purchaseOrderItensId).orElseThrow(()
                -> new IdNotFoundException(purchaseOrderItensId));
    }


}
