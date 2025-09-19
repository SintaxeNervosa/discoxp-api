package com.github.sintaxenervosa.discoxp.dto.imgProduct;

import com.github.sintaxenervosa.discoxp.model.ImageProduct;

public class ImgProductResponseDTO {
    private Long id;
    private String name;
    private String imageUrl; // URL para acessar o arquivo da imagem
    
    // Construtor
    public ImgProductResponseDTO() {}
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    // Método fromEntity atualizado
    public static ImgProductResponseDTO fromEntity(ImageProduct image) {
        ImgProductResponseDTO dto = new ImgProductResponseDTO();
        dto.setId(image.getId());
        dto.setName(image.getName());
        dto.setImageUrl("/api/images/" + image.getId() + "/file"); // URL para o endpoint de arquivo
        return dto;
    }
}