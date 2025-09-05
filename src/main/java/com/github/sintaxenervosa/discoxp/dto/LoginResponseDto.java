package com.github.sintaxenervosa.discoxp.dto;

import com.github.sintaxenervosa.discoxp.model.User;

public record LoginResponseDto(Long id) {
    public static LoginResponseDto fromEntity(User user){
        return new LoginResponseDto(user.getId());
    }
}
