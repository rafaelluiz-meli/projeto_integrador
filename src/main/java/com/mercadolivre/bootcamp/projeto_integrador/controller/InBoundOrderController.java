package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.inbound_order.NewInBoundOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import com.mercadolivre.bootcamp.projeto_integrador.factory.InBoundOrderFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder")
@AllArgsConstructor
public class InBoundOrderController {
    private final InBoundOrderFactory inBoundOrderFactory;


    @PostMapping
    public ResponseEntity<InBoundOrder> createInboundOrder(@RequestBody NewInBoundOrderDTO newInBoundOrderDTO) {
        InBoundOrder createdInboundOrder = inBoundOrderFactory.createInBoundOrder(newInBoundOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInboundOrder);
    }

    @PutMapping
    public ResponseEntity<InBoundOrder> updateInboundOrder(@RequestBody NewInBoundOrderDTO newInBoundOrderDTO) {
        InBoundOrder updatedInboundOrder = inBoundOrderFactory.updateInboundOrder(newInBoundOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedInboundOrder);
    }
}
