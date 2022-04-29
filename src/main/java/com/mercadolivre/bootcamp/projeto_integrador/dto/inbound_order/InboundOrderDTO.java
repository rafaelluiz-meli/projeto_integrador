package com.mercadolivre.bootcamp.projeto_integrador.dto.inbound_order;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class InboundOrderDTO {
    private Long orderNumber;
    private Long representativeId;
    private Section section;
    private BatchStock batchStock;

}
