package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import com.mercadolivre.bootcamp.projeto_integrador.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        Section getSectionById = sectionRepository.getById(sectionId);
        return getSectionById;
    }

    @Override
    public void deleteSection(String sectionId) {
        sectionRepository.deleteById(sectionId);
    }

    @Override
    public Section updateSection(String sectionId, Section section) {
        Optional<Section> getSectionId = sectionRepository.findById(sectionId);

        if (getSectionId.isPresent()) {

            Section sectionUpdate = new Section();

            sectionUpdate.setCapacity(section.getCapacity());
            sectionUpdate.setType(section.getType());
            sectionUpdate.setCategory(section.getCategory());
            sectionUpdate.setCapacity(section.getCapacity());
            sectionUpdate.setListInBoundOrder(section.getListInBoundOrder());
            sectionUpdate.setWarehouseId(section.getWarehouseId());

            return sectionUpdate;

        }

        throw new RuntimeException("Id não encontrado");

    }

    @Override
    public boolean isSectionValid(String sectionID) {

        if (!sectionID.isBlank() || !sectionID.isEmpty()) {
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

        if (getSection.getCapacity().compareTo(totalVolume) == 0) {
            return false;
        }

        return true;
    }


}
