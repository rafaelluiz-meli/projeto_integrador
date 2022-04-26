package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;
import com.mercadolivre.bootcamp.projeto_integrador.repository.RepresentativeRepository;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class RepresentativeServiceImpl implements RepresentativeService {

    private RepresentativeRepository representativeRepository;

    @Override
    public List<Representative> getAllRepresentatives() {

        List<Representative> representativeList = new ArrayList<Representative>();

        representativeList = representativeRepository.findAll();

        return representativeList;
    }

    @Override
    public Representative getRepresentativeById(String representativeId) {
        Representative representative = null;

        representative = representativeRepository.findById(representativeId)
                .orElseThrow();

        return representative;
    }

    @Override
    public Representative createRepresentative(Representative representative) {
        representativeRepository.save(representative);
        return representative;


    }

    @Override
    public Representative updateRepresentative(String representativeId, Representative representative) {



        Representative representativeAux = representativeRepository.findById(representativeId)
                .orElseThrow();

        representativeAux.setFullName(representative.getFullName());
        representativeAux.setSectionId(representative.getSectionId());

        representativeRepository.save(representativeAux);


        return representativeAux;

    }

    @Override
    public void deleteRepresentative(String representativeId) {
        Representative representative = representativeRepository.findById(representativeId).orElseThrow();
        representativeRepository.delete(representative);

    }

    public boolean isRepresentativeAssociatedWithSection(String representativeId, String sectionId) {
        Representative representative = this.getRepresentativeById(representativeId);
        boolean representativeIsAssociated = false;
        if (representative != null) {
            representativeIsAssociated = representative.getSectionId().equals(sectionId);
        }
        return representativeIsAssociated;
    }
}
