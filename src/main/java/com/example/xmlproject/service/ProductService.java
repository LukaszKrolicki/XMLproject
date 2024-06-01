package com.example.xmlproject.service;



import com.example.xmlproject.exceptions.FileNotFoundException;
import com.example.xmlproject.exceptions.FileParsingException;
import com.example.xmlproject.exceptions.ProductNotFoundException;
import com.example.xmlproject.model.Product;
import com.example.xmlproject.model.Products;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private List<Product> products;

    @Value("${file.path}")
    private String filePath;

    @PostConstruct
    public void init() {
        try {
            loadProductsFromFile(filePath);
        } catch (JAXBException | IOException e) {
            throw new IllegalStateException("Failed to load initial products.", e);
        }
    }

    public void loadProductsFromFile(String filePath) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Products.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();

        File file = new File(filePath);
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        try (FileInputStream inputStream = new FileInputStream(file)) {
            Products productsWrapper = (Products) unmarshaller.unmarshal(inputStream);
            this.products = productsWrapper.getProducts();
            logger.info("Loaded {} products from file {}", products.size(), filePath);
        } catch (JAXBException e) {
            throw new FileParsingException("Error parsing XML file");
        }
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProductByName(String name) {
        if (products == null) {
            throw new ProductNotFoundException("Product list is not initialized.");
        }
        return products.stream().filter(p -> p.getName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new ProductNotFoundException("Not found: " + name));
    }

    public int getNumberOfProducts() {
        if (products == null) {
            return 0;
        }
        return products.size();
    }
}