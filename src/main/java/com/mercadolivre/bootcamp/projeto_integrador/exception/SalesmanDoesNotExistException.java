package com.mercadolivre.bootcamp.projeto_integrador.exception;

public class SalesmanDoesNotExistException extends RuntimeException {
    public SalesmanDoesNotExistException(String message) {
        super(message);
    }
}
