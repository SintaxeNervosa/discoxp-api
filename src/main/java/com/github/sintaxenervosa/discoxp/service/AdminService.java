package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.stockist.StockistRequestDto;
import com.github.sintaxenervosa.discoxp.model.Stockist;
import com.github.sintaxenervosa.discoxp.repository.StockistRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AdminService {

    private StockistRepository stockistRepository;

    public void create(StockistRequestDto  stockist) {
        Stockist stockistEntity = new Stockist(stockist);
        stockistRepository.save(stockistEntity);
    }
}
