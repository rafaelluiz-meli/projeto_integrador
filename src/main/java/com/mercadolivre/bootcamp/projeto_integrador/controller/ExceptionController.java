package com.mercadolivre.bootcamp.projeto_integrador.controller;

import com.mercadolivre.bootcamp.projeto_integrador.dto.ErrorDTO;
import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.ZonedDateTime;

/**
 * This class represents Exception controller
 */
@RestControllerAdvice
public class ExceptionController {

    /**
     *
     * @param e represents the exception
     * @return e.HttpStatus()
     */
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> handleGlobalExceptions(BaseException e){
        return new ResponseEntity<>(e.getErrorDTO(), e.getHttpStatus());
    }

    /**
     *
     * @param e represents the exception
     * @return 400 BAD REQUEST
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException e){
        ErrorDTO errorDTO = new ErrorDTO(e.getClass().getSimpleName(), e.getFieldError().getDefaultMessage(), ZonedDateTime.now());
        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }
}
