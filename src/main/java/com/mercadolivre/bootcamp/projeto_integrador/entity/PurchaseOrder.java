package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseOrderNumber;
    private StatusOrder statusOrder;
    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Buyer buyer;
    private LocalDate purchaseOrderDate;
}
