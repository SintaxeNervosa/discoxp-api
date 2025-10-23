package com.github.sintaxenervosa.discoxp.controller;

import com.github.sintaxenervosa.discoxp.dto.address.AddressRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.address.AddressResponseDTO;
import com.github.sintaxenervosa.discoxp.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    // Criar um novo endereço
    @PostMapping
    public ResponseEntity<AddressResponseDTO> criarEndereco(@Valid @RequestBody AddressRequestDTO dto) {
        return ResponseEntity.ok(addressService.adicionarEndereco(dto));
    }

    // Listar todos os endereços
    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> listarEnderecos() {
        return ResponseEntity.ok(addressService.listarEnderecos());
    }

    // Editar um endereço existente
    @PutMapping("/{enderecoId}")
    public ResponseEntity<AddressResponseDTO> editarEndereco(
            @PathVariable Long enderecoId,
            @Valid @RequestBody AddressRequestDTO dto) {
        return ResponseEntity.ok(addressService.editarEndereco(enderecoId, dto));
    }

    // Definir um endereço como padrão
    @PutMapping("/padrao/{enderecoId}")
    public ResponseEntity<Void> definirEnderecoPadrao(@PathVariable Long enderecoId) {
        addressService.definirEnderecoPadrao(enderecoId);
        return ResponseEntity.noContent().build();
    }
}
