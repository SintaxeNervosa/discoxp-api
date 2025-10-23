package com.github.sintaxenervosa.discoxp.validations.address;

import com.github.sintaxenervosa.discoxp.service.ViaCepService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CepValidator implements ConstraintValidator<ValidCep, String> {

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public boolean isValid(String cep, ConstraintValidatorContext context) {
        if (cep == null || cep.isBlank()) return false;

        try {
            viaCepService.buscarEnderecoPorCep(cep);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
