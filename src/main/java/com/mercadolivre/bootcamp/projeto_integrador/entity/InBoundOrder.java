package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class InBoundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long inBoundOrderNumber;
    private LocalDate inBoundOrderDate;
    //Todo: List of BatchStock
//    @OneToOne(cacade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private List<BatchStock> batchStockList;
    //Todo:
//    @OneToOne
//    @JoinColumn(name = "representative_id")
//    private Representative representativeId;
}
