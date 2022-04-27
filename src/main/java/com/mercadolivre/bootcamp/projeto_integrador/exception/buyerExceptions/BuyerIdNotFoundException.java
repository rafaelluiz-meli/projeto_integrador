package com.mercadolivre.bootcamp.projeto_integrador.exception.buyerExceptions;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class BuyerIdNotFoundException extends BaseException {
    public BuyerIdNotFoundException(Long buyerId) {
        super("O id "+ buyerId + " não foi encontrado.", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
