package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.model.Client;
import com.github.sintaxenervosa.discoxp.repository.CpfHolderRepository;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClientValidator implements UserValidator<Client>, CpfValidator {

    private UserRepository userRepository;
    private CpfHolderRepository cpfHolderRepository;


    @Override
    public UserRepository getUserRepository() {
        return userRepository; // necessário para a validação de e-mail
    }

    @Override
    public CpfHolderRepository getCpfHolderRepository() {
        return cpfHolderRepository;
    }


    //demais métodos
}
