package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PurchaseOrderItens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PurchaseOrderItensId;
    private Long productId;
    private Integer quantity;
}
