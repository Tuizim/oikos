package com.oikos.api.services;

import com.oikos.api.dto.authentication.RegisterDto;
import com.oikos.api.dto.user.UserResponseDTO;
import com.oikos.api.entity.User;
import com.oikos.api.enums.UserRole;
import com.oikos.api.exceptions.OikosErrorCatalog;
import com.oikos.api.exceptions.OikosException;
import com.oikos.api.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.mockito.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("UserService - Testes unitários")
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;
    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("createUser: deve salvar usuário com senha Bcrypt e campos corretos")
    void createUser_deveSalvarUsuarioComSenhaCriptografada() {
        // Arrange
        RegisterDto dto = new RegisterDto(
                "Admin da Silva",
                "admin@exemplo.com",
                "123456",
                "+55 11 99999-9999",
                UserRole.ADMIN,
                "admin"
        );

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);

        userService.createUser(dto);

        verify(userRepository, times(1)).save(captor.capture());
        User salvo = captor.getValue();

        assertEquals(dto.username(), salvo.getUsername());
        assertEquals(dto.email(), salvo.getEmail());
        assertEquals(dto.name(), salvo.getName());
        assertEquals(dto.phone(), salvo.getPhone());
        assertEquals(dto.role(), salvo.getRole());

        assertNotEquals(dto.password(), salvo.getPassword());

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        assertTrue(encoder.matches(dto.password(), salvo.getPassword()),
                "Hash Bcrypt não corresponde à senha original");
    }

    @Test
    @DisplayName("deleteByUuid: quando uuid existir, deve remover e não lançar exceção")
    void deleteByUuid_quandoExiste_deveRemover() {
        UUID uuid = UUID.randomUUID();
        when(userRepository.existsByUuid(uuid)).thenReturn(true);

        assertDoesNotThrow(() -> userService.deleteByUuid(uuid));
        verify(userRepository).removeByUuid(uuid);
    }

    @Test
    @DisplayName("deleteByUuid: quando uuid não existir, deve lançar RuntimeException com OikosException de causa")
    void deleteByUuid_quandoNaoExiste_deveLancar() {
        UUID uuid = UUID.randomUUID();
        when(userRepository.existsByUuid(uuid)).thenReturn(false);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> userService.deleteByUuid(uuid));
        assertTrue(ex.getCause() instanceof OikosException);
        OikosException causa = (OikosException) ex.getCause();
        assertEquals(OikosErrorCatalog.USUARIO_NAO_ENCONTRADO, causa.getCodigo());
        verify(userRepository, never()).removeByUuid(any());
    }

    @Test
    @DisplayName("findByUsername: deve delegar ao repositório e retornar UserDetails")
    void findByUsername_deveRetornarUserDetails() {
        String username = "johndoe";
        User user = User.builder().username(username).build();

        when(userRepository.findByUsername(username)).thenReturn(user);

        UserDetails result = userService.findByUsername(username);

        assertNotNull(result);
        assertEquals(username, ((User) result).getUsername());
        verify(userRepository).findByUsername(username);
    }

    @Test
    @DisplayName("findDtoByUuid: quando encontrar, deve retornar DTO não nulo")
    void findDtoByUuid_quandoEncontrar_deveRetornarDto() {
        UUID uuid = UUID.randomUUID();
        User entity = User.builder()
                .username("ana")
                .email("ana@exemplo.com")
                .name("Ana")
                .phone("123")
                .role(UserRole.ADMIN)
                .build();

        when(userRepository.findByUuid(uuid)).thenReturn(Optional.of(entity));

        UserResponseDTO dto = userService.findDtoByUuid(uuid);

        assertNotNull(dto, "DTO não deveria ser nulo");
        verify(userRepository).findByUuid(uuid);
    }

    @Test
    @DisplayName("findDtoByUuid: quando não encontrar, deve lançar OikosException(USUARIO_NAO_ENCONTRADO)")
    void findDtoByUuid_quandoNaoEncontrar_deveLancarOikosException() {
        UUID uuid = UUID.randomUUID();
        when(userRepository.findByUuid(uuid)).thenReturn(Optional.empty());

        OikosException ex = assertThrows(OikosException.class, () -> userService.findDtoByUuid(uuid));
        assertEquals(OikosErrorCatalog.USUARIO_NAO_ENCONTRADO, ex.getCodigo());
        verify(userRepository).findByUuid(uuid);
    }
}
