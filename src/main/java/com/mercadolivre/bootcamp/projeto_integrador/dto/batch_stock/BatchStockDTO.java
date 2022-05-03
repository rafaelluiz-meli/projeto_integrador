package com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock;

import com.mercadolivre.bootcamp.projeto_integrador.dto.product.ProductDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
public class BatchStockDTO {

    private Long batchNumber;
    private int currentQuantity;
    private LocalDate dueDate;

    public static BatchStockDTO convert(BatchStock batchStock) {
        return BatchStockDTO.builder()
                .batchNumber(batchStock.getBatchNumber())
                .currentQuantity(batchStock.getCurrentQuantity())
                .dueDate(batchStock.getDueDate())
                .build();
    }

    public static List<BatchStockDTO> convert(List<BatchStock> batchStockList) {
        return batchStockList.stream().map(BatchStockDTO::convert).collect(Collectors.toList());
    }



}
