package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.model.Admin;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AdminValidator implements UserValidator<Admin> {

    private UserRepository userRepository;

    @Override
    public UserRepository getUserRepository() {
        return userRepository; // necessário para a validação de e-mail
    }

    //demais métodos
}
