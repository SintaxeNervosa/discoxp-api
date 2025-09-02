package com.github.sintaxenervosa.discoxp.validations;

import org.springframework.stereotype.Component;

import com.github.sintaxenervosa.discoxp.model.User;

@Component
public class LoginValidator implements Validator<User>{

    @Override
    public void validate(User user) throws IllegalAccessException {
        if (!user.isStatus()) {
            throw new IllegalAccessException("Usuário desativado");
        }
        
        // dps coloco todas
    }
}
