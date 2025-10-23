package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.address.AddressRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.address.AddressResponseDTO;
import com.github.sintaxenervosa.discoxp.model.Address;
import com.github.sintaxenervosa.discoxp.repository.AddressRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final ViaCepService viaCepService;

    public AddressService(AddressRepository addressRepository, ViaCepService viaCepService) {
        this.addressRepository = addressRepository;
        this.viaCepService = viaCepService;
    }

    @Transactional
    public AddressResponseDTO adicionarEndereco(AddressRequestDTO dto) {
        Map<String, Object> dadosCep = viaCepService.buscarEnderecoPorCep(dto.cep());

        Address endereco = Address.builder()
                .cep(dto.cep())
                .logradouro((String) dadosCep.get("logradouro"))
                .bairro((String) dadosCep.get("bairro"))
                .cidade((String) dadosCep.get("localidade"))
                .estado((String) dadosCep.get("uf"))
                .numero(dto.numero())
                .complemento(dto.complemento())
                .enderecoPadrao(false)
                .build();

        // Se for o primeiro endereço cadastrado, marca como padrão
        if (addressRepository.count() == 0) {
            endereco.setEnderecoPadrao(true);
        }

        addressRepository.save(endereco);
        return new AddressResponseDTO(endereco);
    }

    public List<AddressResponseDTO> listarEnderecos() {
        return addressRepository.findAllByOrderByIdAsc().stream()
                .map(AddressResponseDTO::new)
                .toList();
    }

    @Transactional
    public AddressResponseDTO editarEndereco(Long enderecoId, AddressRequestDTO dto) {
        Address endereco = addressRepository.findById(enderecoId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));

        Map<String, Object> dadosCep = viaCepService.buscarEnderecoPorCep(dto.cep());

        endereco.setCep(dto.cep());
        endereco.setLogradouro((String) dadosCep.get("logradouro"));
        endereco.setBairro((String) dadosCep.get("bairro"));
        endereco.setCidade((String) dadosCep.get("localidade"));
        endereco.setEstado((String) dadosCep.get("uf"));
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());

        addressRepository.save(endereco);
        return new AddressResponseDTO(endereco);
    }

    @Transactional
    public void definirEnderecoPadrao(Long enderecoId) {
        // Desmarca todos os outros endereços
        List<Address> enderecos = addressRepository.findAll();
        for (Address e : enderecos) {
            e.setEnderecoPadrao(false);
        }

        // Marca o endereço selecionado
        Address endereco = addressRepository.findById(enderecoId)
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        endereco.setEnderecoPadrao(true);

        addressRepository.saveAll(enderecos);
    }
}
