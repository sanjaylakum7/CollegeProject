package com.image.Services;

import com.image.Entities.Product;
import com.image.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProductWithImages(String name, String price, MultipartFile imageFile, String path) throws IOException {
        byte[] imageData = imageFile.getBytes();
        Product product = new Product();
        product.setName(name);

        String imageName = imageFile.getOriginalFilename();
        String filePath = path + File.separator + imageName;

        File newFile = new File(path);
        if(!newFile.exists()) newFile.mkdir();
        Files.copy(imageFile.getInputStream(), Paths.get(filePath));

        product.setImage(imageName);
        product.setPrice(price);
        productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(Long productId){
        return productRepository.findById(productId).orElseThrow();
    }
}
