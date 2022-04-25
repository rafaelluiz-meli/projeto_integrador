package com.mercadolivre.bootcamp.projeto_integrador.exception;

import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class WarehouseNaoExisteException extends BaseException{
    public WarehouseNaoExisteException(String warehouseId) {
        super("O Warehouse com id" + warehouseId + " Não existe.", HttpStatus.CONFLICT, ZonedDateTime.now());
    }
}
