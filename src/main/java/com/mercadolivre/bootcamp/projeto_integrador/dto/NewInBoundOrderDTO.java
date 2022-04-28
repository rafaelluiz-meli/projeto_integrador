package com.mercadolivre.bootcamp.projeto_integrador.dto;

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
    private Long orderNumber;
    private Long representativeId;
    private SectionDTO section;
    private NewBatchStockDTO batchStock;
}
