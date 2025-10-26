package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.address.RequestAddressDTO;
import com.github.sintaxenervosa.discoxp.model.DeliveryAddress;
import com.github.sintaxenervosa.discoxp.service.ViaCepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/delivery-address")
public class DeliveryAddressController {

    private final ViaCepService viaCepService;

    public DeliveryAddressController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @PostMapping
    public ResponseEntity<HttpStatusCode> addAddress(@RequestBody RequestAddressDTO request) {
        viaCepService.fyndCepFromDelivery(request);
        return ResponseEntity.status(201).build();
    }
}