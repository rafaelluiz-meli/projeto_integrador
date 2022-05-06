package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * This class is the service implementation of PurchaseOrder entity.
 */
@AllArgsConstructor
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    private final PurchaseOrderRepository repository;

    /**
     * Create a new PurchaseOrder.
     * @param purchaseOrder
     * @return the created PurchaseOrder.
     */
    @Override
    public PurchaseOrder create(PurchaseOrder purchaseOrder) {
        return repository.save(purchaseOrder);
    }

    /**
     * Get from database all PurchaseOrders.
     * @return a PurchaseOrder list.
     * @exception EmptyListException if PurchaseOrder list is empty.
     */
    @Override
    public List<PurchaseOrder> findAll() {
        List<PurchaseOrder> purchaseOrderList = repository.findAll();
        if (purchaseOrderList.isEmpty()) throw new EmptyListException();
        return purchaseOrderList;
    }

    /**
     * Find a PurchaseOrder by Id.
     * @param id
     * @return a PurchaseOrder.
     * @exception IdNotFoundException if id doesn't found.
     */
    @Override
    public PurchaseOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    /**
     * Check if PurchaseOrderNumber exists with {@link #findById(Long) findById} method. <br>
     * If exists and statusOrder, Buyer and PurchaseOrderItemsList are not null
     * then update PurchaseOrder attributes.
     * @param purchaseOrder
     * @return a updated PurchaseOrder.
     *
     */
    @Override
    public PurchaseOrder update(PurchaseOrder purchaseOrder) {
        PurchaseOrder updatedPurchaseOrder = findById(purchaseOrder.getPurchaseOrderNumber());
        if(purchaseOrder.getPurchaseOrderNumber() != null){
            updatedPurchaseOrder.setPurchaseOrderDate(purchaseOrder.getPurchaseOrderDate());
        }
        if(purchaseOrder.getStatusOrder() != null) {
            updatedPurchaseOrder.setStatusOrder(purchaseOrder.getStatusOrder());
        }
        if(purchaseOrder.getBuyer().getBuyerId() != null) {
            updatedPurchaseOrder.setBuyer(purchaseOrder.getBuyer());
        }
        if(purchaseOrder.getPurchaseOrderItemsList() != null) {
            updatedPurchaseOrder.setPurchaseOrderItemsList(purchaseOrder.getPurchaseOrderItemsList());
        }

        return repository.save(updatedPurchaseOrder);
    }

    /**
     * Check if PurchaseOrder id exists with {@link #findById(Long) findById} method. <br>
     * If exists then remove the PurchaseOrder.
     * @param id
     * @return void.
     *
     */
    @Override
    public void remove(Long id) {
        PurchaseOrder purchaseOrder = findById(id);
        repository.delete(purchaseOrder);
    }
}
