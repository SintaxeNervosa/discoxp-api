package com.github.sintaxenervosa.discoxp.service;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.sintaxenervosa.discoxp.model.ImageProduct;
import com.github.sintaxenervosa.discoxp.model.Product;
import com.github.sintaxenervosa.discoxp.repository.ImgProductRepository;
import com.github.sintaxenervosa.discoxp.repository.ProductRepository;
import com.github.sintaxenervosa.discoxp.validations.img.DefaultProductImageValidator;
import com.github.sintaxenervosa.discoxp.validations.img.ImgProductValidator;

@Service
public class ImgProductService {
    private ImgProductRepository imgProductRepository;
    private ProductRepository productRepository;
    private final DefaultProductImageValidator defaultImageValidator;
    private final ImgProductValidator imgProductValidator;

    public ImgProductService(ImgProductRepository imgProductRepository, ProductRepository productRepository, DefaultProductImageValidator defaultImageValidator, ImgProductValidator imgProductValidator) {
        this.imgProductRepository = imgProductRepository;
        this.productRepository = productRepository;
        this.defaultImageValidator = defaultImageValidator;
        this.imgProductValidator = imgProductValidator;
    }

    public void addImages(Long productId, List<MultipartFile> images) {
        Product produto = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto Não encontrado ou não existe"));

        for (MultipartFile image : images) {
            if (image.isEmpty()) {
                continue; // vai pular files vazios
            }

            ImageProduct imageProduct = new ImageProduct();
            // comprimir imagem

            try {
                // byte[] comprimida = compressImage(image, 0.7f);
                imageProduct.setImageData(image.getBytes());//
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("Error encontrando image");
            }

            System.out.println(image.getOriginalFilename());
              // validar nome unico da imagem
            String newUniqueName = imgProductValidator.validateInsertImg(image);

            imageProduct.setName(newUniqueName);
            imageProduct.setProduct(produto);

            System.out.println(newUniqueName);

            imgProductRepository.save(imageProduct);
        }
    }

    public List<ImageProduct> allImagesByIdProduct(Long productId) {
        return imgProductRepository.findByProduct_Id(productId);
    }

    private static byte[] compressImage(MultipartFile file, float compressQuality) throws IOException {
        BufferedImage iamge = ImageIO.read(file.getInputStream());

        ImageWriter writer = ImageIO.getImageWritersByFormatName("jpg").next();

        ImageWriteParam param = writer.getDefaultWriteParam();
        param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        param.setCompressionQuality(compressQuality); // Valor entre 0 e 1 (1 = melhor qualidade, 0 = maior compressão)

        ByteArrayOutputStream byteArraySaida = new ByteArrayOutputStream();
        ImageOutputStream imageSaida = ImageIO.createImageOutputStream(byteArraySaida);
        writer.setOutput(imageSaida);

        writer.write(null, new IIOImage(iamge, null, null), param);

        imageSaida.close();
        byteArraySaida.close();
        writer.dispose();

        return byteArraySaida.toByteArray();
    }


}
