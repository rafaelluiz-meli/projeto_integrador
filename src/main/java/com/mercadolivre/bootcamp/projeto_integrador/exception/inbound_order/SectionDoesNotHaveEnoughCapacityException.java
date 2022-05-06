package com.mercadolivre.bootcamp.projeto_integrador.exception.inbound_order;

import com.mercadolivre.bootcamp.projeto_integrador.exception.BaseException;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public class SectionDoesNotHaveEnoughCapacityException extends BaseException {
    public SectionDoesNotHaveEnoughCapacityException(Long sectionId, BigDecimal totalVolume) {
        super("Section com ID: " + sectionId + " não tem capacidade suficiente para armazenar: " + totalVolume, HttpStatus.BAD_REQUEST, ZonedDateTime.now());
    }
}
