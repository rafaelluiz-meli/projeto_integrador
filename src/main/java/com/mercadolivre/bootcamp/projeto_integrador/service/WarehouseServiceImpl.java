package com.mercadolivre.bootcamp.projeto_integrador.service;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.exception.WarehouseNaoExisteException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.WarehouseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class WarehouseServiceImpl implements WarehouseService{
    private WarehouseRepository warehouseRepository;

    @Override
    public Warehouse findById(String warehouseId) {
        Optional<Warehouse> wh = warehouseRepository.findById(warehouseId);
        if(wh.isEmpty()){
            throw new WarehouseNaoExisteException(warehouseId);
        }else{
            return wh.get();
        }
    }

    @Override
    public List<Warehouse> findAll() {
        List<Warehouse> warehouses = new ArrayList<>();
        warehouseRepository.findAll().forEach(warehouses::add);
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

    @Override
    public void save(Warehouse wh) {
        warehouseRepository.save(wh);
    }


}
