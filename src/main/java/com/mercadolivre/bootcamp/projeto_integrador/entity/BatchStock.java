package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Entity
public class BatchStock {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long batchNumber;
    private Integer initialQuantity;
    private Integer currentQuantity;
    private BigDecimal price;
    private LocalDate dueDate;
    private LocalDate manufacturingDate;

    private LocalDateTime manufacturingTime;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
