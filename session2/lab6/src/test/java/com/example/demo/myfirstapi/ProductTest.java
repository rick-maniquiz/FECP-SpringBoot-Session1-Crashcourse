package com.example.demo.myfirstapi;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    private Product product;

    @BeforeEach
    void setup(){
        product = new Product(100L, "Gold", 1000);
    }

    @Test
    void shouldCreateProductWithoutId(){
        product = new Product("Cheese", 10);
        assertEquals("Cheese",product.getName());
        assertEquals(10,product.getPrice());
    }

    @Test
    void shouldCreateProductWithId(){
        product = new Product(101L,"Cheese", 10);
        assertEquals(101L, product.getId());
        assertEquals("Cheese",product.getName());
        assertEquals(10,product.getPrice());
    }

    @Test
    void shouldBeTheSameHashCode(){
        Product product1 = new Product(100L, "Nuts", 5);
        Product product2 = new Product(100L, "Nuts", 5);
        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    void testSetID(){
        product.setId(99L);
        assertEquals(99L, product.getId());
    }

    @Test
    void testSetName(){
        product.setName("Silver");
        assertEquals("Silver", product.getName());
    }

    @Test
    void testSetPrice(){
        product.setPrice(500);
        assertEquals(500, product.getPrice());
    }

    @Test
    void testEqual(){
        Product product1 = new Product(100L, "Nuts", 5);
        Product product2 = new Product(100L, "Nuts", 5);
        assertTrue(product1.equals(product2));
    }

}