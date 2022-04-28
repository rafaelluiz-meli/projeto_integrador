package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.NewSectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import com.mercadolivre.bootcamp.projeto_integrador.exception.SectionNotFound;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.SectionRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Override
    public Section addSection(NewSectionDTO sectionDTO) {
        return sectionRepository.save(NewSectionDTO.convert(sectionDTO));
    }

    @Override
    public List<Section> getAllSection() {
        List<Section> sectionList = sectionRepository.findAll();
        if(sectionList.isEmpty()) throw new EmptyListException();
        return sectionList;
    }

    @Override
    public Section getSectionById(Long sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(() ->
                new IdNotFoundException(sectionId));
    }

    @Override
    public void deleteSection(Long sectionId) {
        try {
            sectionRepository.deleteById(sectionId);
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException(sectionId);
        }
    }

    @Override
    public Section updateSection(Section section) {
        Section getSectionId = getSectionById(section.getSectionId());

        getSectionId.setCapacity(section.getCapacity());
        getSectionId.setCategory(section.getCategory());
        getSectionId.setCapacity(section.getCapacity());
        getSectionId.setListInBoundOrder(section.getListInBoundOrder());
        getSectionId.setWarehouseId(section.getWarehouseId());

        return sectionRepository.save(getSectionId);
    }

    @Override
    public boolean isSectionValid(Long sectionID) {
            Optional<Section> sectionOptional = sectionRepository.findById(sectionID);

        return sectionOptional.isPresent();
    }

    @Override
    public boolean availableSectionCapacity(BigDecimal totalVolume, Long sectionId) {

        Section getSection = getSectionById(sectionId);

        return getSection.getCapacity().compareTo(totalVolume) != 0 && getSection.getCapacity().compareTo(totalVolume) >= 0;
    }

    @Override
    public boolean sectionCorrespondsProductType(Long sectionId, Category category) {
        Section getSection = sectionRepository.findById(sectionId).orElseThrow(
                () -> new SectionNotFound(sectionId));

        return getSection.getCategory().equals(category);
    }
}
