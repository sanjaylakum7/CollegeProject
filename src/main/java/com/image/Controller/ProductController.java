package com.image.Controller;

import com.image.Entities.Product;
import com.image.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${project.image}")
    private String path;

    @PostMapping("/product")
    public ResponseEntity<String> addProductWithImages(@RequestParam("name") String name,
                                                       @RequestParam("price") String price,
                                                       @RequestParam("image")MultipartFile imageFile) throws IOException {
        productService.addProductWithImages(name, price, imageFile, path);
        return new ResponseEntity<>("Product added successfully !!", HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }
}
