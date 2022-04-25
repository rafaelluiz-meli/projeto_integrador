package com.mercadolivre.bootcamp.projeto_integrador.dto;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.InBoundOrder;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class NewSectionDTO {

    private String sectionId;
    private String type;
    private BigDecimal capacity;
    private float currentTemperature;
    private List<InBoundOrder> listInBoundOrder;
    private Category category;
    private String warehouseId;

    private Section section;

    public NewSectionDTO(String sectionId, String type, BigDecimal capacity,
                         float currentTemperature, Category category, String warehouseId) {

        this.sectionId = sectionId;
        this.type = type;
        this.capacity = capacity;
        this.currentTemperature = currentTemperature;
        this.listInBoundOrder = new ArrayList<>();
        this.category = category;
        this.warehouseId = warehouseId;
    }

    public static Section convert(NewSectionDTO newSectionDTO){
        return Section.builder()
                .sectionId(newSectionDTO.getSectionId())
                .type(newSectionDTO.getType())
                .capacity(newSectionDTO.getCapacity())
                .currentTemperature(newSectionDTO.getCurrentTemperature())
                .listInBoundOrder(newSectionDTO.getListInBoundOrder())
                .category(newSectionDTO.getCategory())
                .warehouseId(newSectionDTO.getWarehouseId()).build();
    }

    public static List<Section> convert(List<NewSectionDTO> newSectionDTOList){
        return newSectionDTOList.stream().map(NewSectionDTO::convert).collect(Collectors.toList());
    }
}
