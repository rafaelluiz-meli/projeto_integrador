package com.mercadolivre.bootcamp.projeto_integrador.entity;

import com.mercadolivre.bootcamp.projeto_integrador.service.RepresentativeService;
import com.mercadolivre.bootcamp.projeto_integrador.service.RepresentativeServiceImpl;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Representative {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String representativeId;
    private String fullName;
    private String sectionId;

}
