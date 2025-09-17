package com.github.sintaxenervosa.discoxp.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.sintaxenervosa.discoxp.dto.imgProduct.ImgProductResponseDTO;
import com.github.sintaxenervosa.discoxp.model.ImageProduct;
import com.github.sintaxenervosa.discoxp.service.ImgProductService;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ImgController {
    private final ImgProductService imgProductService;

    @PostMapping("/images/{productId}")
    public ResponseEntity<String> uparImages(@PathVariable Long productId,
            @RequestParam("file") List<MultipartFile> files) {
        try {
            imgProductService.addImages(productId, files);
            return ResponseEntity.ok("Imagens adicionadas com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro em salvar imagens: " + e.getMessage());
        }
    }

    @GetMapping("/product/{productId}/images")
    public ResponseEntity<List<ImgProductResponseDTO>> getProductImages(@PathVariable Long productId){
        try{
           List<ImageProduct> images = imgProductService.allImagesByIdProduct(productId);
           
           if (images.isEmpty()) {
                return ResponseEntity.noContent().build();
           }

           List<ImgProductResponseDTO> imaDtos = images.stream()
                .map(ImgProductResponseDTO::fromEntity)
                .collect(Collectors.toList());

                return ResponseEntity.ok(imaDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
