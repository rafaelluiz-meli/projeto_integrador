package com.mercadolivre.bootcamp.projeto_integrador.controller;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.exception.WarehouseDoesntExistException;
import com.mercadolivre.bootcamp.projeto_integrador.service.WarehouseService;
import com.mercadolivre.bootcamp.projeto_integrador.service.WarehouseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@AllArgsConstructor
@RestController
public class WarehouseController {
    private WarehouseService warehouseService;
    @RequestMapping("api/v1/fresh-products/warehouse")


    @PostMapping()
    public ResponseEntity<?> createWarehouse(@RequestBody String name) {
        Warehouse wh = new Warehouse(name);
        Warehouse finalWh = warehouseService.save(wh);
        return new ResponseEntity(finalWh, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteWarehouse(@PathVariable(value = "name") Long id) {
        try{
            warehouseService.delete(id);
            return ResponseEntity.ok().body("Deleted with Success");
        }catch(WarehouseDoesntExistException ex){
            return ResponseEntity.badRequest().body("Id not found");
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWarehouse(@PathVariable(value = "name") Long id) {
        Warehouse wh = warehouseService.findById(id);
        Warehouse finalWh = warehouseService.save(wh);
        return ResponseEntity.ok().body(finalWh);
    }
}
