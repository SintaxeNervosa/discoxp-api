package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.model.Stockist;
import com.github.sintaxenervosa.discoxp.model.User;
import com.github.sintaxenervosa.discoxp.repository.StockistRepository;
import org.springframework.stereotype.Service;
@Service
public class UserService {

    private StockistRepository stockistRepository;
    public UserService(StockistRepository stockistRepository) {
        this.stockistRepository = stockistRepository;
    }
}