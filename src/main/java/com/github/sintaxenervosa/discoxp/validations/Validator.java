package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.dto.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.user.UpdateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.model.User;

// interface que define métodos obrigatórios
public interface Validator {
    void validateUserCreation(CreateUserRequestDTO request); // validacao para criar o usuario
    void validateUserUpdate(User value, UpdateUserRequestDTO request); // validacao para criar o usuario
    void validateUserSearchById(String id);
    void validateLogin(LoginRequestDto request);
}
