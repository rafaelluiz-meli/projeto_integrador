package com.mercadolivre.bootcamp.projeto_integrador.dto.warehouse;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ResponseWarehouseNewDTO {
    private Long productId;
    private List<WarehouseNewDTO> listWarehouse;

    public static ResponseWarehouseNewDTO convert(Long productId, List<WarehouseNewDTO> listWarehouse) {
        return ResponseWarehouseNewDTO.builder()
                .productId(productId)
                .listWarehouse(listWarehouse)
                .build();
    }
}
