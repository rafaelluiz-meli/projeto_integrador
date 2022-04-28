package com.mercadolivre.bootcamp.projeto_integrador.exception.warehouseExceptions;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;


public class NoWarehouseCreatedException extends BaseException {
    public NoWarehouseCreatedException() {
        super("No Warehouse Created.", HttpStatus.CONFLICT, ZonedDateTime.now());
    }
}
