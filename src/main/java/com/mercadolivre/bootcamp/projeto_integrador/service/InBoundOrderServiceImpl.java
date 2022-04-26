package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewInBoundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.exception.InBoundOrderIdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.InBoundOrderRepository;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class InBoundOrderServiceImpl implements InBoundOrderService{
    private InBoundOrderRepository inBoundOrderrepository;

    @Override
    public InBoundOrder addInBoundOrder(NewInBoundOrderDTO inBoundOrderDTO){
        //Todo: validation of section, warehouse and representative
        InBoundOrder savedInBoundOrderDTO = inBoundOrderrepository.save(NewInBoundOrderDTO.convert(inBoundOrderDTO));
        return savedInBoundOrderDTO;
    }

    @Override
    public List<InBoundOrder> getAllInBoundOrder(){
        List<InBoundOrder> listInBoundOrder = inBoundOrderrepository.findAll();
        return listInBoundOrder;
    }

    @Override
    public InBoundOrder getInBoundOrderById(Long inBoundOrderNumber){
        InBoundOrder getInBoundOrderById = inBoundOrderrepository.findById(inBoundOrderNumber).orElseThrow(() ->
                new InBoundOrderIdNotFoundException(inBoundOrderNumber));
        return getInBoundOrderById;
    }

    @Override
    public InBoundOrder updateInBoundOrder(InBoundOrder inBoundOrder){
        InBoundOrder getInBoundOrderById = inBoundOrderrepository.findById(inBoundOrder.getInBoundOrderNumber()).orElseThrow(() ->
                new InBoundOrderIdNotFoundException(inBoundOrder.getInBoundOrderNumber()));

        getInBoundOrderById.setInBoundOrderDate(inBoundOrder.getInBoundOrderDate());
        //Todo: finish implementation of commented lines below
//        getInBoundOrderById.setBatchStockList(inBoundOrder.getBatchStockList());
//        getInBoundOrderById.setRepresentativeId(inBoundOrder.getRepresentativeId());
        return inBoundOrderrepository.save(getInBoundOrderById);
    }

    @Override
    public void deleteInBoundOrder(Long inBoundOrderNumber){
        InBoundOrder inBoundOrder = getInBoundOrderById(inBoundOrderNumber);
        inBoundOrderrepository.delete(inBoundOrder);
        try {
            inBoundOrderrepository.deleteById(inBoundOrderNumber);
        } catch (EmptyResultDataAccessException e) {
            new InBoundOrderIdNotFoundException(inBoundOrderNumber);
        }
    }
}
