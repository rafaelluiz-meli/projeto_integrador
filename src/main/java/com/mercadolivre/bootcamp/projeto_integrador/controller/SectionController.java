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

/**
 * This class represents the Section controller
 */
@AllArgsConstructor
@RestController
@RequestMapping("api/v1/fresh-products")
public class SectionController {
    private final SectionService sectionService;

    /**
     *
     * @param sectionId Id of the section to found the section in database.
     * @return 200 OK
     */
    @GetMapping("/section")
    public ResponseEntity<NewSectionDTO> getSectionById(@RequestParam Long sectionId){
        Section sectionExistente = sectionService.getSectionById(sectionId);
        NewSectionDTO result = NewSectionDTO.convert(sectionExistente);
        return ResponseEntity.ok(result);
    }

    /**
     *
     * @param warehouseId warehouse id to found all sections in this warehouse
     * @return 200 OK
     */
    @GetMapping("/section/warehouse")
    public ResponseEntity<List<Section>> getSectionsByWarehouseById(@RequestParam Long warehouseId){
        List<Section> allSectionsByWarehouseId = sectionService.getAllSectionByWarehouseId(warehouseId);
        return ResponseEntity.ok(allSectionsByWarehouseId);
    }

    /**
     *
     * @param newSectionDTO Dto in request body to create a new section
     * @param uriComponentsBuilder uri to insert in response entity
     * @return 201 CREATED or 400 BAD REQUEST
     */
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

    /**
     *
     * @param sectionId Id of the section to remove from database.
     * @return 200 OK or 400 BAD REQUEST
     */
    @DeleteMapping("/section/{sectionId}")
    public ResponseEntity deleteSection (@PathVariable Long sectionId) {
        try{
            sectionService.deleteSection(sectionId);
            return ResponseEntity.ok().body("a seção foi excluída.");
        }catch (IdNotFoundException ex){
            return ResponseEntity.badRequest().body("A seção informada não foi encontrada.");
        }
    }

    /**
     *
     * @param section Section object to be updated.
     * @return 200 OK
     */
    @PutMapping("/section")
    public ResponseEntity<Section> putSection (@RequestBody Section section){
        Section updatedSection = sectionService.updateSection(section);
        return ResponseEntity.ok(updatedSection);
    }

}
