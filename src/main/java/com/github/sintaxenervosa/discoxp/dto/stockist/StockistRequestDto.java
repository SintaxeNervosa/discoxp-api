package com.github.sintaxenervosa.discoxp.dto.stockist;

import com.github.sintaxenervosa.discoxp.model.Group;

public record StockistRequestDto(String name, String email, String password, Group group,String cpf) {

}
