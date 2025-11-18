package com.github.sintaxenervosa.discoxp.repository;

import com.github.sintaxenervosa.discoxp.dto.user.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.model.Group;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import com.github.sintaxenervosa.discoxp.service.UserService;
import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;
import com.github.sintaxenervosa.discoxp.validations.user.DefaultUserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class LoginTest {

    private UserRepository userRepository;
    private DefaultUserValidator validator;
    private UserService userService;
    private PasswordEncoder encoder;

    private User mockUser;

    @BeforeEach
    void setup() {
        ValidationErrorRegistry.hasErrors();

        // mocks
        userRepository = Mockito.mock(UserRepository.class);
        encoder = Mockito.mock(PasswordEncoder.class);

        // validator concreto que depende do repo e encoder
        validator = new DefaultUserValidator(userRepository, encoder);
        userService = new UserService(validator, userRepository, encoder, validator);

        // usuário fake
        mockUser = new User();
        mockUser.setId(1L);
        mockUser.setEmail("teste@teste.com");
        mockUser.setName("Rodrigo");
        mockUser.setGroupEnum(Group.ADMIN);
        mockUser.setStatus(true);
        mockUser.setPassword("HASH"); // senha "armazenada"

        // comportamento do encoder.matches para senha correta
        Mockito.when(encoder.matches("Senha@123", "HASH")).thenReturn(true);
        Mockito.when(encoder.matches("Errada", "HASH")).thenReturn(false);
    }

    // -------------------------------------------------------------
    // TESTES SIMPLES DE LOGIN
    // -------------------------------------------------------------

    @Test
    void deveFalhar_QuandoEmailNaoInformado() {
        LoginRequestDto dto = new LoginRequestDto(null, "Senha@123");

        Assertions.assertThrows(RuntimeException.class, () -> {
            validator.validateLogin(dto);
        });
    }

    @Test
    void deveFalhar_QuandoSenhaNaoInformada() {
        LoginRequestDto dto = new LoginRequestDto("teste@teste.com", null);

        // se o validator tentar buscar o usuário, mockamos retorno para evitar NPE
        Mockito.when(userRepository.findByEmail("teste@teste.com"))
                .thenReturn(Optional.of(mockUser));

        Assertions.assertThrows(RuntimeException.class, () -> {
            validator.validateLogin(dto);
        });
    }

    @Test
    void deveFalhar_QuandoUsuarioNaoExiste() {
        Mockito.when(userRepository.findByEmail("x@x.com"))
                .thenReturn(Optional.empty());

        LoginRequestDto dto = new LoginRequestDto("x@x.com", "Senha@123");

        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.loginUser(dto);
        });
    }

    @Test
    void deveFalhar_QuandoSenhaIncorreta() {
        Mockito.when(userRepository.findByEmail("teste@teste.com"))
                .thenReturn(Optional.of(mockUser));

        LoginRequestDto dto = new LoginRequestDto("teste@teste.com", "Errada");

        Assertions.assertThrows(RuntimeException.class, () -> {
            userService.loginUser(dto);
        });
    }

    @Test
    void deveLogar_QuandoCredenciaisCorretas() {
        Mockito.when(userRepository.findByEmail("teste@teste.com"))
                .thenReturn(Optional.of(mockUser));

        LoginRequestDto dto = new LoginRequestDto("teste@teste.com", "Senha@123");

        Assertions.assertDoesNotThrow(() -> {
            userService.loginUser(dto);
        });
    }
}
