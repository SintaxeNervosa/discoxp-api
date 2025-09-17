package com.github.sintaxenervosa.discoxp.validations.img;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.github.sintaxenervosa.discoxp.repository.ImgProductRepository;

@Component
public class DefaultProductImageValidator implements UniqueNameValidator, ImgProductValidator{
    private ImgProductRepository imgProductRepository;

    public DefaultProductImageValidator(ImgProductRepository imgProductRepository) {
        this.imgProductRepository = imgProductRepository;
    }

    @Override
    public String validateInsertImg(MultipartFile request) {
        if (request == null || request.isEmpty()) {
            throw new IllegalArgumentException("Arquivo não pode ser nulo ou vazio");
        }
        
        
        //ver se é uma imagem
        String contentType = request.getContentType();
        if (contentType == null || !contentType.startsWith("image/")) {
            throw new IllegalArgumentException("Arquivo deve ser uma imagem");
        }
        
        return generateUniqueName(request);
    }
}

