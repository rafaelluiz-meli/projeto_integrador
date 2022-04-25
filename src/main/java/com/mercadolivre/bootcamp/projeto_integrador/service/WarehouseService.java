package com.mercadolivre.bootcamp.projeto_integrador.service;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse find(String warehouseId);
    List<Warehouse> findAll();
    boolean isValidWarehouse(String warehouseId);
}
