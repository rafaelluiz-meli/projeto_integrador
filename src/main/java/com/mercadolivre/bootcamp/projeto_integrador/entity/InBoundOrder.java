package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private BatchStock batchStock;
    private String representativeId;
    private String sectionId;
}

