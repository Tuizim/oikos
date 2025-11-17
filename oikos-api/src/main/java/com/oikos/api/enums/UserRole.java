package com.oikos.api.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");
    private final String description;

    UserRole(String description) {
        this.description = description;
    }
}