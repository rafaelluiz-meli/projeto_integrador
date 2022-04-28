package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.PurchaseOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService{

    private final PurchaseOrderRepository repository;

    @Override
    public PurchaseOrder create(PurchaseOrder purchaseOrder) {
        return repository.save(purchaseOrder);
    }

    @Override
    public List<PurchaseOrder> findAll() {
        List<PurchaseOrder> purchaseOrderList = repository.findAll();
        if (purchaseOrderList.isEmpty()) throw new EmptyListException();
        return purchaseOrderList;
    }

    @Override
    public PurchaseOrder findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
    }

    @Override
    public PurchaseOrder update(PurchaseOrder purchaseOrder) {
        PurchaseOrder updatedPurchaseOrder = findById(purchaseOrder.getPurchaseOrderNumber());
        if(purchaseOrder.getPurchaseOrderNumber() != null){
            updatedPurchaseOrder.setPurchaseOrderDate(purchaseOrder.getPurchaseOrderDate());
        }
        if(purchaseOrder.getStatusOrder() != null) {
            updatedPurchaseOrder.setStatusOrder(purchaseOrder.getStatusOrder());
        }
        if(purchaseOrder.getBuyerId() != null) {
            updatedPurchaseOrder.setBuyerId(purchaseOrder.getBuyerId());
        }
        return repository.save(updatedPurchaseOrder);
    }

    @Override
    public void remove(Long id) {
        PurchaseOrder purchaseOrder = findById(id);
        repository.delete(purchaseOrder);
    }
}