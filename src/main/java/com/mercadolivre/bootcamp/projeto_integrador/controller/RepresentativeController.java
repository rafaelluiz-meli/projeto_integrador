package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.service.RepresentativeService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
public class RepresentativeController {
    private final RepresentativeService representativeService;

    @GetMapping("/representatives")
    public ResponseEntity<List<Representative>> getAllRepresentatives() {
        List<Representative> representativeList = representativeService.getAllRepresentatives();
        return ResponseEntity.ok().body(representativeList);
    }

    @GetMapping("/representatives")
    public ResponseEntity<Representative> getByRepresentativeId(@RequestParam(value = "id") Long representativeId) {
        Representative representative = representativeService.getRepresentativeById(representativeId);
        return ResponseEntity.ok().body(representative);
    }

    @PostMapping("/representatives")
    public ResponseEntity<Representative> createRepresentative(@Valid @RequestBody Representative representative) {
        Representative representativeAux = representativeService.createRepresentative(representative);
        return ResponseEntity.ok().body(representativeAux);
    }

    @PutMapping("/representative")
    public ResponseEntity<Representative> updateRepresentative(@Valid @RequestBody Representative representative) {
        Representative representativeAux = representativeService.updateRepresentative(representative);
        return ResponseEntity.ok().body(representativeAux);
    }

    @DeleteMapping("/representative/{id}")
    public ResponseEntity<String> deleteRepresentative(@PathVariable(value = "id") Long representativeId) {
        representativeService.deleteRepresentative(representativeId);
        return ResponseEntity.ok().body("success");
    }
}
