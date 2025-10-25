package com.github.sintaxenervosa.discoxp.dto.address;

import io.micrometer.common.lang.Nullable;

public record RequestAddressDTO(String id, String cep, String number, @Nullable String complement) { }
