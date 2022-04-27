package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.service.SectionService;
import com.mercadolivre.bootcamp.projeto_integrador.service.SectionServiceImpl;
import com.mercadolivre.bootcamp.projeto_integrador.service.WarehouseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/fresh-products/section")
public class SectionController {
    private final SectionService sectionService;
    private final WarehouseService warehouseService;

    @GetMapping("")
    public ResponseEntity<NewSectionDTO> obter(@PathVariable String id){
        Section sectionExistente = sectionService.getSectionById(id);
        NewSectionDTO result = NewSectionDTO.convert(sectionExistente);
        return ResponseEntity.ok(result);
    }

    @GetMapping("")
    public ResponseEntity<List<Section>> getSectionsByWarehouseById(@RequestParam String warehouseId){
        List<Section> allSectionsByWarehouseId = sectionService.getAllSectionByWarehouseId(warehouseId);
        return ResponseEntity.ok(allSectionsByWarehouseId);
    }

    @PostMapping("")
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


}
