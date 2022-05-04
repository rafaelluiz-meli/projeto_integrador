package com.mercadolivre.bootcamp.projeto_integrador.exception.inbound_order;

import com.mercadolivre.bootcamp.projeto_integrador.entity.Category;
import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class SectionNotAppropriateForProductException extends BaseException {

    public SectionNotAppropriateForProductException(Long sectionId, Category category) {
        super("Produto com categoria: " + category + " não pode ser armazenado em sessão ID: " + sectionId, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
    }
}
