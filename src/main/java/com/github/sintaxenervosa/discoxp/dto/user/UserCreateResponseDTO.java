package com.github.sintaxenervosa.discoxp.dto.user;

import com.github.sintaxenervosa.discoxp.model.Group;
import com.github.sintaxenervosa.discoxp.model.User;

public record UserCreateResponseDTO (String id, Group group) {
    public UserCreateResponseDTO fromEntity(User user) {
        return new UserCreateResponseDTO(user.getId().toString(), user.getGroupEnum());
    }
}
