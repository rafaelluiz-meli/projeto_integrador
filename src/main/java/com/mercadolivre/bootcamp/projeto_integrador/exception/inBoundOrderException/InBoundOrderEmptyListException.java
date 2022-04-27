package com.mercadolivre.bootcamp.projeto_integrador.exception.inBoundOrderException;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class InBoundOrderEmptyListException extends BaseException {

    public InBoundOrderEmptyListException() {
        super("Não foi encontrado nenhum pedido de entrada. ", HttpStatus.NOT_FOUND, ZonedDateTime.now());
    }
}
