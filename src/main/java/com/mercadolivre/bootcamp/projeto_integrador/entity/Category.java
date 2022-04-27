package com.mercadolivre.bootcamp.projeto_integrador.entity;

import javax.persistence.Enumerated;

public enum Category {

    FRESH("Fresco"),
    FROZEN_FOOD("Congelado"),
    REFRIGERATED("Refrigerado");

    private String value;

    Category(String value) {
        this.value = value;
    }

    public String getCategory() {
        return this.value;
    }
}
