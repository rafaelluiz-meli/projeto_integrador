package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.section.NewSectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.service.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/fresh-products")
public class SectionController {
    private final SectionService sectionService;

    @GetMapping("/section")
    public ResponseEntity<NewSectionDTO> getSectionById(@RequestParam Long sectionId){
        Section sectionExistente = sectionService.getSectionById(sectionId);
        NewSectionDTO result = NewSectionDTO.convert(sectionExistente);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/section/warehouse")
    public ResponseEntity<List<Section>> getSectionsByWarehouseById(@RequestParam Long warehouseId){
        List<Section> allSectionsByWarehouseId = sectionService.getAllSectionByWarehouseId(warehouseId);
        return ResponseEntity.ok(allSectionsByWarehouseId);
    }

    @PostMapping("/section")
    public ResponseEntity<NewSectionDTO> creatingSection(@RequestBody NewSectionDTO newSectionDTO, UriComponentsBuilder uriComponentsBuilder){
        Section section = NewSectionDTO.convert(newSectionDTO);
        if (sectionService.isWarehouseValid(section.getWarehouseId())) {
            section = sectionService.addSection(section);
            URI uri = uriComponentsBuilder
                    .path("/section/{id}")
                    .buildAndExpand(section.getSectionId())
                    .toUri();

            NewSectionDTO result = NewSectionDTO.convert(section);
            return ResponseEntity.created(uri).body(result);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/section/{sectionId}")
    public ResponseEntity deleteSection (@PathVariable Long sectionId) {
        try{
            sectionService.deleteSection(sectionId);
            return ResponseEntity.ok().body("a seção foi excluída.");
        }catch (IdNotFoundException ex){
            return ResponseEntity.badRequest().body("A seção informada não foi encontrada.");
        }
    }

    @PutMapping("/section")
    public ResponseEntity<Section> putSection (@RequestBody Section section){
        Section updatedSection = sectionService.updateSection(section);
        return ResponseEntity.ok(updatedSection);
    }

}
