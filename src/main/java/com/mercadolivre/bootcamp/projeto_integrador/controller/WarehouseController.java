package com.mercadolivre.bootcamp.projeto_integrador.controller;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@AllArgsConstructor
@RestController
public class WarehouseController {
    private final WarehouseService warehouseService;
    @RequestMapping("api/v1/fresh-products/warehouse")


    @PostMapping()
    public ResponseEntity createWarehouse(@RequestBody String name) {
        Warehouse wh = new Warehouse(name);
        Warehouse finalWh = warehouseService.save(wh);
        return new ResponseEntity(finalWh, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteWarehouse(@PathVariable(value = "id") Long id) {
        try{
            warehouseService.delete(id);
            return ResponseEntity.ok().body("Deleted with Success");
        }catch(IdNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity getWarehouse(@PathVariable(value = "id") Long id) {
        Warehouse wh = warehouseService.findById(id);
        Warehouse finalWh = warehouseService.save(wh);
        return ResponseEntity.ok().body(finalWh);
    }
}
