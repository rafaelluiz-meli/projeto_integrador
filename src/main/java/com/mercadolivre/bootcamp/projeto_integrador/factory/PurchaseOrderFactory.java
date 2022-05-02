package com.mercadolivre.bootcamp.projeto_integrador.factory;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Buyer;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import com.mercadolivre.bootcamp.projeto_integrador.service.BatchStockService;
import com.mercadolivre.bootcamp.projeto_integrador.service.BuyerService;
import com.mercadolivre.bootcamp.projeto_integrador.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PurchaseOrderFactory {

    private final BuyerService buyerService;

    private final BatchStockService batchStockService;

    private boolean validateNewPurchaseOrder(Long buyerId, Long productId, Integer requestedQuantity){
        buyerService.findById(buyerId);
        batchStockService.isListProductWithValidatedDueDateAndQuantity(productId, requestedQuantity);
        return true;
    }
}
