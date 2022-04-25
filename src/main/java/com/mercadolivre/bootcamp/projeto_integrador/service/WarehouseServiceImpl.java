package com.mercadolivre.bootcamp.projeto_integrador.service;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.exception.WarehouseNaoExisteException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WarehouseServiceImpl implements WarehouseService{
    private WarehouseRepository warehouseRepository;


    @Override
    public Warehouse find(String warehouseId) {
        Optional<Warehouse> wh = warehouseRepository.findById(warehouseId);
        if(wh.isEmpty()){
            throw new WarehouseNaoExisteException(warehouseId);
        }else{
            return wh.get();
        }
    }

    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> warehouses = warehouseRepository.findAll();
        return warehouses;
    }

    @Override
    public boolean isValidWarehouse(String warehouseId) {
        Optional<Warehouse> wh = warehouseRepository.findById(warehouseId);
        if(wh.isEmpty()){
            throw new WarehouseNaoExisteException(warehouseId);
        }else{
            return true;
        }
    }


}
