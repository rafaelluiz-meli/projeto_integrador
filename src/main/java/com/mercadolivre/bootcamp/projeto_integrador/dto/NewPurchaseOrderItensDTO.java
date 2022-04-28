package com.mercadolivre.bootcamp.projeto_integrador.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NewPurchaseOrderItensDTO {
    private Long productId;
    private Long purchaseOrderNumber;
    private int quantity;
}
