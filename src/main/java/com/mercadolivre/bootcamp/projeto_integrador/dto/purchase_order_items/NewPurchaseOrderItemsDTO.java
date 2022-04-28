package com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order_items;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewPurchaseOrderItemsDTO {
    private Long productId;
    private Long purchaseOrderNumber;
    private int quantity;
}
