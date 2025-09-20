package com.github.sintaxenervosa.discoxp.dto.imgProduct;

import com.github.sintaxenervosa.discoxp.model.ImageProduct;

public class ImgProductResponseDTO {
    private Long id;
    private String name;
    private String imageData; //Base64
    

     public ImgProductResponseDTO() {}
    
    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getImageData() { return imageData; }
    public void setImageData(String imageData) { this.imageData = imageData; }
}
