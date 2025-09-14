package com.github.sintaxenervosa.discoxp.validations.product;

public interface EvaluationValidation {

    default void validateEvaluationProduct(double evaluation) {
        if (evaluation < 1.0 || evaluation > 5.0) {
            throw new IllegalArgumentException("A avaliação deve estar entre 1.0 e 5.0.");
        }
        if ((evaluation * 10) % 5 != 0) {
            throw new IllegalArgumentException("A avaliação deve variar de 0.5 em 0.5.");
        }
    }
}
