package com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock;

import com.mercadolivre.bootcamp.projeto_integrador.dto.section.SectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class BatchStockWithSectionDTO {

    private SectionDTO section;
    private Long productId;
    private List<BatchStockDTO> listBatchStock;

}
