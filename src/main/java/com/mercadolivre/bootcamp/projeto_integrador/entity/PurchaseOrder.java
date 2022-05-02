package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "purchase_order_number", nullable = false)
    private List<PurchaseOrderItens> purchaseOrderItemsList;
}
