package com.github.sintaxenervosa.discoxp.dto.client;

public record ExistsCpfResponseDTO(boolean exists) {
    public static ExistsCpfResponseDTO fromEntity(boolean exists) {
        return new ExistsCpfResponseDTO(exists);
    }
}
