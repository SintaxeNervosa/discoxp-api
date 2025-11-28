package com.github.sintaxenervosa.discoxp.unity;

import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import com.github.sintaxenervosa.discoxp.service.UserService;
import com.github.sintaxenervosa.discoxp.validations.user.DefaultUserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private DefaultUserValidator userValidator;

    @Mock
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @BeforeEach
    void setup() {
        userService = new UserService(
                userValidator,
                userRepository,
                passwordEncoder,
                userValidator
        );
    }

    @Test
    void emailForInvalido() {
        CreateUserRequestDTO request = new CreateUserRequestDTO(
                "Alisson Test",
                "email-invalido",
                "ADMIN",
                "Senha@123",
                "12345678901",
                LocalDate.now(),
                "MALE"
        );

        doThrow(new InvalidUserDataException("E-mail inválido"))
                .when(userValidator).validateUserCreation(request);

        InvalidUserDataException ex = assertThrows(
                InvalidUserDataException.class,
                () -> userService.createUser(request)
        );

        assertEquals("E-mail inválido", ex.getMessage());
    }

    @Test
    void nomeForNulo() {
        CreateUserRequestDTO req = new CreateUserRequestDTO(
                null,
                "alisson@gmail.com",
                "ADMIN",
                "Senha@123",
                "12345678901",
                LocalDate.now(),
                "MALE"
        );

        doThrow(new InvalidUserDataException("Nome não pode ser nulo"))
                .when(userValidator).validateUserCreation(req);

        InvalidUserDataException ex = assertThrows(
                InvalidUserDataException.class,
                () -> userService.createUser(req)
        );

        assertEquals("Nome não pode ser nulo", ex.getMessage());
    }

    @Test
    void nomeNumeros() {
        CreateUserRequestDTO req = new CreateUserRequestDTO(
                "Alisson123",
                "alisson@gmail.com",
                "ADMIN",
                "Senha@123",
                "12345678901",
                LocalDate.now(),
                "MALE"
        );

        doThrow(new InvalidUserDataException("Nome não pode conter números"))
                .when(userValidator).validateUserCreation(req);

        InvalidUserDataException ex = assertThrows(
                InvalidUserDataException.class,
                () -> userService.createUser(req)
        );

        assertEquals("Nome não pode conter números", ex.getMessage());
    }

    @Test
    void cpfInvalido() {
        CreateUserRequestDTO request = new CreateUserRequestDTO(
                "Alisosn",
                "alisson@gmail.com",
                "ADMIN",
                "Senha@123",
                "111",
                LocalDate.now(),
                "MALE"
        );

        doThrow(new InvalidUserDataException("CPF inválido"))
                .when(userValidator).validateUserCreation(request);

        assertThrows(InvalidUserDataException.class, () -> userService.createUser(request));
    }

    @Test
    void grupoForNulo() {
        CreateUserRequestDTO req = new CreateUserRequestDTO(
                "Alisson",
                "alisson@gmail.com",
                null,
                "Senha@123",
                "12345678901",
                LocalDate.now(),
                "MALE"
        );

        doThrow(new InvalidUserDataException("Grupo é obrigatório"))
                .when(userValidator).validateUserCreation(req);

        InvalidUserDataException ex = assertThrows(
                InvalidUserDataException.class,
                () -> userService.createUser(req)
        );

        assertEquals("Grupo é obrigatório", ex.getMessage());
    }

    @Test
    void validarRegrasDeSenha() {
        Object[][] cenarios = {
                {"Ab1@", "Senha deve conter no mínimo 8 caracteres"},
                {"senha@123", "Senha deve conter ao menos uma letra maiúscula"},
                {"Senha@aaa", "Senha deve conter ao menos um número"},
                {"Senha123", "Senha deve conter caractere especial"},
                {"SenhaValida1@", "As senhas não coincidem"}
        };

        for (Object[] c : cenarios) {
            String senha = (String) c[0];
            String mensagemEsperada = (String) c[1];

            CreateUserRequestDTO req = new CreateUserRequestDTO(
                    "Alisson",
                    "alisson@gmail.com",
                    "ADMIN",
                    senha,
                    "12345678901",
                    LocalDate.now(),
                    "MALE"
            );

            doThrow(new InvalidUserDataException(mensagemEsperada))
                    .when(userValidator).validateUserCreation(req);

            InvalidUserDataException ex = assertThrows(
                    InvalidUserDataException.class,
                    () -> userService.createUser(req)
            );

            assertEquals(mensagemEsperada, ex.getMessage());
        }
    }
}
