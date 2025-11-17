package com.oikos.api.dto.authentication;

import com.oikos.api.enums.UserRole;

public record RegisterDto(
        String name,
        String email,
        String password,
        String phone,
        UserRole role,
        String username
) {
}
