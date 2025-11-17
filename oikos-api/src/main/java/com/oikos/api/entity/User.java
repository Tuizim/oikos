package com.oikos.api.entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import com.oikos.api.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Column(nullable = false, unique = true, columnDefinition = "uuid DEFAULT uuid_generate_v4()")
    private UUID uuid = UUID.randomUUID();

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 150, unique = true)
    private String email;

    @Column(nullable = false, columnDefinition = "text")
    private String password;

    @Column(length = 20)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserRole role;

    @Builder.Default
    @Column(nullable = false)
    private Boolean active = true;

    @Builder.Default
    @Column(name = "created_at", updatable = false, insertable = false,
            columnDefinition = "timestamp without time zone DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Builder.Default
    @Column(name = "updated_at", insertable = false,
            columnDefinition = "timestamp without time zone DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime updatedAt = LocalDateTime.now();

    @Column(nullable = false, length = 150, unique = true)
    private String username;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if( role == UserRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
