package com.mercadolivre.bootcamp.projeto_integrador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SectionDTO {
    String sectionId;
    String warehouseId;
}
