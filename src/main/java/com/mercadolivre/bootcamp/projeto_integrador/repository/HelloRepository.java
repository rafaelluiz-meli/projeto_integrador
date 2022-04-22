package com.mercadolivre.bootcamp.projeto_integrador.repository;


import com.mercadolivre.bootcamp.projeto_integrador.entity.Hello;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelloRepository extends JpaRepository<Hello, Long> {
}
