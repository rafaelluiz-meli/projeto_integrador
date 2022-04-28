package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PurchaseOrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PurchaseOrderItemsId;
    private Long purchaseOrderNumber;
    private String productId;
    private int quantity;
}
