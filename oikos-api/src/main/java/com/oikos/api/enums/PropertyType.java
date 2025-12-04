package com.oikos.api.enums;

import lombok.Getter;

@Getter
public enum PropertyType {
    HOUSE("Casa"),
    APARTMENT("Apartamento"),
    KITNET("Kitnet"),
    STUDIO("Studio"),
    RURAL("Propriedade Rural"),
    COMMERCIAL("Comercial"),
    OFFICE("Escritório"),
    STORE("Loja / Ponto Comercial"),
    WAREHOUSE("Galpão / Depósito"),
    LAND("Terreno"),
    DUPLEX("Sobrado / Duplex");

    private final String label;

    PropertyType(String label) {
        this.label = label;
    }
}
