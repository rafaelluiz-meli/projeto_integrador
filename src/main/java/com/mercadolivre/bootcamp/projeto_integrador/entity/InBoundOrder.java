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
    //Todo: List of BatchStock
//    private List<BatchStock> batchStockList;
    //Todo:
//    @OneToOne
//    @JoinColumn(name = "representative_id")
//    private Representative representativeId;
}
