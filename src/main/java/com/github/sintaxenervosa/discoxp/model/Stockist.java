package com.github.sintaxenervosa.discoxp.model;

import com.github.sintaxenervosa.discoxp.dto.stockist.StockistRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "tb_stockist")
@NoArgsConstructor
@Data
@Entity()
public class Stockist extends User {
    @Column(nullable = false, unique = true, length = 11)
    private String cpf;

    public Stockist(StockistRequestDto stockist) {
        super(stockist.name(), stockist.email(), stockist.password(), Group.STOCKIST);
        cpf = stockist.cpf();
    }
}
