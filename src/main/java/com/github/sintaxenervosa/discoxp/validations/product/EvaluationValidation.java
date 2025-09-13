package com.github.sintaxenervosa.discoxp.validations.product;

public interface EvaluationValidation {

    default void validate(double evaluation) {
        if (evaluation < 0 || evaluation > 5) {
            throw new IllegalArgumentException("A avaliação deve estar entre 0 e 5.");
        }
    }

}
