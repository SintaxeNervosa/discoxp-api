package com.github.sintaxenervosa.discoxp.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.sintaxenervosa.discoxp.model.ImageProduct;
import com.github.sintaxenervosa.discoxp.model.Product;
import com.github.sintaxenervosa.discoxp.repository.ImgProductRepository;
import com.github.sintaxenervosa.discoxp.repository.ProductRepository;

import javax.imageio.IIOException;

@Service
public class ImgProductService {
    private ImgProductRepository imgProductRepository;
    private ProductRepository productRepository;

    public ImgProductService(ImgProductRepository imgProductRepository, ProductRepository productRepository) {
        this.imgProductRepository = imgProductRepository;
        this.productRepository = productRepository;
    }

    public void addImages(Long productId, List<MultipartFile> images){
        Product produto = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("Produto Não encontrado ou não existe"));

        for(MultipartFile image : images){
//            if (image.isEmpty()) {
//                continue; // vai pular files vazios
//            }

            //validar nome unico da imagem 

            //comprimir imagem

            ImageProduct imageProduct = new ImageProduct();
            try {
                imageProduct.setImageData(image.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Error encontrando image");
            }

            imageProduct.setName(image.getOriginalFilename());
            imageProduct.setProduct(produto);

            System.out.println(image.getOriginalFilename());

            imgProductRepository.save(imageProduct);
        }
    }

    public List<ImageProduct> allImagesByIdProduct(Long productId){
        return imgProductRepository.findByProduct_Id(productId);
    }
}
