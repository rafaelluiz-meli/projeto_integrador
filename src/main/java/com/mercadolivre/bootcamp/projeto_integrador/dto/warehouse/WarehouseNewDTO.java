package com.mercadolivre.bootcamp.projeto_integrador.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class WarehouseNewDTO {
    private Long warehouseId;
    private Long totalInitialQuantity;
    private Long totalQuantity;
}
