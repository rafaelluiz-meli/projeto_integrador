package com.mercadolivre.bootcamp.projeto_integrador.controller;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.exception.WarehouseDoesntExistException;
import com.mercadolivre.bootcamp.projeto_integrador.service.WarehouseService;
import com.mercadolivre.bootcamp.projeto_integrador.service.WarehouseServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WarehouseController {
    private final WarehouseServiceImpl warehouseService;

    @PostMapping("/warehouse")
    public ResponseEntity<?> createWarehouse(@RequestBody String name) {
        Warehouse wh = new Warehouse(name);
        Warehouse finalWh = warehouseService.save(wh);
        return ResponseEntity.ok().body(finalWh);
    }

    @DeleteMapping("/warehouse")
    public ResponseEntity<?> deleteWarehouse(@RequestBody String id) {
        try{
            warehouseService.delete(id);
            return ResponseEntity.ok().body("Deleted with Success");
        }catch(WarehouseDoesntExistException ex){
            return ResponseEntity.badRequest().body("Id not found");
        }

    }
}
