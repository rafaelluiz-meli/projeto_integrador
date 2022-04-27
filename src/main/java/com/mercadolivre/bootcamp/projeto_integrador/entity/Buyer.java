package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Buyer {

    @Id
    private Long id;
}
