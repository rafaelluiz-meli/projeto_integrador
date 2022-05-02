package com.mercadolivre.bootcamp.projeto_integrador.factory;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order.NewPurchaseOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.*;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockService;
import com.mercadolivre.bootcamp.projeto_integrador.service.BuyerService;
import com.mercadolivre.bootcamp.projeto_integrador.service.ProductService;
import com.mercadolivre.bootcamp.projeto_integrador.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PurchaseOrderFactory {

    private final ProductService productService;
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

    public PurchaseOrder setStatusOrderClosed(PurchaseOrder purchaseOrder){
        purchaseOrder.setStatusOrder(StatusOrder.CLOSED);
        return purchaseOrder;
    }

    public List<PurchaseOrderItems> getPurchaseOrderItems(Long orderNumber) {
        return purchaseOrderService.findById(orderNumber).getPurchaseOrderItemsList();
    }

//    private void calculateTotalValue(NewPurchaseOrderDTO newPurchaseOrderDTO){
//        List<PurchaseOrderItems> purchaseOrderItemsList = newPurchaseOrderDTO.getPurchaseOrderItemsList();
//        List<Product> productList = purchaseOrderItemsList.stream()
//                .map(p -> productService.findByProductId(p.getProductId())).collect(Collectors.toList());
//        productList.stream().map(Product::).reduce()
//    }

    private void validateNewPurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO){
        Long buyerId = newPurchaseOrderDTO.getBuyerDTO().getBuyerId();
        List<PurchaseOrderItems> purchaseOrderItemsList = newPurchaseOrderDTO.getPurchaseOrderItemsList();
        buyerService.findById(buyerId);
        purchaseOrderItemsList.forEach(product ->
                batchStockService.isListProductWithValidatedDueDateAndQuantity(
                        product.getProductId(),product.getQuantity()));
    }
}
