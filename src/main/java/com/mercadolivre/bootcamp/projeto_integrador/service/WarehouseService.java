package com.mercadolivre.bootcamp.projeto_integrador.service;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse findById(String warehouseId);
    List<Warehouse> findAll();
    boolean isValidWarehouse(String warehouseId);
    void save(Warehouse wh);
}
