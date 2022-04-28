package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewPurchaseOrderItensDTO;
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
    public PurchaseOrderItems addPurchaseOrderItems(NewPurchaseOrderItensDTO purchaseOrderItemsDTO) {
        PurchaseOrderItems purchaseOrderItems = PurchaseOrderItems.builder()
                .purchaseOrderNumber(purchaseOrderItemsDTO.getPurchaseOrderNumber())
                .productId(purchaseOrderItemsDTO.getProductId())
                .quantity(purchaseOrderItemsDTO.getQuantity())
                .build();

        return purchaseOrderItemsRepository.save(purchaseOrderItems);
    }

    @Override
    public List<PurchaseOrderItems> getAllPurchaseOrderItems() {
        return findAll();
    }

    @Override
    public PurchaseOrderItems updatePurchaseOrderItems(PurchaseOrderItems purchaseOrderItems) {
        PurchaseOrderItems updatePurchaseOrderItems = findById(purchaseOrderItems.getPurchaseOrderItemsId());

        updatePurchaseOrderItems.setPurchaseOrderNumber(purchaseOrderItems.getPurchaseOrderNumber());
        updatePurchaseOrderItems.setProductId(purchaseOrderItems.getProductId());
        updatePurchaseOrderItems.setQuantity(purchaseOrderItems.getQuantity());

        return purchaseOrderItemsRepository.save(updatePurchaseOrderItems);
    }

    @Override
    public void deletePurchaseOrderItems(Long purchaseOrderItemsId) {
        PurchaseOrderItems purchaseOrderItems = findById(purchaseOrderItemsId);
        purchaseOrderItemsRepository.delete(purchaseOrderItems);
    }

    @Override
    public List<PurchaseOrderItems> findAll() {
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        if (purchaseOrderItemsList.isEmpty()) throw new EmptyListException();
        return purchaseOrderItemsList;
    }

    @Override
    public PurchaseOrderItems findById(Long purchaseOrderItemsId) {
        return purchaseOrderItemsRepository.findById(purchaseOrderItemsId).orElseThrow(()
                -> new IdNotFoundException(purchaseOrderItemsId));
    }
}