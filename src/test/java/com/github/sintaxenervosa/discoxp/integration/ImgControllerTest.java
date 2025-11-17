package com.github.sintaxenervosa.discoxp.integration;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import com.github.sintaxenervosa.discoxp.controller.ImgController;
import com.github.sintaxenervosa.discoxp.service.ImgProductService;

@ExtendWith(MockitoExtension.class)
public class ImgControllerTest {

    @Mock
    private ImgProductService imgProductService;

    @InjectMocks
    private ImgController imgController;

    @Test
    void shouldUploadImageSuccessfully() {
        // Arrange Preparação / file simulado
        MockMultipartFile file = new MockMultipartFile(
            "file", 
            "test-image.jpg", 
            "image/jpeg", 
            "conteudo".getBytes()
        );
        List<MultipartFile> files = List.of(file);

        // Act Execução / Chamando o método real do control,q usa service MOCKADO
        ResponseEntity<String> response = imgController.uparImages(1L, files);

        // Assert / Verificação
        assertEquals(HttpStatus.OK, response.getStatusCode());// respostas esperadas
        assertEquals("Imagens adicionadas com sucesso", response.getBody());
    }

    @Test
    void shouldDeleteImageSuccessfully() {
        // Act
        ResponseEntity<?> response = imgController.deleteProductImage(1L, 1L);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Imagem removida com sucesso!", response.getBody());
    }
}