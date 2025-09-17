package com.github.sintaxenervosa.discoxp.validations.product;

import com.github.sintaxenervosa.discoxp.validations.ValidationErrorRegistry;

public interface EvaluationValidation {

    default void validateEvaluationProduct(double evaluation) {
        if (evaluation < 1.0 || evaluation > 5.0) {
            ValidationErrorRegistry.addError("A avaliação deve estar entre 1.0 e 5.0.");
            return;
        }

        if ((evaluation * 10) % 5 != 0) {
            ValidationErrorRegistry.addError("A avaliação deve variar de 0.5 em 0.5.");
        }
    }
}
