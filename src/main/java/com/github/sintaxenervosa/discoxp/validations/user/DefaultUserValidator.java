package com.github.sintaxenervosa.discoxp.validations.user;

import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

import org.hibernate.usertype.UserType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.github.sintaxenervosa.discoxp.dto.user.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.user.UpdateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.model.Group;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;

@Component
public class DefaultUserValidator implements UserValidator, EmailValidator, PasswordValidator, NameValidator, CpfValidator, GroupValidator, BirthDateValidator, GenderValidator {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaultUserValidator(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    // receber o TIPO USER no parametro
    @Override
    public void validateUserCreation(CreateUserRequestDTO request) {

        // validação do nome
        validateName(request.name());

        // se o formato do e-mail for válido
        if (validateFormatEmail(request.email())) {
            // verifica se o e-mail já está sendo utilizado
            if (validateExistsByEmail(request.email())) {
                ValidationErrorRegistry.addError("Email inválido");
            }
        }

        // validação de senha
        validatePassword(request.password());

        // se o formato do cpf for válido
        if (validateFormatCpf(request.cpf())) {
            // verifica se já está em uso
            if (validateExistsByCpf(request.cpf())) {
                ValidationErrorRegistry.addError("Cpf inválido");
            }
        }

        // validação do grupo
        validateGroup(request.group());

        if(request.group().equals(Group.CLIENT.toString())) {
            System.out.println("AQUI!");
            validateBirthDate(request.dateOfBirth());
            validateGender(request.gender());
        }

        // retorna caso não haja erros na lista
        if (!ValidationErrorRegistry.hasErrors()) {
            return;
        }

        // captura a mensagem de erro
        String errorMessage = ValidationErrorRegistry.getErrorMessage();

        throw new InvalidUserDataException(errorMessage);
    }

    @Override
    public void validateUserUpdate(User savedUser, UpdateUserRequestDTO request) {
        // se os nomes são diferentes
        if (!savedUser.getName().equals(request.name())) {
            validateName(request.name()); // valida o nome
        }

        validateGroup(request.group());

        // se o cpf for diferente
        if (!savedUser.getCpf().equals(request.cpf())) {
            validateFormatCpf(request.cpf()); // valida o formato do cpf

            // verifica se existe se já
            if (validateExistsByCpf(request.cpf())) {
                ValidationErrorRegistry.addError("CPF inválido");
            }
        }

        // validação de senha
        validatePassword(request.password());

        // retorna caso não tenha erros
        if (!ValidationErrorRegistry.hasErrors()) {
            return;
        }

        // captura a mensagem de erro
        String errorMessage = ValidationErrorRegistry.getErrorMessage();

        // lança a exceção com a mensagem de erro
        throw new InvalidUserDataException(errorMessage);
    }

    @Override
    public void validateUserSearchById(String id) {
        Long stringToLong = null;
        try {
            stringToLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidUserDataException("ID inválido");
        }

    }

    @Override
    public void validateLogin(LoginRequestDto value) {
        User user = new User();

        // se o formato do e-mail for válido
        if(validateFormatEmail(value.email())) {
            // busca o usuário pelo e-mail
            user = userRepository.findByEmail(value.email()).orElseThrow(
                    () -> new InvalidUserDataException("Email e/ou senha inválidos"));
        }

        try {
            if (value.password() == null) {
                throw new InvalidUserDataException("Informe a senha!");
            }
            if (!passwordEncoder.matches(value.password(), user.getPassword())) {
                throw new InvalidUserDataException("Email e/ou senha invalidos");
            }

        } catch (InvalidUserDataException e) {
            ValidationErrorRegistry.addError(e.getMessage());
        }

        // verifica se está ativo
        if (!user.isStatus()) {
            ValidationErrorRegistry.addError("Usuário desabilitado");
        }

        // rotorna caso não tenha erros
        if (!ValidationErrorRegistry.hasErrors()) { return; }

        // captura a mensagem de erro
        String errorMessage = ValidationErrorRegistry.getErrorMessage();

        // lança a exceção
        throw new InvalidUserDataException(errorMessage);
    }

    @Override
    public UserRepository userRepository() {
        return userRepository;
    }
}

    // adiciona a mensagem de erro na lista
//    public static void addErroMessageInList(String message) {
//        getErrorsSingleton();
//
//        errorsSingleton.add(message);
//    };

//    private static void getErrorsSingleton() {
//        if (errorsSingleton == null) {
//            errorsSingleton = new ArrayList<>();
//        }
//    }



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
//    }

