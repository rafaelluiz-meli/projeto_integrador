package com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import lombok.*;
import org.hibernate.engine.jdbc.batch.spi.Batch;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseBatchStockDTO {
    private Long batchNumber;
    private Long productId;
    private Category category;
    private LocalDate dueDate;
    private int quantity;

    public static ResponseBatchStockDTO map(BatchStock batchStock) {
        return ResponseBatchStockDTO.builder()
                .batchNumber(batchStock.getBatchNumber())
                .productId(batchStock.getProduct().getId())
                .category(batchStock.getProduct().getCategory())
                .dueDate(batchStock.getDueDate())
                .quantity(batchStock.getCurrentQuantity())
                .build();
    }

    public static List<ResponseBatchStockDTO> map(List<BatchStock> batchStockList) {
        return batchStockList.stream().map(ResponseBatchStockDTO::map).collect(Collectors.toList());
    }
 }
