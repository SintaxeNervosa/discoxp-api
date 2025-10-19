package com.github.sintaxenervosa.discoxp.dto.client;

public record ExistsEmailResponseDTO(boolean exists) {
    public static ExistsEmailResponseDTO fromEntity(boolean exists) {
        return new ExistsEmailResponseDTO(exists);
    }
}
