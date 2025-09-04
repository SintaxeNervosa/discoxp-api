package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.dto.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.user.UpdateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserValidator implements Validator, EmailValidator, PasswordValidator, NameValidator, CpfValidator {

    private UserRepository userRepository;



    @Override
    public void validateUserCreation(CreateUserRequestDTO request) {

    }

    @Override
    public void validateUserUpdate(User value, UpdateUserRequestDTO request) {

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


}
