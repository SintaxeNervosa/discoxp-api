package com.github.sintaxenervosa.discoxp.validations;

import java.util.Optional;

import org.apache.commons.validator.routines.EmailValidator;

import com.github.sintaxenervosa.discoxp.dto.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.dto.error.user.UsernameException;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserValidation extends Validation {
    private UserRepository userRepository;
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


      public UserValidation(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    
    public Optional<User> login(LoginRequestDto loginRequestDto){
        Optional<User> userEmail = userRepository.findByEmail(loginRequestDto.email());
        //mais validaçoes
        return userEmail;
    }
}
