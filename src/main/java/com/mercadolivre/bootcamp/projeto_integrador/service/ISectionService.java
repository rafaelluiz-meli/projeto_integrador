package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;

import java.math.BigDecimal;
import java.util.List;

public interface ISectionService {

    Section addSection(NewSectionDTO sectionDTO);
    List<Section> getAllSection();
    Section getSectionById(String sectionId);
    void deleteSection(String sectionId);
    Section updateSection(Section section);
    boolean isSectionValid(String sectionID);
    boolean availableSectionCapacity(BigDecimal totalVolume, String sectionId);


}
