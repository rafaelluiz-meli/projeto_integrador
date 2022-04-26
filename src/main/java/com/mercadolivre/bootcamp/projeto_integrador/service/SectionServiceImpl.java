package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import com.mercadolivre.bootcamp.projeto_integrador.exception.SectionNotFound;
import com.mercadolivre.bootcamp.projeto_integrador.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements ISectionService {

    private final SectionRepository sectionRepository;

    @Override
    public Section addSection(NewSectionDTO sectionDTO) {
        Section savedSectionDTO = sectionRepository.save(NewSectionDTO.convert(sectionDTO));
        return savedSectionDTO;
    }

    @Override
    public List<Section> getAllSection() {
        List<Section> listSections = sectionRepository.findAll();
        return listSections;
    }

    @Override
    public Section getSectionById(String sectionId) {
        Section getSectionById = sectionRepository.findById(sectionId).orElseThrow(() ->
                new SectionNotFound("Id da secao não é valido", HttpStatus.NOT_FOUND, ZonedDateTime.now()));
        return getSectionById;
    }

    @Override
    public void deleteSection(String sectionId) {
        try {
            sectionRepository.deleteById(sectionId);
        } catch (EmptyResultDataAccessException e) {
            new SectionNotFound("Id da secao não foi encontrado!", HttpStatus.NOT_FOUND, ZonedDateTime.now());
        }
    }

    @Override
    public Section updateSection(Section section) {
        Section getSectionId = sectionRepository.findById(section.getSectionId()).orElseThrow(() -> new SectionNotFound("Id da secao não é valido", HttpStatus.NOT_FOUND, ZonedDateTime.now()));

        getSectionId.setCapacity(section.getCapacity());
        getSectionId.setCategory(section.getCategory());
        getSectionId.setCapacity(section.getCapacity());
        getSectionId.setListInBoundOrder(section.getListInBoundOrder());
        getSectionId.setWarehouseId(section.getWarehouseId());


        return sectionRepository.save(getSectionId);
    }


    @Override
    public boolean isSectionValid(String sectionID) {

        if (!sectionID.isBlank() && !sectionID.isEmpty()) {
            Optional<Section> sectionOptional = sectionRepository.findById(sectionID);

            if (sectionOptional.isPresent()) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public boolean availableSectionCapacity(BigDecimal totalVolume, String sectionId) {

        Section getSection = getSectionById(sectionId);

        if (getSection.getCapacity().compareTo(totalVolume) == 0 || getSection.getCapacity().compareTo(totalVolume) < 0) {
            return false;
        }

        return true;
    }


}
