package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.salesman.NewSalesmanDTO;
import com.mercadolivre.bootcamp.projeto_integrador.dto.salesman.UpdateSalesmanDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import com.mercadolivre.bootcamp.projeto_integrador.service.SalesmanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/fresh-products/salesman")
public class SalesmanController {

    private final SalesmanService salesmanService;

    @PostMapping()
    public ResponseEntity<NewSalesmanDTO> createSalesman(@RequestBody Salesman salesman) {
        salesmanService.createSalesman(salesman);
        return ResponseEntity.status(HttpStatus.CREATED).body(NewSalesmanDTO.map(salesman));
    }

    @PutMapping()
    public ResponseEntity<UpdateSalesmanDTO> updateSalesman(@RequestBody UpdateSalesmanDTO updateSalesmanDTO) {
        Salesman salesman = updateSalesmanDTO.map();
        Salesman updateSalesman = salesmanService.updateSalesman(salesman);
        return ResponseEntity.ok(updateSalesmanDTO.map(updateSalesman));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteSalesman(@PathVariable(value = "id") Long salesmanId) {
        salesmanService.removeSalesman(salesmanId);
        return ResponseEntity.ok(salesmanId);
    }
}

