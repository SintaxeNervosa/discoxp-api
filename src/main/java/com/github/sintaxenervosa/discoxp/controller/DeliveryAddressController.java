package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.address.ChangeFavoriteAddressRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.address.RequestAddressDTO;
import com.github.sintaxenervosa.discoxp.model.DeliveryAddress;
import com.github.sintaxenervosa.discoxp.service.DeliveryAddressService;
import com.github.sintaxenervosa.discoxp.service.ViaCepService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery-address")
public class DeliveryAddressController {

    private final ViaCepService viaCepService;
    private final DeliveryAddressService deliveryAddressService;

    public DeliveryAddressController(ViaCepService viaCepService, DeliveryAddressService deliveryAddressService) {
        this.viaCepService = viaCepService;
        this.deliveryAddressService = deliveryAddressService;
    }

    @PostMapping
    public ResponseEntity<HttpStatusCode> addAddress(@RequestBody RequestAddressDTO request) {
        viaCepService.fyndCepFromDelivery(request);
        return ResponseEntity.status(201).build()   ;
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<DeliveryAddress>> getAllDeliveryAddressesByUserId(@PathVariable("userId") String id) {
        List<DeliveryAddress> deliveryAddressList = deliveryAddressService.getAllDeliveryAddressesByUserId(id);
        return ResponseEntity.status(200).body(deliveryAddressList) ;
    }

    @PutMapping
    public ResponseEntity<HttpStatusCode> updateFavoriteAddress(@RequestBody ChangeFavoriteAddressRequestDTO request) {
        deliveryAddressService.changeFavoriteAddress(request);
        return ResponseEntity.status(200).build() ;
    }
}