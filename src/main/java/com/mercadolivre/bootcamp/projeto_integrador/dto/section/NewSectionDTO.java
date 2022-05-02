package com.mercadolivre.bootcamp.projeto_integrador.dto.section;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewSectionDTO {

    private BigDecimal capacity;
    private float currentTemperature;
    private Category category;
    private Long warehouseId;

    public static Section convert(NewSectionDTO newSectionDTO){
        return Section.builder()
                .capacity(newSectionDTO.getCapacity())
                .currentTemperature(newSectionDTO.getCurrentTemperature())
                .category(newSectionDTO.getCategory())
                .warehouseId(newSectionDTO.getWarehouseId()).build();
    }

    public static NewSectionDTO convert(Section section){
        return NewSectionDTO.builder()
                .capacity(section.getCapacity())
                .currentTemperature(section.getCurrentTemperature())
                .category(section.getCategory())
                .warehouseId(section.getWarehouseId()).build();
    }
}
