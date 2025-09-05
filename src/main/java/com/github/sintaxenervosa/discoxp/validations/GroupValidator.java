package com.github.sintaxenervosa.discoxp.validations;

import com.github.sintaxenervosa.discoxp.exception.user.InvalidUserDataException;
import com.github.sintaxenervosa.discoxp.model.Group;

public interface GroupValidator {
    default void validateGroup(Group group) {
        if(group == null) { throw new InvalidUserDataException("Informe o grupo"); }

        boolean exists = false;

        for(Group g : Group.values()) {
            if(g.name().equals(group.name())) {
                exists = true;
            }
        }

        if(!exists) { throw new InvalidUserDataException("Grupo Inexistente"); }
    }
}
