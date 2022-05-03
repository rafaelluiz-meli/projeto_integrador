package com.mercadolivre.bootcamp.projeto_integrador.factory;

import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order.NewPurchaseOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.*;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockService;
import com.mercadolivre.bootcamp.projeto_integrador.service.BuyerService;
import com.mercadolivre.bootcamp.projeto_integrador.service.ProductService;
import com.mercadolivre.bootcamp.projeto_integrador.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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

    public List<PurchaseOrderItems> getPurchaseOrderItems(Long orderNumber) {
        return purchaseOrderService.findById(orderNumber).getPurchaseOrderItemsList();
    }

    public List<List<BatchStock>> calculateTotalValue(NewPurchaseOrderDTO newPurchaseOrderDTO){
        List<PurchaseOrderItems> purchaseOrderItemsList = newPurchaseOrderDTO.getPurchaseOrderItemsList();
        List<List<BatchStock>> result = purchaseOrderItemsList.stream().map(p ->
                batchStockService.isProductWithValidatedDueDateAndQuantity(
                        p.getProductId(), p.getQuantity())).collect(Collectors.toList());

        BigDecimal totalValue;
        result.forEach(batchStock -> {
            batchStock.forEach(product -> {
                BigDecimal price = product.getPrice();
                Integer quantity = product.getCurrentQuantity();
                if( >= product.getCurrentQuantity()){
                    totalValue = price.multiply(BigDecimal.valueOf(quantity));
                }
            });
        });
        //        purchaseOrderItemsList.stream().map(product -> {
//                List<BatchStock> batchStockList =
//                    batchStockService.isProductWithValidatedDueDateAndQuantity(
//                            product.getProductId(), product.getQuantity());
//
//                List<BigDecimal> listPrices =
//                        batchStockList.stream().map(BatchStock::getPrice).collect(Collectors.toList());

// Todo: filtrar por quantidade de produto 
// TODO: 02/05/22 multiplicar valor por quantidade             

        //});
        return result;
    }

    private void validateNewPurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO){
        Long buyerId = newPurchaseOrderDTO.getBuyerDTO().getBuyerId();
        List<PurchaseOrderItems> purchaseOrderItemsList = newPurchaseOrderDTO.getPurchaseOrderItemsList();
        buyerService.findById(buyerId);
        purchaseOrderItemsList.forEach(product ->
                batchStockService.isProductWithValidatedDueDateAndQuantity(
                        product.getProductId(),product.getQuantity()));
    }

    private PurchaseOrder setStatusOrderClosed(PurchaseOrder purchaseOrder){
        purchaseOrder.setStatusOrder(StatusOrder.CLOSED);
        return purchaseOrder;
    }
    public PurchaseOrder updatePurchaseOrder(PurchaseOrder purchaseOrder){
        PurchaseOrder updatedPurchaseOrder = this.setStatusOrderClosed(purchaseOrder);
        return purchaseOrderService.update(updatedPurchaseOrder);
    }
}
