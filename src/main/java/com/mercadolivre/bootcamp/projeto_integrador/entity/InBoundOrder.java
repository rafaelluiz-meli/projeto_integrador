package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class InBoundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long inBoundOrderNumber;
    private LocalDate inBoundOrderDate;

}