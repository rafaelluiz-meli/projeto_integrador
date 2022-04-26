package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class WarehouseAlreadyRegisteredException extends BaseException {
    public WarehouseJaCadastradoException(String warehouseId) {
        super("O Warehouse " + warehouseId + " já está cadastrado.", HttpStatus.CONFLICT, ZonedDateTime.now());
    }
}
