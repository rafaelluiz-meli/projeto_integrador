package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class SampleException extends BaseException{
    public SampleException(String districtName) {
        super("O bairro " + districtName + " já está cadastrado.", HttpStatus.CONFLICT, ZonedDateTime.now());
    }
}
