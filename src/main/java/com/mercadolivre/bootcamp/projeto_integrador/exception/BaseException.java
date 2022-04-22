package com.mercadolivre.bootcamp.projeto_integrador.exception;

import com.mercadolivre.bootcamp.projeto_integrador.dto.ErrorDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
public class BaseException extends RuntimeException {
    private final ErrorDTO errorDTO;
    private final HttpStatus httpStatus;

    public BaseException(String message, HttpStatus httpStatus, ZonedDateTime timestamp){
        this.errorDTO = new ErrorDTO(this.getClass().getSimpleName(), message, timestamp);
        this.httpStatus = httpStatus;
    }
}
