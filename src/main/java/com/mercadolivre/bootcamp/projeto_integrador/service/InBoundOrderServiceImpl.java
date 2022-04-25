package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import com.mercadolivre.bootcamp.projeto_integrador.exception.InBoundOrderIdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.InBoundOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InBoundOrderServiceImpl implements InBoundOrderService{
    private InBoundOrderRepository repository;

    @Override
    public InBoundOrder create(InBoundOrder inBoundOrder){
        //Todo: validation of section, warehouse and representative
        return repository.save(inBoundOrder);
    }

    @Override
    public List<InBoundOrder> read(){
        return repository.findAll();
    }

    @Override
    public InBoundOrder update(InBoundOrder inBoundOrder){
        InBoundOrder updateInBoundOrder = findById(inBoundOrder.getInBoundOrderNumber());
        updateInBoundOrder.setInBoundOrderDate(inBoundOrder.getInBoundOrderDate());
        updateInBoundOrder.setBatchStockList(inBoundOrder.getBatchStockList());
        updateInBoundOrder.setRepresentativeId(inBoundOrder.getRepresentativeId());
        return repository.save(updateInBoundOrder);
    }

    @Override
    public void delete(Long inBoundOrderNumber){
        InBoundOrder inBoundOrder = findById(inBoundOrderNumber);
        repository.delete(inBoundOrder);
    }

    @Override
    public InBoundOrder findById(Long inBoundOrderNumber){
        return repository.findById(inBoundOrderNumber).orElseThrow(() -> new InBoundOrderIdNotFoundException(inBoundOrderNumber));
    }
}
