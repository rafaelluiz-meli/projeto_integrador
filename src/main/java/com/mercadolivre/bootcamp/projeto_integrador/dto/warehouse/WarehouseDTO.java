package com.mercadolivre.bootcamp.projeto_integrador.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class WarehouseDTO {

    private Long warehouseId;
    private Long totalQuantity;

}
