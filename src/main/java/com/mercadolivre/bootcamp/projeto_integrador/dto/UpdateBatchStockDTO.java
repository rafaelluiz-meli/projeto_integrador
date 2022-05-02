package com.mercadolivre.bootcamp.projeto_integrador.dto;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBatchStockDTO {

    private Integer currentQuantity;
    private BigDecimal price;
    private LocalDate dueDate;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private Product product;

    public BatchStock map(){
        return BatchStock.builder()
                .currentQuantity(this.currentQuantity)
                .price(this.price)
                .dueDate(this.dueDate)
                .manufacturingDate(this.manufacturingDate)
                .manufacturingTime(this.manufacturingTime)
                .product(this.product)
                .build();
    }

    public static UpdateBatchStockDTO map(BatchStock batchStock){
        return UpdateBatchStockDTO.builder()
                .currentQuantity(batchStock.getCurrentQuantity())
                .price(batchStock.getPrice())
                .dueDate(batchStock.getDueDate())
                .manufacturingDate(batchStock.getManufacturingDate())
                .manufacturingTime(batchStock.getManufacturingTime())
                .product(batchStock.getProduct())
                .build();
    }
}