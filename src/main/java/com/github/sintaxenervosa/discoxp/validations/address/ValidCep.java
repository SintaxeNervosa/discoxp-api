package com.github.sintaxenervosa.discoxp.validations.address;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CepValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCep {
    String message() default "CEP inválido ou não encontrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
