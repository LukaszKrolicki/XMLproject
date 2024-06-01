package com.example.xmlproject.controller;

import com.example.xmlproject.exceptions.FileNotFoundException;
import com.example.xmlproject.exceptions.FileParsingException;
import com.example.xmlproject.exceptions.ProductNotFoundException;
import com.example.xmlproject.model.Product;
import com.example.xmlproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


import com.example.xmlproject.model.Product;
import com.example.xmlproject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.JAXBException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Value("${file.path}")
    private String filePathRes;

    @GetMapping("/load")
    public ResponseEntity<String> loadProducts() {
        try {
            productService.loadProductsFromFile(filePathRes);
            return ResponseEntity.ok("Products loaded successfully");
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (FileParsingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred: " + e.getMessage());
        }
    }

    @GetMapping("/count")
    public ResponseEntity<Integer> getProductCount() {
        return ResponseEntity.ok(productService.getNumberOfProducts());
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{name}")
    public ResponseEntity<Product> getProductByName(@PathVariable String name) {
        Product product = productService.getProductByName(name);
        if (product == null) {
            throw new ProductNotFoundException("Not found: " + name);
        }
        return ResponseEntity.ok(product);
    }
}