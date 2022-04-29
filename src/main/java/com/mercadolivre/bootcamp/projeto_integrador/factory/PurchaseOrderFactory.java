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

    private final ProductService productService;

    private final BatchStockService batchStockService;

    public boolean isBuyerRegistered(Long buyerId){
        Long result = buyerService.findById(buyerId).getBuyerId();
        return result > 1;
    }

    public boolean isProductInStock(Long productId, Integer quantity) {
        return productService.availableStockQuantity(quantity);
    }

    public boolean isProductValidForNextThreeWeeks(Long productId){
        return productService.validateProductDueDate(productId);
    }
}
