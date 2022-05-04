package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private BigDecimal volume;
    private Float minimumTemperature;
    private Float maxTemperature;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    @JoinColumn(name = "salesman_id")
    private Salesman salesman;

}
