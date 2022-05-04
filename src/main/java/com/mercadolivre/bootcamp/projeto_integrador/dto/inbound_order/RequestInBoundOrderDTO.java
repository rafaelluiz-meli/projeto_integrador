package com.mercadolivre.bootcamp.projeto_integrador.dto.inbound_order;

import com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock.NewBatchStockDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.section.SectionDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RequestInBoundOrderDTO {
    private Long orderNumber;
    private Long representativeId;
    private SectionDTO section;
    private NewBatchStockDTO batchStock;
}
