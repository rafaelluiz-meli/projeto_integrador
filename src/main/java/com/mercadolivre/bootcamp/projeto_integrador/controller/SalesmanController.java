package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.salesmanDTO.NewSalesmanDto;
import com.mercadolivre.bootcamp.projeto_integrador.dto.salesmanDTO.UpdateSalesmanDTO;
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

    @PostMapping("/addSalesman")
    public ResponseEntity<NewSalesmanDto> createSalesman(@RequestBody Salesman salesman) {
        salesmanService.createSalesman(salesman);
        return ResponseEntity.status(HttpStatus.CREATED).body(NewSalesmanDto.map(salesman));
    }

    @PutMapping("/updateSalesman")
    public ResponseEntity<UpdateSalesmanDTO> updateSalesman(@RequestBody UpdateSalesmanDTO updateSalesmanDTO) {
        Salesman salesman = updateSalesmanDTO.map();
        Salesman updateSalesman = salesmanService.updateSalesman(salesman);
        return ResponseEntity.ok(updateSalesmanDTO.map(updateSalesman));
    }

    @DeleteMapping("/{salesman}")
    public ResponseEntity<Long> deleteSalesman(@PathVariable(value = "salesman") Salesman salesman) {
        salesmanService.removeSalesman(salesman.getId());
        return ResponseEntity.ok(salesman.getId());
    }
}

