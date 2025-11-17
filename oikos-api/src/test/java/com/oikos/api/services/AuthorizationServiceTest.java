package com.oikos.api.services;

import com.oikos.api.entity.User;
import com.oikos.api.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthorizationServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthorizationService authorizationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve retornar UserDetails quando o usuário for encontrado")
    void deveRetornarUserDetailsQuandoUsuarioExistir() {
        String username = "admin";
        User user = new User();
        user.setUsername(username);

        when(userService.findByUsername(username)).thenReturn(user);

        UserDetails result = authorizationService.loadUserByUsername(username);

        assertNotNull(result);
        assertEquals(username, result.getUsername());
        verify(userService).findByUsername(username);
    }

    @Test
    @DisplayName("Deve lançar UsernameNotFoundException quando o usuário não existir")
    void deveLancarExcecaoQuandoUsuarioNaoExistir() {
        String username = "inexistente";
        when(userService.findByUsername(username)).thenThrow(UsernameNotFoundException.class);

        assertThrows(UsernameNotFoundException.class, () -> {
            authorizationService.loadUserByUsername(username);
        });

        verify(userService).findByUsername(username);
    }
}