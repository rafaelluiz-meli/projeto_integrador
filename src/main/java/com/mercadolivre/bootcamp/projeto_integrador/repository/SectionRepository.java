package com.mercadolivre.bootcamp.projeto_integrador.repository;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface SectionRepository extends JpaRepository<Section, Long> {
}
