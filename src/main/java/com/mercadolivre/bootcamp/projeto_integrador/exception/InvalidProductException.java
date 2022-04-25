package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class InvalidProductException extends BaseException {

    public InvalidProductException(String productId) {
        super("Não foi possível encontrar um produto com id: " + productId, HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}