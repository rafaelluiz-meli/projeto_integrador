package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class SalesmanListIsEmptyException extends BaseException{
    public SalesmanListIsEmptyException() {
        super("Não foi encontrado nenhum vendedor.", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
