package com.mercadolivre.bootcamp.projeto_integrador.exception.batch_stock;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class orderByNotValidException extends BaseException {
    public orderByNotValidException(String orderBy) {
        super("Não é possível ordernar por: "+ orderBy, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
    }
}
