package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.dto.error.user.UsernameException;
import lombok.AllArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;

@AllArgsConstructor
public class UserValidation extends Validation {

    private EmailValidator emailValidator;

    public void isNameValid(String username) {
        if(username.length() < 3) {
            throw new UsernameException("O nome deve ser maior que 2 catacteres");
        }

        for(char c : username.toCharArray()) {
            if(Character.isDigit(c)) {
                throw new UsernameException("Nome de usuário inválido");
            }
        }
    }

    public void isEmailValid(String email) {

    }



}
