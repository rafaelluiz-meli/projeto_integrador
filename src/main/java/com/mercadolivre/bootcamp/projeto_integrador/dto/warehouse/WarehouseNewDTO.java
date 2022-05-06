package com.mercadolivre.bootcamp.projeto_integrador.dto.warehouse;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
public class WarehouseNewDTO {
    private Long warehouseId;
    private Long totalInitialQuantity;
    private Long totalQuantity;
    private Long percent;
}
