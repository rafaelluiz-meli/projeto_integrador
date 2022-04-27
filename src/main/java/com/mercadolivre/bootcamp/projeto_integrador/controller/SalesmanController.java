package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSalesmanDto;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Salesman;
import com.mercadolivre.bootcamp.projeto_integrador.service.SalesmanService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping()
public class SalesmanController {

    private final SalesmanService salesmanService;

    @PostMapping("/salesman")
    public ResponseEntity<NewSalesmanDto> createSalesman(@Valid @RequestBody Salesman salesman) {
        salesmanService.createSalesman(salesman);
        return ResponseEntity.status(HttpStatus.CREATED).body(NewSalesmanDto.builder().build());
    }

    @PutMapping("/salesman/{id}")
    public ResponseEntity<Long> updateSalesman(@PathVariable(value = "id" ) Long salesmanId, @Valid @RequestBody Salesman salesman) {
        Salesman salesmanAux = salesmanService.updateSalesman(salesmanId, salesman);
        return ResponseEntity.ok().body(salesmanId);
    }

    @DeleteMapping("/salesman/{id}")
    public ResponseEntity<Long> deleteSalesman(@PathVariable(value = "id") Long salesmanId) {
        salesmanService.removeSalesman(salesmanId);
        return ResponseEntity.ok(salesmanId);
    }





}

