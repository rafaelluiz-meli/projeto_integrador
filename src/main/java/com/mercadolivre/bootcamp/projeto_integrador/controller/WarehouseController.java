package com.mercadolivre.bootcamp.projeto_integrador.controller;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This classe represents the Warehouse controller
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/fresh-products/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;

    /**
     *
     * @param name Name to create a new Warehouse
     * @return 200 OK
     */
    @PostMapping()
    public ResponseEntity createWarehouse(@RequestBody String name) {
        Warehouse wh = new Warehouse(name);
        Warehouse finalWh = warehouseService.save(wh);
        return new ResponseEntity(finalWh, HttpStatus.CREATED);
    }

    /**
     *
     * @param warehouseId Id of warehouse to be removed.
     * @return 200 OK or 404 NOT FOUND
     */
    @DeleteMapping("/{warehouseId}")
    public ResponseEntity deleteWarehouse(@PathVariable Long warehouseId) {
        try{
            warehouseService.delete(warehouseId);
            return ResponseEntity.ok().body("Deleted with Success");
        }catch(IdNotFoundException ex){
            return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
        }

    }

    /**
     *
     * @param warehouseId Id of the Warehouse to get a Warehouse of the database.
     * @return 200 OK
     */
    @GetMapping()
    public ResponseEntity getWarehouse(@RequestParam Long warehouseId) {
        Warehouse wh = warehouseService.findById(warehouseId);
        return ResponseEntity.ok().body(wh);
    }
}
