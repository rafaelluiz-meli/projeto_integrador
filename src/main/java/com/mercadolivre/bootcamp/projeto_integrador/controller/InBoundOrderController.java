package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.inbound_order.RequestInBoundOrderDTO;
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
    public ResponseEntity<InBoundOrder> createInboundOrder(@RequestBody RequestInBoundOrderDTO requestInBoundOrderDTO) {
        InBoundOrder createdInboundOrder = inBoundOrderFactory.createInBoundOrder(requestInBoundOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInboundOrder);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InBoundOrder> updateInboundOrder(@PathVariable Long id, @RequestBody RequestInBoundOrderDTO requestInBoundOrderDTO) {
        InBoundOrder updatedInboundOrder = inBoundOrderFactory.updateInboundOrder(id, requestInBoundOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedInboundOrder);
    }
}
