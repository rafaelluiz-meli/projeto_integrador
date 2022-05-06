package com.mercadolivre.bootcamp.projeto_integrador.dto.warehouse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * MODIFICANDO TESTE 1
 *
 */


@Builder
@Data
@AllArgsConstructor
public class WarehouseDTO {

    private Long warehouseId;
    private Long totalQuantity;

}
