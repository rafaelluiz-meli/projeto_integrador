package com.mercadolivre.bootcamp.projeto_integrador.factory;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order.NewPurchaseOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Buyer;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrder;
import com.mercadolivre.bootcamp.projeto_integrador.entity.PurchaseOrderItens;
import com.mercadolivre.bootcamp.projeto_integrador.entity.StatusOrder;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockService;
import com.mercadolivre.bootcamp.projeto_integrador.service.BuyerService;
import com.mercadolivre.bootcamp.projeto_integrador.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class PurchaseOrderFactory {

    private final BuyerService buyerService;

    private final BatchStockService batchStockService;

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrder createNewPurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO){
        this.validateNewPurchaseOrder(newPurchaseOrderDTO);
        Buyer buyer = buyerService.findById(newPurchaseOrderDTO.getBuyerDTO().getBuyerId());
        PurchaseOrder createdPurchaseOrder = PurchaseOrder.builder()
                .buyer(buyer)
                .purchaseOrderDate(LocalDate.now())
                .purchaseOrderItemsList(newPurchaseOrderDTO.getPurchaseOrderItemsList())
                .statusOrder(StatusOrder.CART)
                .build();

        return purchaseOrderService.create(createdPurchaseOrder);
    }
    private void validateNewPurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO){
        Long buyerId = newPurchaseOrderDTO.getBuyerDTO().getBuyerId();
        List<PurchaseOrderItens> purchaseOrderItemsList = newPurchaseOrderDTO.getPurchaseOrderItemsList();
        buyerService.findById(buyerId);
        purchaseOrderItemsList.forEach(product ->
                batchStockService.isListProductWithValidatedDueDateAndQuantity(
                        product.getProductId(),product.getQuantity()));
    }
}
