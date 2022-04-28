package com.mercadolivre.bootcamp.projeto_integrador.exception.warehouseExceptions;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class WarehouseDoesntExistException extends BaseException {
    public WarehouseDoesntExistException(String warehouseId) {
        super("Warehouse with id" + warehouseId + " does not exist.", HttpStatus.CONFLICT, ZonedDateTime.now());
    }
}
