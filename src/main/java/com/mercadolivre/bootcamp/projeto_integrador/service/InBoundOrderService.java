package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.inbound_order.InboundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import java.util.List;

public interface InBoundOrderService {
    InBoundOrder addInBoundOrder(InboundOrderDTO inBoundOrderDTO);
    List<InBoundOrder> findAll();
    List<InBoundOrder> getAllInBoundOrder();
    InBoundOrder findById(Long inBoundOrderNumber);
    InBoundOrder updateInBoundOrder(InBoundOrder inBoundOrder);
    void deleteInBoundOrder(Long inBoundOrderNumber);
}
