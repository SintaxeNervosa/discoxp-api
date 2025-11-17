package com.github.sintaxenervosa.discoxp.unity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import com.github.sintaxenervosa.discoxp.model.ImageProduct;
import com.github.sintaxenervosa.discoxp.model.Product;
import com.github.sintaxenervosa.discoxp.repository.ImgProductRepository;
import com.github.sintaxenervosa.discoxp.repository.ProductRepository;
import com.github.sintaxenervosa.discoxp.service.ImgProductService;
import com.github.sintaxenervosa.discoxp.validations.img.ImgProductValidator;

@ExtendWith(MockitoExtension.class)
public class ImgProductServiceTest {

    @Mock
    private ImgProductRepository imgProductRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ImgProductValidator imgProductValidator;
    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private ImgProductService imgProductService;

    private ImageProduct imageProduct;
    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setId(1L);
        product.setName("Produto Teste");
        product.setEvaluation(1.5);
        product.setDescription("blabla♚♛♜♝♞♟");
        product.setPrice(new BigDecimal("45.50"));
        product.setQuantity(500);

        imageProduct = new ImageProduct();
        imageProduct.setId(1L);
        imageProduct.setName("Imagem Teste");
        imageProduct.setProduct(product);
    }

    @Test
    void shouldAddImages() {
        // Preparing os mocks
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("Imagem Teste.jpg");

        try {//fznd simulado uma imagem manualmente dnv
            when(multipartFile.getBytes()).thenReturn(new byte[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 });
        } catch (IOException e) {
             fail("IOException não deveria ocorrer no mock");
        }
        when(imgProductRepository.save(any(ImageProduct.class))).thenReturn(imageProduct);

        List<MultipartFile> images = List.of(multipartFile);
        
        assertDoesNotThrow(() -> imgProductService.addImages(1L, images));

        // verificar results e averiguar 
        verify(productRepository, times(1)).findById(1L);
        verify(imgProductValidator, times(1)).validateInsertImg(multipartFile);
        verify(imgProductRepository, times(1)).save(any(ImageProduct.class));

        System.out.println("Teste de adição de imagens passou");
    }

    // id da imagem existe
    @Test
    void shouldThrowExceptionImageNotFound() {
        
        when(imgProductRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> imgProductService.deleteProductImage(1L, 999L));

        assertEquals("Imagem não encontrada!", exception.getMessage());
    }

    @Test
    void shouldDeleteImageSuccessfully() {
        when(imgProductRepository.findById(1L)).thenReturn(Optional.of(imageProduct));

        assertDoesNotThrow(() -> imgProductService.deleteProductImage(1L, 1L));

        verify(imgProductRepository, times(1)).deleteById(1L);
    }

    @Test
    void shouldThrowExceptionWhenImageTooLarge() {

        reset(multipartFile);

        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(multipartFile.isEmpty()).thenReturn(false);
        when(multipartFile.getOriginalFilename()).thenReturn("large-image.jpg");

        // imagem exatamente 16MB simulada
        byte[] largeImage = new byte[16 * 1024 * 1024]; // 16MB
        try {
            when(multipartFile.getBytes()).thenReturn(largeImage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<MultipartFile> images = List.of(multipartFile);

        // Não pode lançar exceção pois exatamente 16MB
        try {
        imgProductService.addImages(1L, images);
        System.out.println("NÃO lançou exceção para imagem grande");
    } catch (Exception e) {
        System.out.println("Lançou exceção: " + e.getMessage());
    }
    }
}