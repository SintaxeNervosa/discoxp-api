package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.address.RequestAddressDTO;
import com.github.sintaxenervosa.discoxp.dto.user.CreateUserRequestDTO;
import com.github.sintaxenervosa.discoxp.service.ViaCepService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing-address")
public class BillingAddressController {

    private final ViaCepService viaCepService;

    public BillingAddressController(ViaCepService viaCepService) {
        this.viaCepService = viaCepService;
    }

    @PostMapping
    public ResponseEntity<HttpStatusCode> addAddress(@RequestBody RequestAddressDTO request) {
        viaCepService.findByCepFromAddress(request);
        return ResponseEntity.ok(HttpStatus.CREATED);
    };
}
