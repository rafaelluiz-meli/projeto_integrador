package com.mercadolivre.bootcamp.projeto_integrador.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long buyerId;
    private String fullName;
}
