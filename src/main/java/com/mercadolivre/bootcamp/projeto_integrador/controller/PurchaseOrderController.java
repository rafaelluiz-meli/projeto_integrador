package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order.NewPurchaseOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;
import com.mercadolivre.bootcamp.projeto_integrador.factory.PurchaseOrderFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PurchaseOrderController.baseUri)
@AllArgsConstructor
public class PurchaseOrderController {

    public static final String baseUri =  "/api/v1/fresh-products/purchaseOrder";

    private final PurchaseOrderFactory purchaseOrderFactory;

    @PostMapping
    public ResponseEntity<PurchaseOrder> createNewPurchaseOrder(@RequestBody NewPurchaseOrderDTO newPurchaseOrderDTO){
        PurchaseOrder purchaseOrder = purchaseOrderFactory.createNewPurchaseOrder(newPurchaseOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrder);
    }

    @GetMapping
    public ResponseEntity<List<PurchaseOrder>>listPurchaseOrders(){
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@RequestBody PurchaseOrder purchaseOrder){
        purchaseOrder = purchaseOrderFactory.setStatusOrderClosed(purchaseOrder);
        return ResponseEntity.ok(purchaseOrder);
    }
}
