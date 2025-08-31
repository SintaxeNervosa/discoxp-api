package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.model.Stockist;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class StockistValidator implements UserValidator<Stockist> {
    private UserRepository userRepository;

    @Override
    public UserRepository getUserRepository() {
        return userRepository; // necessário para a validação de e-mail
    }

    //demais métodos
}
