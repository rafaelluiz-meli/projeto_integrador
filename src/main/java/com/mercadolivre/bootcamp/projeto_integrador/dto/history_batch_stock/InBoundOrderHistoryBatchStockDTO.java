package com.mercadolivre.bootcamp.projeto_integrador.dto.history_batch_stock;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.HistoryBatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.HistoryType;
import lombok.*;

import java.time.LocalDateTime;
@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class InBoundOrderHistoryBatchStockDTO {

    private Long idHistory;
    private BatchStock batchStock;
    private HistoryType historyType;
    private Long inBoundOrderId;
    private Long productId;
    private Integer initialQuantity;
    private LocalDateTime inBoundOrderDate;

    public HistoryBatchStock map(){
        return HistoryBatchStock.builder()
                .id(this.idHistory)
                .batchStock(this.batchStock)
                .historyType(this.historyType)
                .inBoundOrderId(this.inBoundOrderId)
                .productId(this.productId)
                .quantity(this.initialQuantity)
                .dateHistory(this.inBoundOrderDate)
                .build();
    }

    public static InBoundOrderHistoryBatchStockDTO map(HistoryBatchStock historyBatchStock) {
        return InBoundOrderHistoryBatchStockDTO.builder()
                .idHistory(historyBatchStock.getId())
                .batchStock(historyBatchStock.getBatchStock())
                .historyType(historyBatchStock.getHistoryType())
                .inBoundOrderId(historyBatchStock.getInBoundOrderId())
                .productId(historyBatchStock.getProductId())
                .initialQuantity(historyBatchStock.getQuantity())
                .inBoundOrderDate(historyBatchStock.getDateHistory())
                .build();
    }
}
