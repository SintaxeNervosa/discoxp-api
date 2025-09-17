package com.github.sintaxenervosa.discoxp.validations.img;

import java.time.Instant;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public interface UniqueNameValidator {
    
    default String generateUniqueName(MultipartFile request) {

        String uuid = UUID.randomUUID().toString();

        long timestamp = Instant.now().toEpochMilli();

        return "img_" + request.getOriginalFilename() +  "_" + uuid + "_" + timestamp ;
    }

}
