package com.example.demo.myfirstapi;

import com.example.demo.myfirstapi.model.Product;
import com.example.demo.myfirstapi.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    ProductRepository productRepository;
    List<Product> products;

    @BeforeEach
    void setup(){
        productRepository = new ProductRepository();
    }

    @Test
    void shouldReturnAllProducts(){
        String findAllStringOutput = productRepository.findAll().toString();
        assertTrue(findAllStringOutput.contains("Laptop"));
        assertTrue(findAllStringOutput.contains("1200"));
        assertTrue(findAllStringOutput.contains("Keyboard"));
        assertTrue(findAllStringOutput.contains("75"));
        assertTrue(findAllStringOutput.contains("Mouse"));
        assertTrue(findAllStringOutput.contains("25"));
    }

    @Test
    void shouldSaveNonExistingProducts(){
        Product product = new Product("Yogurt", 7.0);
        productRepository.save(product);
        Long productID = product.getId();
        if (productRepository.findById(productID).isPresent()){
            assertEquals(product, productRepository.findById(productID).get());
        } else {
            fail("Item is not saved.");
        }
    }

    @Test
    void shouldUpdateExistingProducts(){
        Product product = new Product("Eggs", 2.0);
        productRepository.save(product);
        Long productID = product.getId();
        Product productNew = new Product(productID,"Ramen", 3.0);
        productRepository.save(productNew);
        if (productRepository.findById(productID).isPresent()) {
            assertEquals(productNew, productRepository.findById(productID).get());
        } else {
            fail("Item is not updated.");
        }
    }

    @Test
    void shouldThrowErrorUpdatingProductThatDoesNotExit(){
        Exception exception = assertThrows(NoSuchElementException.class,
                () -> productRepository.save(new Product(-1L,"Ramen", 3.0)));
        assertTrue(exception.getMessage().toLowerCase().contains("not found for update"));
    }

    @Test
    void shouldReturnNonEmptyOptional(){
        Long existentID = 1L;
        assertTrue(productRepository.findById(existentID).isPresent());
    }

    @Test
    void shouldReturnEmptyOptional(){
        Long inexistentID = -1L;
        assertFalse(productRepository.findById(inexistentID).isPresent());
    }



}