package com.github.sintaxenervosa.discoxp.controller;

import java.net.http.HttpHeaders;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.naming.InvalidNameException;

import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.sintaxenervosa.discoxp.dto.imgProduct.ImgProductResponseDTO;
import com.github.sintaxenervosa.discoxp.model.ImageProduct;
import com.github.sintaxenervosa.discoxp.repository.ImgProductRepository;
import com.github.sintaxenervosa.discoxp.service.ImgProductService;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;

@RequiredArgsConstructor
@RestController
public class ImgController {
    private final Tika tika = new Tika();// TIKA!!!!!!!!!
    private final ImgProductService imgProductService;
    private final ImgProductRepository imgProductRepository;

    @PostMapping("/images/{productId}")
    public ResponseEntity<String> uparImages(@PathVariable Long productId,
            @RequestParam("file") List<MultipartFile> files) {
        try {
            imgProductService.addImages(productId, files);
            return ResponseEntity.ok("Imagens adicionadas com sucesso");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro em salvar imagens: " + e.getMessage());
        }
    }

    @GetMapping("/images/{imageId}/file")
    public ResponseEntity<byte[]> getImageFile(@PathVariable Long imageId) {
        try {
            Optional<ImageProduct> imageOpt = imgProductRepository.findById(imageId);

            if (imageOpt.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            ImageProduct image = imageOpt.get();
            byte[] imageData = image.getImageData();

            if (imageData == null || imageData.length == 0) {
                return ResponseEntity.notFound().build();
            }

            String ContentType = detectContentType(imageData);

            return ResponseEntity.ok()
                    .header(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION,
                            "inline; filename=\"" + image.getName() + "\"")
                    .contentType(MediaType.parseMediaType(ContentType))
                    .body(image.getImageData());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/product/{productId}/images")
    public ResponseEntity<List<ImgProductResponseDTO>> getProductImages(@PathVariable Long productId) {
        try {
            List<ImageProduct> images = imgProductService.allImagesByIdProduct(productId);

            if (images.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            ///PRocessar imgs para incluir dats binarios
            List<ImgProductResponseDTO> imaDtos = images.stream()
                    .map(image -> {
                        ImgProductResponseDTO dto = new ImgProductResponseDTO();
                        dto.setId(image.getId());
                        dto.setName(image.getName());

                        // adiciona data em base64
                        if (image.getImageData() != null && image.getImageData().length > 0) {
                            String contentType = detectContentType(image.getImageData());
                            dto.setImageData("data:" + contentType + ";base64," +
                                    Base64.getEncoder().encodeToString(image.getImageData()));
                        } else {
                            System.out.println("Não funfou");
                        }
                        return dto;
                    }).collect(Collectors.toList());

            return ResponseEntity.ok(imaDtos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Deletar
    @DeleteMapping("/product/{productId}/images/{idImageProduct}")
    public ResponseEntity<?> deleteProductImage(
            @PathVariable Long productId, @PathVariable Long idImageProduct) {
        try {
            imgProductService.deleteProductImage(productId, idImageProduct);
            return ResponseEntity.ok("Imagem removida com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/product/{productId}/images")
    public ResponseEntity<?> deleteAllProductImage(@PathVariable String productId) {
        try {
            imgProductService.deleteAllProductImage(productId);
            return ResponseEntity.ok("Imagem removida com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private String detectContentType(byte[] imageData) {
        try {
            return tika.detect(imageData);
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }
}
