package com.oikos.api.dto.user;

import com.oikos.api.entity.User;

public class UserConverter {
    public UserResponseDTO entityToResponseDto(User entity) {
        return UserResponseDTO.builder()
        .name(entity.getName())
        .username(entity.getUsername())
        .email(entity.getEmail())
        .phone(entity.getPhone())
        .active(entity.getActive())
        .create_at(entity.getCreatedAt())
        .update_at(entity.getUpdatedAt())
        .role(entity.getRole().getDescription())
        .build();
    }
}
