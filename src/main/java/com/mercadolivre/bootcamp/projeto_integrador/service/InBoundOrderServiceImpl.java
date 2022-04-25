package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import com.mercadolivre.bootcamp.projeto_integrador.repository.InBoundOrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InBoundOrderServiceImpl implements InBoundOrderService{
    private InBoundOrderRepository repository;

    public InBoundOrder create(InBoundOrder inBoundOrder){
        return repository.save(inBoundOrder);
    }

    public List<InBoundOrder> read(){
        return repository.findAll();
    }

    public InBoundOrder update(InBoundOrder inBoundOrder){
        InBoundOrder updateInBoundOrder = findById(inBoundOrder.getInBoundOrderNumber());
    }

    public void delete(Long inBoundOrderNumber){
        InBoundOrder inBoundOrder = findById(inBoundOrderNumber);
        repository.delete(inBoundOrder);
    }

    public InBoundOrder findById(Long inBoundOrderNumber){
        return repository.findById(inBoundOrderNumber).orElseThrow() -> new InBoundOrderIdNotFoundException(inBoundOrderNumber);
    }
}
