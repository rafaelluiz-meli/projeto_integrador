package com.mercadolivre.bootcamp.projeto_integrador.exception.inbound_order;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class InvalidInboundOrderException extends BaseException {

    public InvalidInboundOrderException() {
        super("Pedido de compra inválido ou incompleto.", HttpStatus.BAD_REQUEST, ZonedDateTime.now());
    }
}
