package com.github.sintaxenervosa.discoxp.validations.img;

import java.nio.channels.MulticastChannel;

import org.springframework.web.multipart.MultipartFile;

import com.github.sintaxenervosa.discoxp.model.ImageProduct;

public interface  ImgProductValidator {
    String valideteInsertImg(MultipartFile request);
}
