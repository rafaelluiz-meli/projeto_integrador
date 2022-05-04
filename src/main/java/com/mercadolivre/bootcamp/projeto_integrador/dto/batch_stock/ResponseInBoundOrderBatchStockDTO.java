package com.mercadolivre.bootcamp.projeto_integrador.dto.batch_stock;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseInBoundOrderBatchStockDTO {
    private Long batchNumber;
    private Integer quantity;
    private BigDecimal price;
    private LocalDate dueDate;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private Long productId;

    public BatchStock map() {
        return BatchStock.builder()
                    .initialQuantity(this.quantity)
                    .currentQuantity(this.quantity)
                    .price(this.price)
                    .dueDate(this.dueDate)
                    .manufacturingDate(this.manufacturingDate)
                    .manufacturingTime(this.manufacturingTime)
                    .build();
    }

    public BatchStock map(BatchStock currentBatchStock) {
        return BatchStock.builder()
                .batchNumber(currentBatchStock.getBatchNumber())
                .initialQuantity(currentBatchStock.getInitialQuantity())
                .currentQuantity(this.quantity)
                .price(this.price)
                .dueDate(currentBatchStock.getDueDate())
                .manufacturingDate(currentBatchStock.getManufacturingDate())
                .manufacturingTime(currentBatchStock.getManufacturingTime())
                .product(currentBatchStock.getProduct())
                .build();
    }

}
