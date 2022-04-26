package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.service.RepresentativeServiceImpl;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class RepresentativeController {
    RepresentativeServiceImpl representativeServiceImpl;

    @GetMapping("/representatives")
    public List<Representative> getAllRepresentatives() {
        return representativeServiceImpl.getAllRepresentatives();
    }

    @GetMapping("/representatives/{id}")
    public Representative getByRepresentativeId(@PathVariable(value = "id") String representativeId) {
        return representativeServiceImpl.getRepresentativeById(representativeId);
    }

    @PostMapping("/representatives")
    public Representative createRepresentative(@Valid @RequestBody Representative representative) {
        return representativeServiceImpl.createRepresentative(representative);
    }

    @PutMapping("/representative/{id}")
    public Representative updateRepresentative(@PathVariable(value = "id") String representativeId, @Valid @RequestBody Representative representative) {
        return representativeServiceImpl.updateRepresentative(representativeId, representative);
    }

    @DeleteMapping("/representative/{id}")
    public void deleteRepresentative(@PathVariable(value = "id") String representativeId) {
        representativeServiceImpl.deleteRepresentative(representativeId);
    }

}


