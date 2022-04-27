package com.mercadolivre.bootcamp.projeto_integrador.exception.buyerExceptions;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class BuyerListEmptyException extends BaseException {
    public BuyerListEmptyException() {
        super("Não foi encontrado nenhum item.", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
