package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;
    private BigDecimal capacity;
    private float currentTemperature;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Long warehouseId;

}
