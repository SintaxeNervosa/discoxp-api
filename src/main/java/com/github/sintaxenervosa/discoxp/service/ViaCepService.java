package com.github.sintaxenervosa.discoxp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class ViaCepService {
    private static final String VIA_CEP_URL = "https://viacep.com.br/ws/{cep}/json/";

    public Map<String, Object> buscarEnderecoPorCep(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> resposta = restTemplate.getForObject(VIA_CEP_URL, Map.class, cep);

        if (resposta == null || resposta.containsKey("erro")) {
            throw new IllegalArgumentException("CEP inválido ou não encontrado");
        }

        return resposta;
    }
}
