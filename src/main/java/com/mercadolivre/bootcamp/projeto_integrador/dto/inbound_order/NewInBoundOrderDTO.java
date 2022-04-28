package com.mercadolivre.bootcamp.projeto_integrador.dto.inbound_order;

import com.mercadolivre.bootcamp.projeto_integrador.dto.section.SectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NewInBoundOrderDTO {
    private Long representativeId;
    private SectionDTO sectionDTO;
    private BatchStock batchStock;
}
