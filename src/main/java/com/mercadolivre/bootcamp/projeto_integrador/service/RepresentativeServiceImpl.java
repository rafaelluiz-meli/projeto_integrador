package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.inbound_order.RepresentativeNotAssociatedWithSectionException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.RepresentativeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
/**
 * This class is the service implementation of representative entity.
 */
@Service
@AllArgsConstructor
public class RepresentativeServiceImpl implements RepresentativeService {

    private final RepresentativeRepository representativeRepository;

    /**
     * Get representatives list
     * @return a representatives list
     * @exception EmptyListException if representative list is empty.
     */
    @Override
    public List<Representative> getAllRepresentatives() {
        List<Representative> representativeList = representativeRepository.findAll();
        if(representativeList.isEmpty()) throw new EmptyListException();
        return representativeList;
    }

    /**
     * Get representative by id
     * @param representativeId
     * @return representative
     * @exception IdNotFoundException if id not exists
     */
    @Override
    public Representative getRepresentativeById(Long representativeId) {
        return representativeRepository
                .findById(representativeId)
                .orElseThrow(() -> new IdNotFoundException(representativeId));
    }

    /**
     * Is persistence in database by repository
     * @param representative
     * @return a representative object
     */
    @Override
    public Representative createRepresentative(Representative representative) {
        return representativeRepository.save(representative);
    }

    /**
     * Check if representative id exists with {@link #getRepresentativeById(Long)}  getRepresentativeById} method. <br>
     * If exists then update representative attributes.
     * @param representative
     * @return a updated representative
     *
     */
    @Override
    public Representative updateRepresentative(Representative representative) {
        Representative updateRepresentative = getRepresentativeById(representative.getId());
        updateRepresentative.setFullName(representative.getFullName());
        updateRepresentative.setSectionId(representative.getSectionId());
        return representativeRepository.save(updateRepresentative);
    }

    /**
     * Check if representative id exists with {@link #getRepresentativeById(Long)}  getRepresentativeById} method. <br>
     * If exists then remove.
     * @param representativeId
     * @return void
     */
    @Override
    public void deleteRepresentative(Long representativeId) {
        representativeRepository.delete(getRepresentativeById((representativeId)));
    }

    /**
     * Check if representative is associated with section
     * @param representativeId
     * @param sectionId
     * @return true if representative is associated with section
     * @exception RepresentativeNotAssociatedWithSectionException if representative id not is associated with section id
     */
    public boolean isRepresentativeAssociatedWithSection(Long representativeId, Long sectionId) {
        Representative representative = getRepresentativeById(representativeId);
        if(!representative.getSectionId().equals(sectionId)) throw new RepresentativeNotAssociatedWithSectionException(representativeId, sectionId);


        return true;
    }
}
