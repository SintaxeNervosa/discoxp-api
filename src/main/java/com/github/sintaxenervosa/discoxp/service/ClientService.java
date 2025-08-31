package com.github.sintaxenervosa.discoxp.service;

import org.springframework.stereotype.Service;

import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import com.github.sintaxenervosa.discoxp.validations.UserValidation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Service
public class ClientService {
    private UserRepository userRepository;
    private UserValidation userValidation;


}
