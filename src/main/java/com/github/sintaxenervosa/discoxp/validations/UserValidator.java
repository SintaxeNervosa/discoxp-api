package com.github.sintaxenervosa.discoxp.validations;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.sintaxenervosa.discoxp.dto.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.user.UpdateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;

@Component
public class UserValidator
        implements Validator, EmailValidator, PasswordValidator, NameValidator, CpfValidator, GroupValidator {

    private final UserRepository userRepository;
    private static List<String> errorsSingleton;
    private final PasswordEncoder passwordEncoder;

    public UserValidator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void validateUserCreation(CreateUserRequestDTO request) {
        getErrorsSingleton(); // criar (se necessário) uma única instância da lista

        try {
            validateName(request.name());
        } catch (InvalidUserDataException e) {
            errorsSingleton.add(e.getMessage());
        }

        try {
            validateFormatEmail(request.email());
            if (validateExistsByEmail(request.email())) {
                throw new InvalidUserDataException("Email invalido");
            }

        } catch (InvalidUserDataException e) {
            errorsSingleton.add(e.getMessage());
        }

        try {
            validatePassword(request.password());
        } catch (InvalidUserDataException e) {
            errorsSingleton.add(e.getMessage());
        }

        try {
            validateFormatCpf(request.cpf());
            if (validateExistsByCpf(request.cpf())) {
                throw new InvalidUserDataException("Cpf invalido");
            }
        } catch (InvalidUserDataException e) {
            errorsSingleton.add(e.getMessage());
        }

        try {
//            validateGroup(Group.valueOf(request.group()));
        } catch (InvalidUserDataException e) {
            errorsSingleton.add(e.getMessage());
        } catch (IllegalArgumentException e) {
            errorsSingleton.add("Grupo invalido");
        }

        if (errorsSingleton.isEmpty()) {
            return;
        }

        StringBuilder errorMessage = new StringBuilder(errorsSingleton.size());
        for (String error : errorsSingleton) {
            errorMessage.append(error).append(", ");
        }

        errorsSingleton.clear();
        throw new InvalidUserDataException(errorMessage.toString());
    }

    @Override
    public void validateUserUpdate(User savedUser, UpdateUserRequestDTO request) {
        getErrorsSingleton(); // cria (se necessário) uma única instância da lista

        // se os nomes são diferentes
        if (!savedUser.getName().equals(request.name())) {
            validateName(request.name()); // valida o nome
        };

        validateGroup(request.group());

        // se o cpf for diferente
        if (!savedUser.getCpf().equals(request.cpf())) {
            validateFormatCpf(request.cpf()); // valida o formato do cpf

            // verifica se existe se já
            if (validateExistsByCpf(request.cpf())) {
                errorsSingleton.add("CPF inválido"); // valida se existe em outro usuário
            };
        };

        // validação de senha
        validatePassword(request.password());

        if(errorsSingleton.isEmpty()) { return; }

        String errorMessage = getErrorMessage();

        throw new InvalidUserDataException(errorMessage);
    }


//    @Override
//    public void validateUserUpdate(User savedUser, UpdateUserRequestDTO request) {
//        getErrorsSingleton(); // criar (se necessário) uma única instância da lista
//
//        try {
//            // validação de nome caso diferentes
//            if (!savedUser.getName().equals(request.name())) {
//                validateName(request.name());
//            }
//            ;
//        } catch (InvalidUserDataException e) {
//            errorsSingleton.add(e.getMessage()); // adiciona o erro a lista de erros
//        }
//        ;
//
//        try {
//            Group group = Group.valueOf(request.group());
//            if (!savedUser.getGroupEnum().equals(group)) {
//                validateGroup(group);
//            }
//            ;
//        } catch (InvalidUserDataException e) {
//            errorsSingleton.add(e.getMessage());
//        } catch (IllegalArgumentException e) {
//            errorsSingleton.add("Grupo inválido");
//        }
//        ;
//
//        try {
//            if (!savedUser.getCpf().equals(request.cpf())) {
//                validateFormatCpf(request.cpf()); // valida o formato do cpf
//
//                if (validateExistsByCpf(request.cpf())) {
//                    throw new InvalidUserDataException("CPF inválido"); // valida se existe em outro usuário
//                }
//                ;
//            }
//            ;
//        } catch (InvalidUserDataException e) {
//            errorsSingleton.add(e.getMessage());
//        }
//
//        try {
//            validatePassword(request.password());
//        } catch (InvalidUserDataException e) {
//            errorsSingleton.add(e.getMessage());
//        }
//
//

//
//    }

    @Override
    public void validateUserSearchById(String id) {
        Long stringToLong;
        try {
            stringToLong = Long.parseLong(id);
        } catch (NumberFormatException e) {

        }

    }

    @Override
    public void validateLogin(LoginRequestDto value) {
        getErrorsSingleton();
        User user = new User();

        try {
            validateFormatEmail(value.email());
            user = userRepository.findByEmail(value.email()).orElseThrow(
                    () -> new InvalidUserDataException("Email e/ou senha invalidos"));
        } catch (InvalidUserDataException e) {
            errorsSingleton.add(e.getMessage());
        }

        try {
            if (value.password() == null) {
                throw new InvalidUserDataException("Informe a senha!");
            }
            if (!passwordEncoder.matches(value.password(), user.getPassword())) {
                throw new InvalidUserDataException("Email e/ou senha invalidos");
            }

        } catch (InvalidUserDataException e) {
            errorsSingleton.add(e.getMessage());
        }

        if(!user.isStatus()) {
            errorsSingleton.add("Usuário desabilitado");
        }

        if (errorsSingleton.isEmpty()) {
            return;
        }

        String errorMessage = getErrorMessage();

        throw new InvalidUserDataException(errorMessage); // lança um exceção dos os erros
    }

    @Override
    public UserRepository userRepository() {
        return userRepository;
    }

    // adiciona a mensagem de erro na lista
    public static void addErroMessageInList(String message) {
        getErrorsSingleton();

        errorsSingleton.add(message);
    };

    private static void getErrorsSingleton() {
        if (errorsSingleton == null) {
            errorsSingleton = new ArrayList<>();
        }
    }


    private static String getErrorMessage() {
        getErrorsSingleton();

        StringBuilder errorMessage = new StringBuilder(errorsSingleton.size());

        for (String m : errorsSingleton) {
            errorMessage.append(m).append(", ");
        };

        errorsSingleton.clear();
        return errorMessage.toString();
    }

}
