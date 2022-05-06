package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order.NewPurchaseOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order.PurchaseOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItems;
import com.mercadolivre.bootcamp.projeto_integrador.factory.PurchaseOrderFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This class represents PurchaseOrder controller.
 */
@RestController
@RequestMapping(PurchaseOrderController.baseUri)
@AllArgsConstructor
public class PurchaseOrderController {

    public static final String baseUri =  "/api/v1/fresh-products/purchaseOrder";

    private final PurchaseOrderFactory purchaseOrderFactory;

    /**
     *
     * @param newPurchaseOrderDTO Dto received in request body to create a new PurchaseOrder
     * @return 201 CREATED
     */
    @PostMapping
    public ResponseEntity<PurchaseOrderDTO> createNewPurchaseOrder(@RequestBody NewPurchaseOrderDTO newPurchaseOrderDTO){
        PurchaseOrderDTO purchaseOrder = purchaseOrderFactory.createNewPurchaseOrder(newPurchaseOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseOrder);
    }

    /**
     *
     * @param orderNumber Number of PurchaseOrder to list the purchaseOrderItems.
     * @return 200 OK
     */
    @GetMapping
    public ResponseEntity<List<PurchaseOrderItems>>listPurchaseOrders(@RequestParam Long orderNumber){
        List<PurchaseOrderItems> purchaseOrderItems = purchaseOrderFactory.getPurchaseOrderItems(orderNumber);
        return ResponseEntity.ok().body(purchaseOrderItems);
    }

    /**
     *
     * @param newPurchaseOrderDTO Dto in request body to update a purchase order by id
     * @return 200 OK
     */
    @PutMapping
    public ResponseEntity<PurchaseOrderDTO> updatePurchaseOrder(@RequestBody NewPurchaseOrderDTO newPurchaseOrderDTO){
        PurchaseOrderDTO updatePurchaseOrder = purchaseOrderFactory.updatePurchaseOrder(newPurchaseOrderDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatePurchaseOrder);
    }
}
