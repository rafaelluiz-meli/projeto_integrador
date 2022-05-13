package com.mercadolivre.bootcamp.projeto_integrador.entity;

public enum HistoryType {

    ENTRY("Entrada"),
    EXIT("Saída");

    private String value;

    HistoryType(String value) {
        this.value = value;
    }

    public String getHistoryType() {
        return this.value;
    }
}
