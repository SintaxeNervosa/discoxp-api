package com.github.sintaxenervosa.discoxp.dto.address;

import com.github.sintaxenervosa.discoxp.model.Address;

public record AddressResponseDTO(
        Long id,
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String estado,
        String numero,
        String complemento,
        boolean enderecoPadrao
) {
    public AddressResponseDTO(Address address) {
        this(
                address.getId(),
                address.getCep(),
                address.getLogradouro(),
                address.getBairro(),
                address.getCidade(),
                address.getEstado(),
                address.getNumero(),
                address.getComplemento(),
                address.isEnderecoPadrao()
        );
    }
}
