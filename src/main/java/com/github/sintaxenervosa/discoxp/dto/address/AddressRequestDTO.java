package com.github.sintaxenervosa.discoxp.dto.address;

import com.github.sintaxenervosa.discoxp.validations.address.ValidCep;
import jakarta.validation.constraints.NotBlank;

public record AddressRequestDTO(
        @NotBlank(message = "O CEP é obrigatório")
        @ValidCep
        String cep,

        @NotBlank(message = "O número é obrigatório")
        String numero,

        String complemento
) {}
