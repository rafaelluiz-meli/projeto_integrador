package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;

public class InBoundOrderIdNotFoundException extends BaseException{

    public InBoundOrderIdNotFoundException(Long inBoundOrderNumber){
        super("O id " + inBoundOrderNumber + " não foi encontrado.", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
