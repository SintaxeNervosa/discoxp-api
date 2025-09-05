package com.github.sintaxenervosa.discoxp.dto;

import com.github.sintaxenervosa.discoxp.model.Group;
import com.github.sintaxenervosa.discoxp.model.User;

public record LoginResponseDto(Long id, Group group) {
    public static LoginResponseDto fromEntity(User user){
        return new LoginResponseDto(user.getId(), user.getGroupEnum());
    }
}
