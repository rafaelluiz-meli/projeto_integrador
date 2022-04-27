package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewInBoundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import java.util.List;

public interface InBoundOrderService {
    InBoundOrder addInBoundOrder(NewInBoundOrderDTO inBoundOrderDTO);
    List<InBoundOrder> getAllInBoundOrder();
    InBoundOrder findById(Long inBoundOrderNumber);
    List<InBoundOrder> findAll();
    InBoundOrder updateInBoundOrder(InBoundOrder inBoundOrder);
    void deleteInBoundOrder(Long inBoundOrderNumber);
}