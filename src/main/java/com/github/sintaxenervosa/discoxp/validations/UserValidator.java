package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.dto.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.user.UpdateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.model.Group;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class UserValidator implements Validator, EmailValidator, PasswordValidator, NameValidator, CpfValidator, GroupValidator {

    private UserRepository userRepository;
    private static List<String> errorsSingleton;

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
            if(validateExistsByEmail(request.email())) {
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
            validateGroup(Group.valueOf(request.group()));
        } catch (InvalidUserDataException e) {
            errorsSingleton.add(e.getMessage());
        } catch (IllegalArgumentException e) {
            errorsSingleton.add("Grupo invalido");
        }

        if(errorsSingleton.isEmpty()) {
            return;
        }

        StringBuilder errorMessage = new  StringBuilder(errorsSingleton.size());
        for(String error: errorsSingleton){
            errorMessage.append(error).append(", ");
        }

        errorsSingleton.clear();
        throw new InvalidUserDataException(errorMessage.toString());
    }

    @Override
    public void validateUserUpdate(User savedUser, UpdateUserRequestDTO request) {
        getErrorsSingleton(); // criar (se necessário) uma única instância da lista

        try {
            // validação de nome caso diferentes
            if(!savedUser.getName().equals(request.name())) {
                validateName(request.name());
            };
        } catch (InvalidUserDataException e) {
            errorsSingleton.add(e.getMessage()); // adiciona o erro a lista de erros
        };

        try {
            Group group = Group.valueOf(request.group());
            if(!savedUser.getGroupEnum().equals(group)) {
                validateGroup(group);
            };
        } catch (InvalidUserDataException e) {
            errorsSingleton.add(e.getMessage());
        } catch (IllegalArgumentException e) {
            errorsSingleton.add("Grupo inválido");
        };

        try {
            if(!savedUser.getCpf().equals(request.cpf())) {
                validateFormatCpf(request.cpf()); // valida o formato do cpf

                if(validateExistsByCpf(request.cpf())) {
                    throw new InvalidUserDataException("CPF inválido"); // valida se existe em outro usuário
                };
            };
        } catch (InvalidUserDataException e) {
            errorsSingleton.add(e.getMessage());
        }

        StringBuilder errorMessage = new StringBuilder(errorsSingleton.size());

        for(String message : errorsSingleton) {
            errorMessage.append(message).append(", ");
        }

        // verifica se há erros
        if(!errorMessage.isEmpty()) {
            errorsSingleton.clear(); // limpa o array
            throw new InvalidUserDataException(errorMessage.toString()); // lança um exceção dos os erros
        }

    }

    @Override
    public void validateUserSearchById(String id) {

    }

    @Override
    public void validateLogin(LoginRequestDto request) {

    }

    @Override
    public UserRepository userRepository() {
        return userRepository;
    }

    private static void getErrorsSingleton() {
        if (errorsSingleton == null) {
            errorsSingleton = new ArrayList<>();
        }
    }
}
