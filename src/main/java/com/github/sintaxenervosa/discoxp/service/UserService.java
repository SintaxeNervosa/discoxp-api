package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.validations.UserValidator;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private UserValidator userValidator;

    public UserService(UserValidator userValidator){
        this.userValidator = userValidator;
    }

    public void createUser(CreateUserRequestDTO request) {
        userValidator.validatePassword(request.password());
        // ...
        // userRepository.save(user);
    }
}