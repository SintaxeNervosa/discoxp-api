package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.user.UpdateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.exception.user.UserNotFoundExeption;
import com.github.sintaxenervosa.discoxp.model.Group;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import com.github.sintaxenervosa.discoxp.validations.UserValidator;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.github.sintaxenervosa.discoxp.dto.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.dto.LoginResponseDto;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserValidator userValidator;
    private final PasswordEncoder  passwordEncoder;

    public UserService(UserValidator userValidator, UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userValidator = userValidator;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(CreateUserRequestDTO request) {
        userValidator.validateUserCreation(request);
        String encodedPassword = passwordEncoder.encode(request.password());
        User user = new User(request);

        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public LoginResponseDto loginUser(LoginRequestDto request){
        userValidator.validateLogin(request);

        User user = userRepository.findByEmail(request.email()).get();
        return LoginResponseDto.fromEntity(user);
    }

    public void updateUser(UpdateUserRequestDTO request) {
        if(request.id() == null || request.id().isEmpty()) {
            throw new InvalidUserDataException("Informe o id");
        }

        long id = 0;

        try {
            id = Long.parseLong(request.id());
        } catch (NumberFormatException e) {
            throw new InvalidUserDataException("ID inválido");
        }

        User savedUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundExeption("Usuário não encontrado"));

        userValidator.validateUserUpdate(savedUser, request); // realiza todas as validações para editar

        User newUser = new User();
        // dados que não se alteram
        newUser.setId(savedUser.getId());
        newUser.setEmail(savedUser.getEmail());
        newUser.setPassword(savedUser.getPassword());

        // dados que se alteram
        newUser.setName(request.name());
        newUser.setCpf(request.cpf());
        newUser.setGroupEnum(Group.valueOf(request.group()));

        userRepository.save(newUser);
    }
}