package com.github.sintaxenervosa.discoxp.dto.user;

import com.github.sintaxenervosa.discoxp.model.User;
import lombok.Getter;

@Getter
public class UserDto {

    private final Long id;
    private final String name;
    private final String email;
    private final String groupEnum;
    private final boolean status;
    
    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.groupEnum = user.getGroupEnum().toString();
        this.status = user.isStatus();


    }
}
