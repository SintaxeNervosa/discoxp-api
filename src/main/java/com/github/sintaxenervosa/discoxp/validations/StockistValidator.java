package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.model.Stockist;
import com.github.sintaxenervosa.discoxp.repository.CpfHolderRepository;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class StockistValidator implements UserValidator<Stockist>, CpfValidator {
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
