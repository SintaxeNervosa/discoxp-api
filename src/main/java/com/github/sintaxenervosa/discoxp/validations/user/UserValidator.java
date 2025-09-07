package com.github.sintaxenervosa.discoxp.validations.user;

import com.github.sintaxenervosa.discoxp.dto.user.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.user.UpdateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.model.User;

// interface que define métodos obrigatórios
public interface UserValidator {
    void validateUserCreation(CreateUserRequestDTO request); // validacao para criar o usuario
    void validateUserUpdate(User savedUser, UpdateUserRequestDTO request); // validacao para criar o usuario
    void validateUserSearchById(String id);
    void validateLogin(LoginRequestDto request);
}
