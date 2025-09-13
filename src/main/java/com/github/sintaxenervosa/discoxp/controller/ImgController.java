package com.github.sintaxenervosa.discoxp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.github.sintaxenervosa.discoxp.service.ImgProductService;

import jakarta.servlet.Servlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ImgController {
    private final ImgProductService imgProductService;

    // ""
    @PostMapping("/images/{productId}")
    public ResponseEntity<String> uparImages(
            @PathVariable Long productId,
            HttpServletRequest request,
            @RequestParam(value = "images", required = false) List<MultipartFile> images) {
        System.out.println("\nTipo: " + request.getContentType());

        if (images == null) {
            return ResponseEntity.badRequest().body("Nada");
        }
        System.out.println("images: " + images.size());
        try {

            imgProductService.addImages(productId, images);
            return ResponseEntity.ok("Imagens adicionadas com sucesso");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro em salvar imagens");
        }
    }
}

// @RequiredArgsConstructor
// @RestController
// @RequestMapping("images")
// public class ImgController {
// private final ImgProductService imgProductService;
// //"/{productId}/images"
// @PostMapping(value = "/{productId}", consumes =
// org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
// public ResponseEntity<String> uparImages(
// @PathVariable Long productId,
// @RequestParam("images") List<MultipartFile> images) {
// try {
// imgProductService.addImages(productId, images);
// return ResponseEntity.ok("Imagens adicionadas com sucesso");
// } catch (Exception e) {
// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro em
// salvar imagens");
// }
// }
// }
