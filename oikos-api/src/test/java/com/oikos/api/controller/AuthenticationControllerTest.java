package com.oikos.api.controller;

import com.oikos.api.dto.authentication.AuthenticationDto;
import com.oikos.api.dto.authentication.LoginResponseDto;
import com.oikos.api.dto.authentication.RegisterDto;
import com.oikos.api.entity.User;
import com.oikos.api.enums.UserRole;
import com.oikos.api.exceptions.OikosErrorCatalog;
import com.oikos.api.exceptions.OikosException;
import com.oikos.api.infra.security.TokenService;
import com.oikos.api.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenService tokenService;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Deve realizar login com sucesso e retornar o token JWT")
    void deveFazerLoginComSucessoERetornarToken() {
        // Arrange
        AuthenticationDto dto = new AuthenticationDto("user", "123");
        User user = new User();
        Authentication auth = mock(Authentication.class);

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(auth);
        when(auth.getPrincipal()).thenReturn(user);
        when(tokenService.generateToken(user)).thenReturn("fake-jwt");

        // Act
        ResponseEntity response = authenticationController.login(dto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        LoginResponseDto body = (LoginResponseDto) response.getBody();
        assertEquals("fake-jwt", body.token());
        verify(authenticationManager).authenticate(any());
        verify(tokenService).generateToken(user);
    }

    @Test
    @DisplayName("Deve registrar um novo usuário quando o login ainda não existe")
    void deveRegistrarUsuarioComSucessoQuandoNaoExiste() {
        RegisterDto dto = new RegisterDto("novoUser", "email@teste.com", "123","1799999999", UserRole.USER,"Novo");

        when(userService.findByUsername("novoUser")).thenReturn(null);

        ResponseEntity response = authenticationController.register(dto);

        assertEquals(200, response.getStatusCodeValue());
        verify(userService).createUser(dto);
    }

    @Test
    @DisplayName("Deve lançar exceção quando tentar registrar um usuário já existente")
    void deveLancarExcecaoQuandoUsuarioJaExiste() {
        RegisterDto dto = new RegisterDto("userExistente", "email@teste.com", "123","1799999999", UserRole.USER,"userExistente");

        when(userService.findByUsername("userExistente")).thenReturn(new User());

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            authenticationController.register(dto);
        });

        assertTrue(thrown.getCause() instanceof OikosException);
        OikosException cause = (OikosException) thrown.getCause();
        assertEquals(OikosErrorCatalog.USUARIO_LOGIN_JA_REGISTRADO, cause.getCodigo());
    }
}
