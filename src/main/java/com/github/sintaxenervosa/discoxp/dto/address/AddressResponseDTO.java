package com.github.sintaxenervosa.discoxp.dto.address;

import com.github.sintaxenervosa.discoxp.model.DeliveryAddress;

public record AddressResponseDTO(Long id, String street, String neighborhood, String uf, String cep, boolean isFavorite) {
    public static AddressResponseDTO fromEntity(DeliveryAddress deliveryAddress) {
        return new AddressResponseDTO(
                deliveryAddress.getId(),
                deliveryAddress.getStreet(),
                deliveryAddress.getNeighborhood(),
                deliveryAddress.getUf(),
                deliveryAddress.getCep(),
                deliveryAddress.isFavorite()
        );
    }
}
