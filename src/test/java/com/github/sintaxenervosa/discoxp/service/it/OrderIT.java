package com.github.sintaxenervosa.discoxp.service.it;

import com.github.sintaxenervosa.discoxp.dto.address.ProductAndQuantityRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.order.CreateOrderResponseDTO;
import com.github.sintaxenervosa.discoxp.dto.order.OrderRequestDTO;
import com.github.sintaxenervosa.discoxp.model.*;
import com.github.sintaxenervosa.discoxp.repository.DeliveryAddressRepository;
import com.github.sintaxenervosa.discoxp.repository.ProductRepository;
import com.github.sintaxenervosa.discoxp.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderIT {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private DeliveryAddressRepository deliveryAddressRepository;

    @BeforeEach
    public void init() {
        userRepository.save(new User("João", "47958777830", "joao@gmail.com", "@Teste1234", Group.CLIENT, LocalDate.of(2005, 01, 02), Gender.HOMEM));
        productRepository.save(new Product("Fifa 22", 4.5, "Fifa muito bom", BigDecimal.valueOf(100), 2));
        deliveryAddressRepository.save(new DeliveryAddress("SP", "São Paulo", "Jardim Almeida Prado" ,"", "21", "Rua José Roberto Cotime", "04854250"));
    }

    @Test
    public void createOrderSuccess() {
        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(
                "1",
                "PIX",
                "10",
                "1",
                List.of(new ProductAndQuantityRequestDTO("1", "1")));

        ResponseEntity<CreateOrderResponseDTO> response = restTemplate.postForEntity("/order", orderRequestDTO, CreateOrderResponseDTO.class);
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertEquals(response.getBody().id(), 1);
    }

}
