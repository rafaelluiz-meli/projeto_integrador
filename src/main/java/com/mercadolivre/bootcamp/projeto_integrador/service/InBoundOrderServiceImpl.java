package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewInBoundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.exception.inBoudnOrderException.InBoundOrderEmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.inBoudnOrderException.InBoundOrderIdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.InBoundOrderRepository;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@AllArgsConstructor
public class InBoundOrderServiceImpl implements InBoundOrderService{
    private final InBoundOrderRepository inBoundOrderrepository;

    @Override
    public InBoundOrder addInBoundOrder(NewInBoundOrderDTO inBoundOrderDTO){
        //Todo: validation of section, warehouse and representative
        InBoundOrder inBoundOrder = InBoundOrder.builder()
                .inBoundOrderDate(inBoundOrderDTO.getInBoundOrderDate())
                .batchStock(inBoundOrderDTO.getBatchStock())
                .representativeId(inBoundOrderDTO.getRepresentativeId())
                .build();

        return inBoundOrderrepository.save(inBoundOrder);
    }

    @Override
    public List<InBoundOrder> getAllInBoundOrder(){
        return inBoundOrderrepository.findAll();
    }

    @Override
    public InBoundOrder getInBoundOrderById(Long inBoundOrderNumber){
        return inBoundOrderrepository.findById(inBoundOrderNumber).orElseThrow(() ->
                new InBoundOrderIdNotFoundException(inBoundOrderNumber));
    }

    @Override
    public InBoundOrder updateInBoundOrder(InBoundOrder inBoundOrder){
        InBoundOrder updateInBoundOrderById = inBoundOrderrepository.findById(inBoundOrder.getInBoundOrderNumber()).orElseThrow(() ->
                new InBoundOrderIdNotFoundException(inBoundOrder.getInBoundOrderNumber()));

        updateInBoundOrderById.setInBoundOrderDate(inBoundOrder.getInBoundOrderDate());
        updateInBoundOrderById.setBatchStock(inBoundOrder.getBatchStock());
        updateInBoundOrderById.setRepresentativeId(inBoundOrder.getRepresentativeId());
        return inBoundOrderrepository.save(updateInBoundOrderById);
    }

    @Override
    public void deleteInBoundOrder(Long inBoundOrderNumber){
        InBoundOrder inBoundOrder = getInBoundOrderById(inBoundOrderNumber);
        inBoundOrderrepository.delete(inBoundOrder);
    }
}
