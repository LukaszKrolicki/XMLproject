package com.example.xmlproject.controller;

import com.example.xmlproject.exceptions.FileNotFoundException;
import com.example.xmlproject.exceptions.FileParsingException;
import com.example.xmlproject.exceptions.GlobalExceptionHandler;
import com.example.xmlproject.exceptions.ProductNotFoundException;
import com.example.xmlproject.model.Product;
import com.example.xmlproject.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class XmLprojectApplicationTests {

    @MockBean
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
        mockMvc = MockMvcBuilders.standaloneSetup(productController)
                .setControllerAdvice(globalExceptionHandler)
                .build();
    }

    @Test
    public void testLoadProducts() throws Exception {
        mockMvc.perform(get("/products/load"))
                .andExpect(status().isOk())
                .andExpect(content().string("Products loaded successfully"));
    }

    @Test
    public void testGetProductCount() throws Exception {
        when(productService.getNumberOfProducts()).thenReturn(3);

        mockMvc.perform(get("/products/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("3"));
    }

    @Test
    public void testGetAllProducts() throws Exception {
        when(productService.getProducts()).thenReturn(Arrays.asList(
                new Product(1, "apple", "fruit", "2303-E1A-G-M-W209B-VM", "FruitsAll", true),
                new Product(2, "orange", "fruit", "5603-J1A-G-M-W982F-PO", "FruitsAll", false),
                new Product(3, "glass", "dish", "9999-E7R-Q-M-K287B-YH", "HomeHome", true)
        ));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].name").value("apple"))
                .andExpect(jsonPath("$[1].name").value("orange"))
                .andExpect(jsonPath("$[2].name").value("glass"));
    }

    @Test
    public void testGetProductByName() throws Exception {
        when(productService.getProductByName("apple")).thenReturn(
                new Product(1, "apple", "fruit", "2303-E1A-G-M-W209B-VM", "FruitsAll", true)
        );

        mockMvc.perform(get("/products/apple"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("apple"));
    }

    @Test
    public void testGetProductByName_NotFound() throws Exception {
        when(productService.getProductByName("apple2")).thenThrow(new ProductNotFoundException("Not found: apple2"));

        mockMvc.perform(get("/products/apple2"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Not found: apple2"));
    }

    @Test
    public void testGetProductCountWhenEmpty() throws Exception {
        when(productService.getNumberOfProducts()).thenReturn(0);

        mockMvc.perform(get("/products/count"))
                .andExpect(status().isOk())
                .andExpect(content().string("0"));
    }



}