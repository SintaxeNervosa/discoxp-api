package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.address.RequestAddressDTO;
import com.github.sintaxenervosa.discoxp.exception.address.DuplicateAddressException;
import com.github.sintaxenervosa.discoxp.exception.address.InvalidAddressException;
import com.github.sintaxenervosa.discoxp.exception.user.UserNotFoundExeption;
import com.github.sintaxenervosa.discoxp.model.BillingAddress;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.BillingAddressRepository;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ViaCepService {

    private final UserRepository userRepository;
    private final BillingAddressRepository billingAddressRepository;

    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    public void findByCepFromAddress(RequestAddressDTO request) {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> resposta;
        try {
            resposta = restTemplate.getForObject(VIA_CEP_URL, Map.class, request.cep());
        } catch (HttpClientErrorException e) {
            throw new InvalidAddressException("CEP inválido ou não encontrado");
        }

        BillingAddress billingAddress = new BillingAddress();
        billingAddress.setCep(resposta.get("cep").toString());
        billingAddress.setStreet(resposta.get("logradouro").toString());
        billingAddress.setNumber(request.number());
        billingAddress.setCity(request.complement());
        billingAddress.setNeighborhood(resposta.get("bairro").toString());
        billingAddress.setCity(resposta.get("localidade").toString());
        billingAddress.setEstado(resposta.get("uf").toString());

        User user = userRepository.findById(Long.parseLong(request.id()))
                .orElseThrow(() -> new UserNotFoundExeption("Usuário não encontrado"));

        billingAddress.setUser(user);

        // salva o endereço de entrega

        try {
            BillingAddress newBillingAddress = billingAddressRepository.save(billingAddress);

            // adiciona o endereço de faturamento na tabela de cliente
            user.setBillingAddress(newBillingAddress);
            userRepository.save(user);
        } catch (Exception error) {
            throw new DuplicateAddressException();
        }
    }
}
