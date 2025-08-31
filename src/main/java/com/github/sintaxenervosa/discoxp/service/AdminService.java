package com.github.sintaxenervosa.discoxp.service;

import com.github.sintaxenervosa.discoxp.dto.stockist.StockistRequestDto;
import com.github.sintaxenervosa.discoxp.model.Stockist;
import com.github.sintaxenervosa.discoxp.repository.StockistRepository;
import com.github.sintaxenervosa.discoxp.validations.StockistValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AdminService {

    private StockistRepository stockistRepository;
    private StockistValidator stockistValidator;

    public void createStockist(StockistRequestDto stockistDto) throws IllegalAccessException {
        Stockist stockistEntity = new Stockist(stockistDto);
        stockistValidator.validate(stockistEntity);

        System.out.println(stockistEntity.toString());
        stockistRepository.save(stockistEntity);
    }
}
