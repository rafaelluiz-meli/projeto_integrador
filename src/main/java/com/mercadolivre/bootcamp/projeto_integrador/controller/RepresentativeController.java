package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.service.RepresentativeServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RepresentativeController {
    RepresentativeServiceImpl representativeServiceImpl;

    @GetMapping("/representatives")
    public ResponseEntity<?> getAllRepresentatives() {
        List<Representative> representativeList = representativeServiceImpl.getAllRepresentatives();
        return ResponseEntity.ok().body(representativeList);
    }

    @GetMapping("/representatives/{id}")
    public ResponseEntity<?> getByRepresentativeId(@PathVariable(value = "id") String representativeId) {
        Representative representative = representativeServiceImpl.getRepresentativeById(representativeId);
        return ResponseEntity.ok().body(representative);
    }

    @PostMapping("/representatives")
    public ResponseEntity<?> createRepresentative(@Valid @RequestBody Representative representative) {
        Representative representativeAux = representativeServiceImpl.createRepresentative(representative);
        return ResponseEntity.ok().body(representativeAux);
    }

    @PutMapping("/representative/{id}")
    public ResponseEntity<?> updateRepresentative(@PathVariable(value = "id") String representativeId, @Valid @RequestBody Representative representative) {
        Representative representativeAux = representativeServiceImpl.updateRepresentative(representativeId, representative);
        return ResponseEntity.ok().body(representativeAux);
    }

    @DeleteMapping("/representative/{id}")
    public ResponseEntity<?> deleteRepresentative(@PathVariable(value = "id") String representativeId) {
        representativeServiceImpl.deleteRepresentative(representativeId);
        return ResponseEntity.ok().body("success");
    }

}


