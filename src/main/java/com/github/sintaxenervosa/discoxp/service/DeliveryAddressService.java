package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.address.AddressResponseDTO;
import com.github.sintaxenervosa.discoxp.dto.address.ChangeFavoriteAddressRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.exception.user.UserNotFoundExeption;
import com.github.sintaxenervosa.discoxp.model.DeliveryAddress;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.DeliveryAddressRepository;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryAddressService {

    private final DeliveryAddressRepository deliveryAddressRepository;
    private final UserRepository userRepository;

    public DeliveryAddress getDeliveryAddressById(String id){
        long idParseLong;

        try {
            idParseLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidUserDataException("ID inválido.");
        }

        return  deliveryAddressRepository.findById(idParseLong).get();
    }

    public List<AddressResponseDTO> getAllDeliveryAddressesByUserId(String id){
        if(id == null || id == null) {
            throw new InvalidUserDataException("Informe os campos");
        }

        long userIdParseLong;

        try {
            userIdParseLong = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidUserDataException("ID inválido.");
        }

        User user = userRepository.findById(userIdParseLong).orElseThrow(
                () -> new UserNotFoundExeption("Usuário não encontrado."));

        List<DeliveryAddress> addressList = deliveryAddressRepository.findAllByUser(user);

        List<AddressResponseDTO> parseAddresToResponseDTO = addressList
                .stream()
                .map(AddressResponseDTO::fromEntity).toList();

        return parseAddresToResponseDTO;
    };

    public void changeFavoriteAddress(ChangeFavoriteAddressRequestDTO deliveryAddress){
        System.out.println(deliveryAddress.addressId());
        System.out.println(deliveryAddress.clientId());
        if(deliveryAddress.clientId() == null || deliveryAddress.addressId() == null) {
                throw new InvalidUserDataException("Informe os campos");
        }

        long userIdParseLong, deliveryAddressParseLong;

        try {
            userIdParseLong = Long.parseLong(deliveryAddress.clientId());
            deliveryAddressParseLong = Long.parseLong(deliveryAddress.addressId());
        } catch (NumberFormatException e) {
            throw new InvalidUserDataException("ID inválido.");
        }

        User user = userRepository.findById(userIdParseLong).orElseThrow(
                () -> new UserNotFoundExeption("Usuário não encontrado"));

        if(!deliveryAddressRepository.existsById(deliveryAddressParseLong)) { return; }

        List<DeliveryAddress> deliveryAddressList = deliveryAddressRepository.findAllByUser(user);

        for(DeliveryAddress d : deliveryAddressList) {
            if(!d.getId().equals(deliveryAddressParseLong)) {
                d.setFavorite(false);
                deliveryAddressRepository.save(d);
                continue;
            }

            d.setFavorite(true);
            deliveryAddressRepository.save(d);
        }
    }

    public AddressResponseDTO getFavoriteDeliveryAddressesByUserId(String id) {
        User user = userRepository.findById(Long.parseLong(id)).orElseThrow(() ->
                new UserNotFoundExeption("Usuário não encontrado"));

        DeliveryAddress deliveryAddress = deliveryAddressRepository.findByUserAndIsFavoriteTrue(user);
        return AddressResponseDTO.fromEntity(deliveryAddress);
    }
}
