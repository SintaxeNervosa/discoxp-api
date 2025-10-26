package com.github.sintaxenervosa.discoxp.repository;

import com.github.sintaxenervosa.discoxp.exception.address.InvalidAddressException;
import com.github.sintaxenervosa.discoxp.model.DeliveryAddress;
import com.github.sintaxenervosa.discoxp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface DeliveryAddressRepository extends JpaRepository<DeliveryAddress, Long> {
    boolean existsByUserIdAndCepAndNumber(Long userId, String cep, String numero);

    List<DeliveryAddress> findAllByUser(User user);
}
