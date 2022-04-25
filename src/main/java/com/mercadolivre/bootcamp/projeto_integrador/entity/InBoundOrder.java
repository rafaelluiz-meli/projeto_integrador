package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity @Getter @Setter
public class InBoundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long inBoundOrderNumber;
    private LocalDate inBoundOrderDate;
//    private List<Batchstock> batchStockList;
//    @OneToOne
//    @JoinColumn(name = "representative_id")
//    private Representative representativeId;
}
