package com.github.sintaxenervosa.discoxp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.github.sintaxenervosa.discoxp.dto.LoginRequestDto;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.StockistRepository;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;

    private StockistRepository stockistRepository;

    public Optional<User> login(LoginRequestDto loginRequest){
        System.out.println("Vamos lá logando...");
        return null;
    }
}