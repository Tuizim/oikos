package com.oikos.api.dto.user;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserResponseDTO {
    private String username;
    private String name;
    private String email;
    private String phone;
    private String role;
    private Boolean active;
    private LocalDateTime create_at;
    private LocalDateTime update_at;
}
