package com.github.sintaxenervosa.discoxp.service.unit;

import com.github.sintaxenervosa.discoxp.dto.address.ProductAndQuantityRequestDTO;
import com.github.sintaxenervosa.discoxp.dto.order.OrderRequestDTO;
import com.github.sintaxenervosa.discoxp.exception.address.InvalidAddressException;
import com.github.sintaxenervosa.discoxp.exception.order.InvalidOrderException;
import com.github.sintaxenervosa.discoxp.exception.product.ProductNotFoundException;
import com.github.sintaxenervosa.discoxp.exception.user.UserNotFoundExeption;
import com.github.sintaxenervosa.discoxp.model.*;
import com.github.sintaxenervosa.discoxp.repository.*;
import com.github.sintaxenervosa.discoxp.service.OrderService;
import com.github.sintaxenervosa.discoxp.validations.order.DefaultOrderValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Spy
    private DefaultOrderValidator defaultOrderValidator;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private DeliveryAddressRepository deliveryAddressRepository;

    @Test
    public void orderCreatedSuccessfully() {
        Product product = new Product(1l, "zelda", 4d, "Zelda muito bom", BigDecimal.valueOf(200), 3);

        // simula o retorno do produto  ao chamar a repository de produto
        Mockito.when(productRepository.findById(1l)).thenReturn(Optional.of(product));

        User user = new User(1l, "Italo" ,"italo@gmail.com", "@Teste1234" , Group.CLIENT, LocalDate.of(1999, 03, 01), Gender.HOMEM);

        // simula o retorno do produto  ao chamar a repository de user
        Mockito.when(userRepository.findById(1l)).thenReturn(Optional.of(user));

        DeliveryAddress deliveryAddress = new DeliveryAddress(1l, "SP", "São Paulo", "Jardim Almeida Prado", null, "21" ,"Rua José Roberto Cotime", "04854250");

        // simula o retorno do produto  ao chamar a repository de DeliveryAddress
        Mockito.when(deliveryAddressRepository.findById(1l)).thenReturn(Optional.of(deliveryAddress));

        ProductAndQuantityRequestDTO productAndQuantityRequestDTO = new ProductAndQuantityRequestDTO(
                productRepository.findById(1l).get().getId().toString(),
                "1");

        List<ProductAndQuantityRequestDTO> productAndQuantityRequestDTOList = new ArrayList<>();
        productAndQuantityRequestDTOList.add(productAndQuantityRequestDTO);

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(
                userRepository.findById(1l).get().getId().toString(),
                PaymentMethod.PIX.toString(),
                "200",
                deliveryAddressRepository.findById(1l).get().getId().toString(),
                productAndQuantityRequestDTOList
        );

        // simula um retorno do objeto recebido (tipo jpa)
        Mockito.when(orderItemRepository.save(Mockito.any(OrderItem.class))).thenAnswer(
                item -> {
                    OrderItem orderItem = item.getArgument(0);
                    orderItem.setId(1l);
                    return orderItem;
                });

        // simula um retorno do objeto recebido (tipo jpa)
        Mockito.when(orderRepository.save(Mockito.any(Order.class))).thenAnswer(
                order -> {
                    Order newOrder = order.getArgument(0);
                    newOrder.setOrderId(1);
                    return newOrder;
                }
        );

        try {
            orderService.addOrder(orderRequestDTO);
        }catch (InvalidOrderException e){
            fail("Não deveria lançar uma excessão.");
        }
    }

    @Test
    public void insufficientQuantity() {
        Product product = new Product(1l, "zelda", 4d, "Zelda muito bom", BigDecimal.valueOf(200), 3);

        // simula o retorno do produto  ao chamar a repository de produto
        Mockito.when(productRepository.findById(1l)).thenReturn(Optional.of(product));

        User user = new User(1l, "Italo" ,"italo@gmail.com", "@Teste1234" , Group.CLIENT, LocalDate.of(1999, 03, 01), Gender.HOMEM);

        // simula o retorno do produto  ao chamar a repository de user
        Mockito.when(userRepository.findById(1l)).thenReturn(Optional.of(user));

        DeliveryAddress deliveryAddress = new DeliveryAddress(1l, "SP", "São Paulo", "Jardim Almeida Prado", null, "21" ,"Rua José Roberto Cotime", "04854250");

        // simula o retorno do produto  ao chamar a repository de DeliveryAddress
        Mockito.when(deliveryAddressRepository.findById(1l)).thenReturn(Optional.of(deliveryAddress));

        ProductAndQuantityRequestDTO productAndQuantityRequestDTO = new ProductAndQuantityRequestDTO(
                productRepository.findById(1l).get().getId().toString(),
                "4");

        List<ProductAndQuantityRequestDTO> productAndQuantityRequestDTOList = new ArrayList<>();
        productAndQuantityRequestDTOList.add(productAndQuantityRequestDTO);

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(
                userRepository.findById(1l).get().getId().toString(),
                PaymentMethod.PIX.toString(),
                "200",
                deliveryAddressRepository.findById(1l).get().getId().toString(),
                productAndQuantityRequestDTOList
        );

        assertThrows(InvalidOrderException.class, () -> orderService.addOrder(orderRequestDTO));
    }

    @Test
    public void invalidPaymentMethod() {
        Product product = new Product(1l, "zelda", 4d, "Zelda muito bom", BigDecimal.valueOf(200), 3);

        // simula o retorno do produto  ao chamar a repository de produto
        Mockito.when(productRepository.findById(1l)).thenReturn(Optional.of(product));

        User user = new User(1l, "Italo" ,"italo@gmail.com", "@Teste1234" , Group.CLIENT, LocalDate.of(1999, 03, 01), Gender.HOMEM);

        // simula o retorno do produto  ao chamar a repository de user
        Mockito.when(userRepository.findById(1l)).thenReturn(Optional.of(user));

        DeliveryAddress deliveryAddress = new DeliveryAddress(1l, "SP", "São Paulo", "Jardim Almeida Prado", null, "21" ,"Rua José Roberto Cotime", "04854250");

        // simula o retorno do produto  ao chamar a repository de DeliveryAddress
        Mockito.when(deliveryAddressRepository.findById(1l)).thenReturn(Optional.of(deliveryAddress));

        ProductAndQuantityRequestDTO productAndQuantityRequestDTO = new ProductAndQuantityRequestDTO(
                productRepository.findById(1l).get().getId().toString(),
                "1");

        List<ProductAndQuantityRequestDTO> productAndQuantityRequestDTOList = new ArrayList<>();
        productAndQuantityRequestDTOList.add(productAndQuantityRequestDTO);

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(
                userRepository.findById(1l).get().getId().toString(),
                null,
                "200",
                deliveryAddressRepository.findById(1l).get().getId().toString(),
                productAndQuantityRequestDTOList
        );

        assertThrows(InvalidOrderException.class, () -> orderService.addOrder(orderRequestDTO));
    }

    @Test
    public void invalidFreight() {
        Product product = new Product(1l, "zelda", 4d, "Zelda muito bom", BigDecimal.valueOf(200), 3);

        // simula o retorno do produto  ao chamar a repository de produto
        Mockito.when(productRepository.findById(1l)).thenReturn(Optional.of(product));

        User user = new User(1l, "Italo" ,"italo@gmail.com", "@Teste1234" , Group.CLIENT, LocalDate.of(1999, 03, 01), Gender.HOMEM);

        // simula o retorno do produto  ao chamar a repository de user
        Mockito.when(userRepository.findById(1l)).thenReturn(Optional.of(user));

        DeliveryAddress deliveryAddress = new DeliveryAddress(1l, "SP", "São Paulo", "Jardim Almeida Prado", null, "21" ,"Rua José Roberto Cotime", "04854250");

        // simula o retorno do produto  ao chamar a repository de DeliveryAddress
        Mockito.when(deliveryAddressRepository.findById(1l)).thenReturn(Optional.of(deliveryAddress));

        ProductAndQuantityRequestDTO productAndQuantityRequestDTO = new ProductAndQuantityRequestDTO(
                productRepository.findById(1l).get().getId().toString(),
                "1");

        List<ProductAndQuantityRequestDTO> productAndQuantityRequestDTOList = new ArrayList<>();
        productAndQuantityRequestDTOList.add(productAndQuantityRequestDTO);

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(
                userRepository.findById(1l).get().getId().toString(),
                PaymentMethod.PIX.toString(),
                "-1",
                deliveryAddressRepository.findById(1l).get().getId().toString(),
                productAndQuantityRequestDTOList
        );

        assertThrows(InvalidOrderException.class, () -> orderService.addOrder(orderRequestDTO));
    }

    @Test
    public void invalidDeliveryAddress() {
        Product product = new Product(1l, "zelda", 4d, "Zelda muito bom", BigDecimal.valueOf(200), 3);

        // simula o retorno do produto  ao chamar a repository de produto
        Mockito.when(productRepository.findById(1l)).thenReturn(Optional.of(product));

        User user = new User(1l, "Italo" ,"italo@gmail.com", "@Teste1234" , Group.CLIENT, LocalDate.of(1999, 03, 01), Gender.HOMEM);

        // simula o retorno do produto  ao chamar a repository de user
        Mockito.when(userRepository.findById(1l)).thenReturn(Optional.of(user));

        ProductAndQuantityRequestDTO productAndQuantityRequestDTO = new ProductAndQuantityRequestDTO(
                productRepository.findById(1l).get().getId().toString(),
                "1");

        List<ProductAndQuantityRequestDTO> productAndQuantityRequestDTOList = new ArrayList<>();
        productAndQuantityRequestDTOList.add(productAndQuantityRequestDTO);

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(
                userRepository.findById(1l).get().getId().toString(),
                PaymentMethod.PIX.toString(),
                "1",
                "2",
                productAndQuantityRequestDTOList
        );

        assertThrows(InvalidAddressException.class, () -> orderService.addOrder(orderRequestDTO));
    }

    @Test
    public void invalidProduct() {
        User user = new User(1l, "Italo" ,"italo@gmail.com", "@Teste1234" , Group.CLIENT, LocalDate.of(1999, 03, 01), Gender.HOMEM);

        // simula o retorno do usuário ao chamar a repository de user
        Mockito.when(userRepository.findById(1l)).thenReturn(Optional.of(user));

        DeliveryAddress deliveryAddress = new DeliveryAddress(1l, "SP", "São Paulo", "Jardim Almeida Prado", null, "21" ,"Rua José Roberto Cotime", "04854250");

        // simula o retorno do endereço  ao chamar a repository de DeliveryAddress
        Mockito.when(deliveryAddressRepository.findById(1l)).thenReturn(Optional.of(deliveryAddress));

        ProductAndQuantityRequestDTO productAndQuantityRequestDTO = new ProductAndQuantityRequestDTO(
                "2",
                "1");

        List<ProductAndQuantityRequestDTO> productAndQuantityRequestDTOList = new ArrayList<>();
        productAndQuantityRequestDTOList.add(productAndQuantityRequestDTO);

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(
                userRepository.findById(1l).get().getId().toString(),
                PaymentMethod.PIX.toString(),
                "10",
                deliveryAddressRepository.findById(1l).get().getId().toString(),
                productAndQuantityRequestDTOList
        );

        assertThrows(ProductNotFoundException.class, () -> orderService.addOrder(orderRequestDTO));
    }

    @Test
    public void invalidUser() {
        Product product = new Product(1l, "zelda", 4d, "Zelda muito bom", BigDecimal.valueOf(200), 3);

        // simula o retorno do produto  ao chamar a repository de produto
        Mockito.when(productRepository.findById(1l)).thenReturn(Optional.of(product));

        DeliveryAddress deliveryAddress = new DeliveryAddress(1l, "SP", "São Paulo", "Jardim Almeida Prado", null, "21" ,"Rua José Roberto Cotime", "04854250");

        // simula o retorno do produto  ao chamar a repository de DeliveryAddress
        Mockito.when(deliveryAddressRepository.findById(1l)).thenReturn(Optional.of(deliveryAddress));

        ProductAndQuantityRequestDTO productAndQuantityRequestDTO = new ProductAndQuantityRequestDTO(
                productRepository.findById(1l).get().getId().toString(),
                "1");

        List<ProductAndQuantityRequestDTO> productAndQuantityRequestDTOList = new ArrayList<>();
        productAndQuantityRequestDTOList.add(productAndQuantityRequestDTO);

        OrderRequestDTO orderRequestDTO = new OrderRequestDTO(
                "2",
                PaymentMethod.PIX.toString(),
                "200",
                deliveryAddressRepository.findById(1l).get().getId().toString(),
                productAndQuantityRequestDTOList
        );

        assertThrows(UserNotFoundExeption.class, () -> orderService.addOrder(orderRequestDTO));
    }
}