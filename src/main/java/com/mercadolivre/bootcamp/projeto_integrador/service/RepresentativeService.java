package com.mercadolivre.bootcamp.projeto_integrador.service;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Representative;

import java.util.List;

public interface RepresentativeService {
    public List<Representative> getAllRepresentatives();

    public Representative getRepresentativeById(String representativeId);

    public Representative createRepresentative(Representative representative);

    public Representative updateRepresentative(String representativeId, Representative representative);

    public void deleteRepresentative(String representativeId);

    public boolean isRepresentativeAssociatedWithSection(String representativeId, String sectionId);

}
