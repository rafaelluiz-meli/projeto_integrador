package com.mercadolivre.bootcamp.projeto_integrador.exception.warehouse;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class WarehouseAlreadyRegisteredException extends BaseException {
    public WarehouseAlreadyRegisteredException(Long warehouseId) {
        super("O Armazém " + warehouseId + " já está criado.", HttpStatus.CONFLICT, ZonedDateTime.now());
    }
}
