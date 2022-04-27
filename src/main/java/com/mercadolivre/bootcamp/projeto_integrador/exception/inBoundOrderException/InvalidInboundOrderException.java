package com.mercadolivre.bootcamp.projeto_integrador.exception.inBoundOrderException;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class InvalidInboundOrderException extends BaseException {

    public InvalidInboundOrderException() {

        super("InBoundOrder is not valid.", HttpStatus.BAD_REQUEST, ZonedDateTime.now());
    }
}
