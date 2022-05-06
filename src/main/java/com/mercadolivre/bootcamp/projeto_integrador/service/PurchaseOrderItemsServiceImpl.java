package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order_items.NewPurchaseOrderItemsDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.PurchaseOrderItemsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is the service implementation of PurchaseOrderItems entity.
 */
@AllArgsConstructor
@Service
public class PurchaseOrderItemsServiceImpl implements PurchaseOrderItemsService {
    private final PurchaseOrderItemsRepository purchaseOrderItemsRepository;

    /**
     * Create a new PurchaseOrderItems.
     * @param purchaseOrderItemsDTO
     * @return the created PurchaseOrderItems.
     */
    @Override
    public PurchaseOrderItems addPurchaseOrderItems(NewPurchaseOrderItemsDTO purchaseOrderItemsDTO) {
        PurchaseOrderItems purchaseOrderItems = PurchaseOrderItems.builder()
                .productId(purchaseOrderItemsDTO.getProductId())
                .quantity(purchaseOrderItemsDTO.getQuantity())
                .build();

        return purchaseOrderItemsRepository.save(purchaseOrderItems);
    }

    /**
     * Get all PurchaseOrderItems using the method {@link #findAll() findAll}.
     * @return a PurchaseOrderItems list.
     */
    @Override
    public List<PurchaseOrderItems> getAllPurchaseOrderItems() {
        return findAll();
    }

    /**
     * Check if PurchaseOrderItems id exists with {@link #findById(Long) findById} method. <br>
     * If exists then update PurchaseOrderItems attributes.
     * @param purchaseOrderItems
     * @return a updated PurchaseOrderItems.
     *
     */
    @Override
    public PurchaseOrderItems updatePurchaseOrderItems(PurchaseOrderItems purchaseOrderItems) {
        PurchaseOrderItems updatePurchaseOrderItems = findById(purchaseOrderItems.getPurchaseOrderItemsId());
        updatePurchaseOrderItems.setProductId(purchaseOrderItems.getProductId());
        updatePurchaseOrderItems.setQuantity(purchaseOrderItems.getQuantity());

        return purchaseOrderItemsRepository.save(updatePurchaseOrderItems);
    }

    /**
     * Check if PurchaseOrderItemsId exists with {@link #findById(Long) findById} method. <br>
     * If exists then remove the PurchaseOrderItems.
     * @param purchaseOrderItemsId
     * @return void.
     *
     */
    @Override
    public void deletePurchaseOrderItems(Long purchaseOrderItemsId) {
        PurchaseOrderItems purchaseOrderItems = findById(purchaseOrderItemsId);
        purchaseOrderItemsRepository.delete(purchaseOrderItems);
    }

    /**
     * Get from database all PurchaseOrderItems.
     * @return a PurchaseOrderItems list.
     * @exception EmptyListException if PurchaseOrderItems list is empty.
     */
    @Override
    public List<PurchaseOrderItems> findAll() {
        List<PurchaseOrderItems> purchaseOrderItemsList = purchaseOrderItemsRepository.findAll();
        if (purchaseOrderItemsList.isEmpty()) throw new EmptyListException();
        return purchaseOrderItemsList;
    }

    /**
     * Find a PurchaseOrderItems by Id.
     * @param purchaseOrderItemsId
     * @return a PurchaseOrderItems.
     * @exception IdNotFoundException if id doesn't found.
     */
    @Override
    public PurchaseOrderItems findById(Long purchaseOrderItemsId) {
        return purchaseOrderItemsRepository.findById(purchaseOrderItemsId).orElseThrow(()
                -> new IdNotFoundException(purchaseOrderItemsId));
    }
}
