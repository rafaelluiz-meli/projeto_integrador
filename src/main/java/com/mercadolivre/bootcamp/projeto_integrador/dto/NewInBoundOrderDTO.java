package com.mercadolivre.bootcamp.projeto_integrador.dto;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewInBoundOrderDTO {
    private String representativeId;
    private SectionDTO sectionDTO;
    private BatchStock batchStock;
}
