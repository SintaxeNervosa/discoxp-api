package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.dto.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.dto.LoginResponseDto;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginValidator {

    @Autowired
    private UserRepository userRepository;

    public LoginResponseDto validate(LoginRequestDto value){
        // userRepository.findByEmail()
        return  null;
    }
}
