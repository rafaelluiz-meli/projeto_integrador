package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.EmptyListException;
import com.mercadolivre.bootcamp.projeto_integrador.exception.generics.IdNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.RepresentativeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@AllArgsConstructor
public class RepresentativeServiceImpl implements RepresentativeService {

    private final RepresentativeRepository representativeRepository;

    @Override
    public List<Representative> getAllRepresentatives() {
        List<Representative> representativeList = representativeRepository.findAll();
        if(representativeList.isEmpty()) throw new EmptyListException();
        return representativeList;
    }

    @Override
    public Representative getRepresentativeById(Long representativeId) {
        return representativeRepository
                .findById(representativeId)
                .orElseThrow(() -> new IdNotFoundException(representativeId));
    }

    @Override
    public Representative createRepresentative(Representative representative) {
        return representativeRepository.save(representative);
    }

    @Override
    public Representative updateRepresentative(Representative representative) {
        Representative updateRepresentative = getRepresentativeById(representative.getId());
        updateRepresentative.setFullName(representative.getFullName());
        updateRepresentative.setSectionId(representative.getSectionId());
        return representativeRepository.save(updateRepresentative);
    }

    @Override
    public void deleteRepresentative(Long representativeId) {
        representativeRepository.delete(getRepresentativeById((representativeId)));
    }

    public boolean isRepresentativeAssociatedWithSection(Long representativeId, Long sectionId) {
        Representative representative = getRepresentativeById(representativeId);
        boolean representativeIsAssociated = false;
        representativeIsAssociated = representative.getSectionId().equals(sectionId);
        return representativeIsAssociated;
    }
}
