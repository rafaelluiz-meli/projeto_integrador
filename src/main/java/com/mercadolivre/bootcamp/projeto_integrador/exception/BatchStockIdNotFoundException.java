package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class BatchStockIdNotFoundException extends BaseException{

    public BatchStockIdNotFoundException(Long id) {
        super("The id " + id + " not found.", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
