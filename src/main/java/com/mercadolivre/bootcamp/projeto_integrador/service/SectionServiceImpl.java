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

/**
 * This class is the service implementation of section entity.
 */
@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final WarehouseRepository warehouseRepository;


    /**
     * Is persistence in database by repository
     * @param section
     * @return a section object
     */
    @Override
    public Section addSection(Section section) {
        return sectionRepository.save(section);
    }


    /**
     * Get from database all sections.
     * @return a section list.
     * @exception EmptyListException if section list is empty.
     */
    @Override
    public List<Section> getAllSection() {
        List<Section> sectionList = sectionRepository.findAll();
        if(sectionList.isEmpty()) throw new EmptyListException();
        return sectionList;
    }

    /**
     * Verify that warehouse is valid.
     * @return true if warehouse exists or false if not exists.
     * @exception IdNotFoundException if warehouse id is not exists.
     */
    public boolean isWarehouseValid(Long warehouseId) {
        Optional<Warehouse> warehouse = warehouseRepository.findById(warehouseId);
        if (warehouse.isPresent()){
            return true;
        }
        throw new IdNotFoundException(warehouseId);
    }

    /**
     * Get section list filtered by warehouse id
     * @param warehouseId
     * @return section list
     * @exception IdNotFoundException if warehouse id is not exists.
     */
    @Override
    public List<Section> getAllSectionByWarehouseId(Long warehouseId) {
        if (isWarehouseValid(warehouseId)){
            return sectionRepository.findAllByWarehouseId(warehouseId);
        } else {
            throw new IdNotFoundException(warehouseId);
        }
    }

    /**
     * Get section list filtered by sectionId
     * @param sectionId
     * @return
     * @exception IdNotFoundException if section id is not exists.
     */
    @Override
    public Section getSectionById(Long sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(() ->
                new IdNotFoundException(sectionId));
    }

     /**
     * Check if section id exists with {@link #getSectionById(Long)}  getSectionById} method. <br>
     * If exists then remove the section.
     * @param sectionId
     * @return void.
     * @exception IdNotFoundException if section id not found.
     */
    @Override
    public void deleteSection(Long sectionId) {
        try {
            sectionRepository.delete(getSectionById(sectionId));
        } catch (EmptyResultDataAccessException e) {
            throw new IdNotFoundException(sectionId);
        }
    }

    /**
     * Check if section id exists with {@link #getSectionById(Long)}  getSectionId} method. <br>
     * If exists then update section attributes.
     * @param section
     * @return a updated section
     *
     */
    @Override
    public Section updateSection(Section section) {
        Section getSectionId = getSectionById(section.getSectionId());

        getSectionId.setCapacity(section.getCapacity());
        getSectionId.setCategory(section.getCategory());
        getSectionId.setCapacity(section.getCapacity());
        getSectionId.setWarehouseId(section.getWarehouseId());

        return sectionRepository.save(getSectionId);
    }

    /**
     * Verify that section is valid.
     * @return true if section exists or false if not exists.
     * @exception IdNotFoundException if section id is not exists.
     */
    @Override
    public boolean isSectionValid(Long sectionID) throws IdNotFoundException {
        sectionRepository.findById(sectionID).orElseThrow(() -> new IdNotFoundException(sectionID));
        return true;
    }

    /**
     * Verify if exist available capacity in section
     * @param totalVolume
     * @param sectionId
     * @return true if exist capacity or false if not exist.
     * @exception  SectionDoesNotHaveEnoughCapacityException if section not have capacity.
     */
    @Override
    public boolean availableSectionCapacity(BigDecimal totalVolume, Long sectionId) throws SectionDoesNotHaveEnoughCapacityException {
        Section getSection = getSectionById(sectionId);
        if (getSection.getCapacity().compareTo(totalVolume) < 0) throw new SectionDoesNotHaveEnoughCapacityException(sectionId, totalVolume);
        return true;
    }


    /**
     *
     * Verify if section corresponds product type.
     * @param sectionId
     * @param category
     * @return true if section corresponds product type.
     * @exception  IdNotFoundException if section id not exists.
     * @exception SectionNotAppropriateForProductException if section not corresponds product type.
     */
    @Override
    public boolean sectionCorrespondsProductType(Long sectionId, Category category) throws IdNotFoundException {
        Section getSection = sectionRepository.findById(sectionId).orElseThrow(
                () -> new IdNotFoundException(sectionId));
        if (!getSection.getCategory().equals(category)) throw new SectionNotAppropriateForProductException(sectionId, category);
        return true;
    }
}
