package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.dto.section.NewSectionDTO;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import com.mercadolivre.bootcamp.projeto_integrador.entity.Warehouse;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;

import com.mercadolivre.bootcamp.projeto_integrador.exception.inbound_order.SectionDoesNotHaveEnoughCapacityException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.inbound_order.SectionNotAppropriateForProductException;

import com.mercadolivre.bootcamp.projeto_integrador.repository.SectionRepository;
import com.mercadolivre.bootcamp.projeto_integrador.repository.WarehouseRepository;
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
    private final WarehouseRepository warehouseRepository;

    @Override
    public Section addSection(Section section) {
        return sectionRepository.save(section);
    }

    @Override
    public List<Section> getAllSection() {
        List<Section> sectionList = sectionRepository.findAll();
        if(sectionList.isEmpty()) throw new EmptyListException();
        return sectionList;
    }

    public boolean isWarehouseValid(Long warehouseId) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseId);
        if (warehouse.isPresent()){
            return true;
        }
        throw new IdNotFoundException(warehouseId);
    }

    @Override
    public List<Section> getAllSectionByWarehouseId(Long warehouseId) {
        if (isWarehouseValid(warehouseId)){
            return sectionRepository.findAllByWarehouseId(warehouseId);
        } else {
            throw new IdNotFoundException(warehouseId);
        }
    }

    @Override
    public Section getSectionById(Long sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(() ->
                new IdNotFoundException(sectionId));
    }

    @Override
    public void deleteSection(Long sectionId) {
        try {
            sectionRepository.delete(getSectionById(sectionId));
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
        getSectionId.setWarehouseId(section.getWarehouseId());

        return sectionRepository.save(getSectionId);
    }

    @Override
    public boolean isSectionValid(Long sectionID) throws IdNotFoundException {
        sectionRepository.findById(sectionID).orElseThrow(() -> new IdNotFoundException(sectionID));
        return true;
    }

    @Override
    public boolean availableSectionCapacity(BigDecimal totalVolume, Long sectionId) throws SectionDoesNotHaveEnoughCapacityException {
        Section getSection = getSectionById(sectionId);
        if (getSection.getCapacity().compareTo(totalVolume) < 0) throw new SectionDoesNotHaveEnoughCapacityException(sectionId, totalVolume);
        return true;
    }

    @Override
    public boolean sectionCorrespondsProductType(Long sectionId, Category category) throws IdNotFoundException {
        Section getSection = sectionRepository.findById(sectionId).orElseThrow(
                () -> new IdNotFoundException(sectionId));
        if (!getSection.getCategory().equals(category)) throw new SectionNotAppropriateForProductException(sectionId, category);
        return true;
    }
}
