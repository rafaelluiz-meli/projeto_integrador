package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.exception.RepresentativeNotFoundException;
import com.mercadolivre.bootcamp.projeto_integrador.repository.RepresentativeRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class RepresentativeServiceImpl implements RepresentativeService {

    private RepresentativeRepository representativeRepository;

    @Override
    public List<Representative> getAllRepresentatives() {

        List<Representative> representativeList = new ArrayList<>();

        representativeList = representativeRepository.findAll();

        return representativeList;
    }

    @Override
    public Representative getRepresentativeById(String representativeId) {
        Representative representative = null;

        representative = representativeRepository.findById(representativeId)
                .orElseThrow(() -> new RepresentativeNotFoundException(representativeId));

        return representative;
    }

    @Override
    public Representative createRepresentative(Representative representative) {
        return representativeRepository.save(representative);
    }

    @Override
    public Representative updateRepresentative(String representativeId, Representative representative) {

        Representative representativeAux = getRepresentativeById(representativeId);

        representativeAux.setFullName(representative.getFullName());
        representativeAux.setSectionId(representative.getSectionId());

        return representativeRepository.save(representativeAux);

    }

    @Override
    public void deleteRepresentative(String representativeId) {
        Representative representative = getRepresentativeById(representativeId);
        representativeRepository.delete(representative);

    }


    public boolean isRepresentativeAssociatedWithSection(String representativeId, String sectionId) {
        Representative representative = getRepresentativeById(representativeId);
        boolean representativeIsAssociated = false;
        representativeIsAssociated = representative.getSectionId().equals(sectionId);
        return representativeIsAssociated;
    }

}