/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.data.ProductRepository;
import com.dvb.imaginapainting.entities.Product;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Daniel Bart
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class ProductServiceTest {
    
    @Autowired
    ProductRepository productRepository;
    
    @Autowired
    ProductService productService;
    
    public ProductServiceTest() {
    }
    
    @Test
    public void testFindAllProductsFiltered() {
        List<Product> productListFiltered = productService.findAllProductsFiltered("1", "1", "1", "1", "1", "0", "2500");
        
        assertEquals(1, productListFiltered.size());
    }
    
}
