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
public class PurchaseOrderHistoryBatchStockDTO {

    private Long idHistory;
    private BatchStock batchStock;
    private HistoryType historyType;
    private Long purchaseOrderId;
    private Long productId;
    private Integer purchaseQuantity;
    private LocalDateTime purchaseOrderDate;

    public HistoryBatchStock map(){
        return HistoryBatchStock.builder()
                .id(this.idHistory)
                .batchStock(this.batchStock)
                .historyType(this.historyType)
                .purchaseOrderId(this.purchaseOrderId)
                .productId(this.productId)
                .quantity(this.purchaseQuantity)
                .dateHistory(this.purchaseOrderDate)
                .build();
    }

    public static PurchaseOrderHistoryBatchStockDTO map(HistoryBatchStock historyBatchStock) {
        return PurchaseOrderHistoryBatchStockDTO.builder()
                .idHistory(historyBatchStock.getId())
                .batchStock(historyBatchStock.getBatchStock())
                .historyType(historyBatchStock.getHistoryType())
                .purchaseOrderId(historyBatchStock.getPurchaseOrderId())
                .productId(historyBatchStock.getProductId())
                .purchaseQuantity(historyBatchStock.getQuantity())
                .purchaseOrderDate(historyBatchStock.getDateHistory())
                .build();
    }
}
