package com.mercadolivre.bootcamp.projeto_integrador.exception.inbound_order;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

public class RepresentativeNotAssociatedWithSectionException extends BaseException {
    public RepresentativeNotAssociatedWithSectionException(Long representativeId, Long sectionId) {
        super("Representante com ID: " + representativeId + " não está autorizado para criar ordens em sessão ID: " + sectionId, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
    }
}
