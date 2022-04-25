package com.mercadolivre.bootcamp.projeto_integrador.dto;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class NewProductDto {
    private String productName;
    private BigDecimal volume;
    private Float minimumTemperature;
    private Float maxTemperature;
    private Category category;
    private String salesman_id;
}
