package com.mercadolivre.bootcamp.projeto_integrador.exception.batchStockException;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class BatchStockIdNotFoundException extends BaseException {

    public BatchStockIdNotFoundException(Long id) {
        super("O id " + id + " não foi encontrado.", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
