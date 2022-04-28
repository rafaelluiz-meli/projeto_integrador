package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Data
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;
    private String name;

    public Warehouse(String name) {
        this.name = name;
    }
}
