package com.github.sintaxenervosa.discoxp.validations.img;

import org.springframework.web.multipart.MultipartFile;

public interface ImgProductValidator {
    String validateInsertImg(MultipartFile request);
}