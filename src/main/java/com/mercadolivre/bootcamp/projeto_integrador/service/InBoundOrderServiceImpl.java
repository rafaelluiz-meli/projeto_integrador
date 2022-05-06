package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.inbound_order.InboundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.InBoundOrderRepository;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * This class is the service implementation of inBoundOrder entity.
 */
@Service
@AllArgsConstructor
public class InBoundOrderServiceImpl implements InBoundOrderService{
    private final InBoundOrderRepository inBoundOrderrepository;

    /**
     * Create a new inBoundOrder.
     * @param inBoundOrderDTO
     * @return the created inBoundOrder.
     */
    @Override
    public InBoundOrder addInBoundOrder(InboundOrderDTO inBoundOrderDTO){
        InBoundOrder inBoundOrder = InBoundOrder.builder()
                .inBoundOrderNumber(inBoundOrderDTO.getOrderNumber())
                .inBoundOrderDate(LocalDateTime.now())
                .batchStock(inBoundOrderDTO.getBatchStock())
                .representativeId(inBoundOrderDTO.getRepresentativeId())
                .section(inBoundOrderDTO.getSection())
                .build();

        return inBoundOrderrepository.save(inBoundOrder);
    }

    /**
     * Get from database all inBoundOrder.
     * @return a inBoundOrder list.
     * @exception EmptyListException if inBoundOrder list is empty.
     */
    @Override
    public List<InBoundOrder> findAll() {
        List<InBoundOrder> inBoundOrderList = inBoundOrderrepository.findAll();
        if (inBoundOrderList.isEmpty()) throw new EmptyListException();
        return inBoundOrderList;
    }

    /**
     * Get all inBoundOrder using the method {@link #findAll() findAll}.
     * @return a inBoundOrder list.
     */
    @Override
    public List<InBoundOrder> getAllInBoundOrder(){
        return findAll();
    }

    /**
     * Find a InBoundOrder by Id.
     * @param inBoundOrderNumber
     * @return a InBoundOrder.
     * @exception IdNotFoundException if id doesn't found.
     */
    @Override
    public InBoundOrder findById(Long inBoundOrderNumber){
        return inBoundOrderrepository.findById(inBoundOrderNumber).orElseThrow(() ->
                new IdNotFoundException(inBoundOrderNumber));
    }

    /**
     * Check if InBoundOrder id exists with {@link #findById(Long) findById} method. <br>
     * If exists then update InBoundOrder attributes.
     * @param inBoundOrder
     * @return a updated InBoundOrder.
     *
     */
    @Override
    public InBoundOrder updateInBoundOrder(InBoundOrder inBoundOrder){
        InBoundOrder updateInBoundOrderById = findById(inBoundOrder.getInBoundOrderNumber());

        updateInBoundOrderById.setInBoundOrderDate(inBoundOrder.getInBoundOrderDate());
        updateInBoundOrderById.setBatchStock(inBoundOrder.getBatchStock());

        return inBoundOrderrepository.save(updateInBoundOrderById);
    }

    /**
     * Check if InBoundOrder id exists with {@link #findById(Long) findById} method. <br>
     * If exists then remove the InBoundOrder.
     * @param inBoundOrderNumber
     * @return void.
     *
     */
    @Override
    public void deleteInBoundOrder(Long inBoundOrderNumber){
        InBoundOrder inBoundOrder = findById(inBoundOrderNumber);
        inBoundOrderrepository.delete(inBoundOrder);
    }
}
