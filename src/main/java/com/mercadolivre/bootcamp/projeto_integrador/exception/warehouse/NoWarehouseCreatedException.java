package com.mercadolivre.bootcamp.projeto_integrador.exception.warehouse;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class NoWarehouseCreatedException extends BaseException {
    public NoWarehouseCreatedException() {
        super("Nenhum Armazém criado.", HttpStatus.CONFLICT, ZonedDateTime.now());
    }
}
