package com.mercadolivre.bootcamp.projeto_integrador.dto.purchase_order_items;

import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewPurchaseOrderItemsDTO {
    private Long productId;
    private Long purchaseOrderNumber;
    private Integer quantity;

}
