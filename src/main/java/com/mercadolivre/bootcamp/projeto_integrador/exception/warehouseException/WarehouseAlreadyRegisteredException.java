package com.mercadolivre.bootcamp.projeto_integrador.exception.warehouseException;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class WarehouseAlreadyRegisteredException extends BaseException {
    public WarehouseAlreadyRegisteredException(String warehouseId) {
        super("The Warehouse " + warehouseId + " is already created.", HttpStatus.CONFLICT, ZonedDateTime.now());
    }
}
