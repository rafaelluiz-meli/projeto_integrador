package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import java.util.List;

public interface InBoundOrderService {
    InBoundOrder create(InBoundOrder inBoundOrder);
    List<InBoundOrder> read();
    InBoundOrder update(InBoundOrder inBoundOrder);
    void delete(Long inBoundOrderNumber);
    InBoundOrder findById(Long inBoundOrderNumber);
}
