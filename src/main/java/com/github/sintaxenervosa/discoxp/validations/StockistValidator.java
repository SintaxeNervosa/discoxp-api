package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.model.Stockist;
import com.github.sintaxenervosa.discoxp.repository.CpfHolderRepository;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class StockistValidator implements Validator<Stockist>, EmailValidator, PasswordValidator, NameValidator, CpfValidator {

    private final UserRepository userRepository;
    private final CpfHolderRepository cpfHolderRepository;

    @Override
    public void validate(Stockist stockist) throws IllegalAccessException {
        validateEmail(stockist.getEmail());
        validatePassword(stockist.getPassword());
        validateName(stockist.getName());
        validateCpf(stockist.getCpf());
    }

    @Override
    public CpfHolderRepository getCpfHolderRepository() {
        return cpfHolderRepository;
    }

    @Override
    public UserRepository getUserRepository() {
        return userRepository;
    }
}
