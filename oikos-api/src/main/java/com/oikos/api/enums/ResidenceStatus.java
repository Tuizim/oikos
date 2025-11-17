package com.oikos.api.enums;

import lombok.Getter;

@Getter
public enum ResidenceStatus {
    AVAILABLE("Disponível"),
    OCCUPIED("Ocupada"),
    MAINTENANCE("Em manutenção"),
    RESERVED("Reservada"),
    INACTIVE("Inativa");

    private final String label;
    ResidenceStatus(String label) {
        this.label = label;
    }
}
