package com.github.sintaxenervosa.discoxp.validations.img;

import org.springframework.web.multipart.MultipartFile;

import com.github.sintaxenervosa.discoxp.repository.ImgProductRepository;

public class DefaultProductImageValidator implements UniqueNameValidator {
    private ImgProductRepository imgProductRepository;

    public DefaultProductImageValidator(ImgProductRepository imgProductRepository) {
        this.imgProductRepository = imgProductRepository;
    }

    @Override
    public String ImgProductValidator(MultipartFile request) {
        return generateUniqueName(request);
    }
}
