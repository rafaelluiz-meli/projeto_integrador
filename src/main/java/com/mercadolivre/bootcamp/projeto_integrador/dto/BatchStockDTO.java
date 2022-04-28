package com.mercadolivre.bootcamp.projeto_integrador.dto;

import com.mercadolivre.bootcamp.projeto_integrador.entity.BatchStock;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Product;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockDTO {
    private Integer initialQuantity;
    private Integer currentQuantity;
    private BigDecimal price;
    private LocalDate dueDate;
    private LocalDate manufacturingDate;
    private LocalDateTime manufacturingTime;
    private Product product;

    public BatchStock map(){
        return BatchStock.builder()
                .initialQuantity(this.initialQuantity)
                .currentQuantity(this.currentQuantity)
                .price(this.price)
                .dueDate(this.dueDate)
                .manufacturingDate(this.manufacturingDate)
                .manufacturingTime(this.manufacturingTime)
                .product(this.product)
                .build();
    }

    public static BatchStockDTO map(BatchStock batchStock){
        return BatchStockDTO.builder()
                .initialQuantity(batchStock.getInitialQuantity())
                .currentQuantity(batchStock.getCurrentQuantity())
                .price(batchStock.getPrice())
                .dueDate(batchStock.getDueDate())
                .manufacturingDate(batchStock.getManufacturingDate())
                .manufacturingTime(batchStock.getManufacturingTime())
                .product(batchStock.getProduct())
                .build();
    }

    public static List<BatchStockDTO> map(List<BatchStock> batchStockList){
        return batchStockList.stream().map(BatchStockDTO::map).collect(Collectors.toList());
    }
}
