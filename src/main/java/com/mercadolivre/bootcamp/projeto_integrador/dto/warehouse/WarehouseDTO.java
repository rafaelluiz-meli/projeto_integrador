package com.mercadolivre.bootcamp.projeto_integrador.dto.warehouse;

import com.mercadolivre.bootcamp.projeto_integrador.dto.salesman.NewSalesmanDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Data
@AllArgsConstructor
public class WarehouseDTO {

    private Long warehouseId;
    private Long totalQuantity;

}
