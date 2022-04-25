package com.mercadolivre.bootcamp.projeto_integrador.entity;

import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sectionId;
    private String type;
    private BigDecimal capacity;
    private float currentTemperature;
    private List<inBoundOrder> listInBoundOrder;
    private Category category;
    private String warehouseId;

}
