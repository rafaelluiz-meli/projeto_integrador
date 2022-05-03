package com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock;

import com.mercadolivre.bootcamp.projeto_integrador.dto.section.SectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class BatchStockWithSectionDTO {

    private SectionDTO sectionDTO;
    private Long productId;
    private List<BatchStockDTO> listBatchStock;

    static BatchStockWithSectionDTO convert(BatchStock batchStock){
        return BatchStockWithSectionDTO.builder()
                .sectionDTO(SectionDTO.builder()
                        .sectionId(batchStock.getSection().getSectionId())
                        .warehouseId(batchStock.getSection().getWarehouseId())
                        .build())
                .productId(batchStock.getProduct().getId())
                // TODO: 03/05/22 aguardando a criacao da lista. --Flavio Germano 
                //.listBatchStock(batchStock.)
                .build();
    }



}
