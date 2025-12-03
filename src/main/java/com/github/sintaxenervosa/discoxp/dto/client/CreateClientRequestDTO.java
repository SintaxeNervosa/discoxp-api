package com.github.sintaxenervosa.discoxp.dto.client;

import com.github.sintaxenervosa.discoxp.dto.address.RequestAddressDTO;
import io.micrometer.common.lang.Nullable;

import java.time.LocalDate;

public record CreateClientRequestDTO (
        String name,
        String email,
        String cpf,
        String password,
        LocalDate dateOfBirth,
        String gender,
        @Nullable RequestAddressDTO deliveryAddress,
        @Nullable RequestAddressDTO billingAddress
){}
