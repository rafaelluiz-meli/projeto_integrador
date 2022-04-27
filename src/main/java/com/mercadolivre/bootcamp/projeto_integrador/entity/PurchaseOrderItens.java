package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PurchaseOrderItens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String PurchaseOrderItensId;
    private Long purchaseOrderNumber;
    private String productId;
    private int quantity;
}
