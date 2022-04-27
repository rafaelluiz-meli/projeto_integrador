package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class PurchaseOrderIdNotFoundException extends BaseException{

    public PurchaseOrderIdNotFoundException(Long id) {
        super("O id " + id + " da ordem de compra não foi encontrado.", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
