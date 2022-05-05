package com.mercadolivre.bootcamp.projeto_integrador.factory;

import com.mercadolivre.bootcamp.projeto_integrador.dto.history_batch_stock.PurchaseOrderHistoryBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order.NewPurchaseOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order.PurchaseOrderDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.*;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockService;
import com.mercadolivre.bootcamp.projeto_integrador.service.BuyerService;
import com.mercadolivre.bootcamp.projeto_integrador.service.HistoryBatchStockService;
import com.mercadolivre.bootcamp.projeto_integrador.service.PurchaseOrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PurchaseOrderFactory {

    private final HistoryBatchStockService historyBatchStockService;
    private final BuyerService buyerService;

    private final BatchStockService batchStockService;

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderDTO createNewPurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO){
        this.validateNewPurchaseOrder(newPurchaseOrderDTO);
        PurchaseOrder createdPurchaseOrder = purchaseOrderService.create(convertToPurchaseOrder(newPurchaseOrderDTO));

        PurchaseOrderDTO createdPurchaseOrderDTO = PurchaseOrderDTO.map(createdPurchaseOrder);

        BigDecimal totalValue = this.calculateTotalValue(newPurchaseOrderDTO);
        createdPurchaseOrderDTO.setTotalValue(totalValue);

        return createdPurchaseOrderDTO;
    }

    private void addHistoryBatchStock(PurchaseOrder purchaseOrder, BatchStock batchStock){
        List<PurchaseOrderItems> productsOfPurchaseOrder = purchaseOrder.getPurchaseOrderItemsList();
        List<HistoryBatchStock> dtoList = productsOfPurchaseOrder.stream().map(product ->
        {
            PurchaseOrderHistoryBatchStockDTO dto = PurchaseOrderHistoryBatchStockDTO.builder()
                    .purchaseOrderId(purchaseOrder.getPurchaseOrderNumber())
                    .batchStock(batchStock)
                    .productId(product.getProductId())
                    .purchaseQuantity(product.getQuantity())
                    .historyType(HistoryType.EXIT)
                    .purchaseOrderDate(purchaseOrder.getPurchaseOrderDate().atTime(LocalTime.now()))
                    .build();
            return dto.map();
        }).collect(Collectors.toList());
        dtoList.forEach(historyBatchStockService::createNewHistory);
    }

    public PurchaseOrderDTO updatePurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO){
        // Update batchStock when finishing a purchaseOrder (setting status to closed)
        this.updateBatchStockQuantity(newPurchaseOrderDTO);

        // Update purchaseOrder
        newPurchaseOrderDTO.setStatusOrder(StatusOrder.CLOSED);
        PurchaseOrder updatedPurchaseOrder = this.convertToPurchaseOrder(newPurchaseOrderDTO);
        PurchaseOrder updatedResponsePurchaseOrder = purchaseOrderService.update(updatedPurchaseOrder);
        return PurchaseOrderDTO.map(updatedResponsePurchaseOrder);
    }

    private void updateBatchStockQuantity(NewPurchaseOrderDTO newPurchaseOrderDTO) {
        List<PurchaseOrderItems> purchaseOrderItemsList = newPurchaseOrderDTO.getPurchaseOrderItemsList();
        List<BatchStock> batchStockList = this.selectAvailableBatchStock(newPurchaseOrderDTO);

        List<BatchStock> updatedBatchStockList = batchStockList.stream().peek(batchStock -> {
            Long productId = batchStock.getProduct().getId();
            Integer currentQuantity = batchStock.getCurrentQuantity();

            PurchaseOrderItems purchaseOrder = purchaseOrderItemsList
                    .stream()
                    .filter(p -> productId.equals(p.getProductId()))
                    .findFirst()
                    .orElseThrow(EmptyListException::new);

            batchStock.setCurrentQuantity(currentQuantity - purchaseOrder.getQuantity());
            //addHistoryBatchStock(newPurchaseOrderDTO.map(), batchStock);
        }).collect(Collectors.toList());
        updatedBatchStockList.forEach(batchStockService::update);


    }

    public List<PurchaseOrderItems> getPurchaseOrderItems(Long orderNumber) {
        return purchaseOrderService.findById(orderNumber).getPurchaseOrderItemsList();
    }

    private PurchaseOrder convertToPurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO) {
        Buyer buyer = buyerService.findById(newPurchaseOrderDTO.getBuyerDTO().getBuyerId());

        if (newPurchaseOrderDTO.getStatusOrder() == null) {
            newPurchaseOrderDTO.setStatusOrder(StatusOrder.CART);
        }

        PurchaseOrder createdPurchaseOrder = PurchaseOrder.builder()
                .purchaseOrderNumber(newPurchaseOrderDTO.getPurchaseOrderNumber())
                .buyer(buyer)
                .purchaseOrderDate(LocalDate.now())
                .purchaseOrderItemsList(newPurchaseOrderDTO.getPurchaseOrderItemsList())
                .statusOrder(newPurchaseOrderDTO.getStatusOrder())
                .build();

        return createdPurchaseOrder;
    }

    private BigDecimal calculateTotalValue(NewPurchaseOrderDTO newPurchaseOrderDTO){
        List<PurchaseOrderItems> purchaseOrderItemsList = newPurchaseOrderDTO.getPurchaseOrderItemsList();

        // Retorna lista de produtos disponíveis para purchaseOrder
        // Faz validação de (Quantity, ProductId e DueDate)
        List<BatchStock> availableBatchStockList = this.selectAvailableBatchStock(newPurchaseOrderDTO);


        // Itera sobre lista de lotes disponíveis para aquele produto
        // Faz soma de (preço do lote * quantidade) para cada produto
        BigDecimal sumTotalValue = availableBatchStockList.stream().map(batchStock -> {
            Long productId = batchStock.getProduct().getId();

            PurchaseOrderItems purchaseOrder = purchaseOrderItemsList
                    .stream()
                    .filter(p -> productId.equals(p.getProductId()))
                    .findFirst()
                    .orElseThrow(EmptyListException::new);

            return batchStock.getPrice().multiply(BigDecimal.valueOf(purchaseOrder.getQuantity()));
        }).reduce(BigDecimal.ZERO, BigDecimal::add);

        return sumTotalValue;
    }

    private List<BatchStock> selectAvailableBatchStock(NewPurchaseOrderDTO newPurchaseOrderDTO) {
        List<PurchaseOrderItems> purchaseOrderItemsList = newPurchaseOrderDTO.getPurchaseOrderItemsList();

        return purchaseOrderItemsList
                        .stream()
                        .map(batchStockService::selectBatchStock)
                        .collect(Collectors.toList());
    }

    private void validateNewPurchaseOrder(NewPurchaseOrderDTO newPurchaseOrderDTO){

        // Validates buyer
        Long buyerId = newPurchaseOrderDTO.getBuyerDTO().getBuyerId();
        buyerService.findById(buyerId); // throws exception if can'' find buyer

        // Validates products
        List<PurchaseOrderItems> purchaseOrderItemsList = newPurchaseOrderDTO.getPurchaseOrderItemsList();

        purchaseOrderItemsList.forEach(product ->
                batchStockService.isProductWithValidatedDueDateAndQuantity(
                        product.getProductId(),product.getQuantity()));
    }

}
