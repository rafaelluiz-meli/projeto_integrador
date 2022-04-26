package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class SectionNotFound extends BaseException{

    public SectionNotFound(String message) {
        super("A section com ID: " + message, HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
