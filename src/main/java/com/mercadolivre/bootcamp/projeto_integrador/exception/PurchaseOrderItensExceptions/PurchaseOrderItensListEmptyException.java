package com.mercadolivre.bootcamp.projeto_integrador.exception.PurchaseOrderItensExceptions;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class PurchaseOrderItensListEmptyException extends BaseException {
    public PurchaseOrderItensListEmptyException() {
        super("Não foi encontrado nenhum item.", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
