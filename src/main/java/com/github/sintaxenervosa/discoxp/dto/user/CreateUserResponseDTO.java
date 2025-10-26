package com.github.sintaxenervosa.discoxp.dto.user;

import com.github.sintaxenervosa.discoxp.model.Group;
import com.github.sintaxenervosa.discoxp.model.User;

public record CreateUserResponseDTO(String id, Group group) {
    public static CreateUserResponseDTO fromEntity(User user) {
        return new CreateUserResponseDTO(user.getId().toString(), user.getGroupEnum());
    }
}
