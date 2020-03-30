/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.Category;
import com.dvb.imaginapainting.entities.Material;
import com.dvb.imaginapainting.entities.Medium;
import com.dvb.imaginapainting.entities.Product;
import com.dvb.imaginapainting.entities.Style;
import com.dvb.imaginapainting.entities.Subject;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    public ProductRepositoryTest() {
    }

    @Test
    public void testFindById() {
        Product product = productRepository.findById(1)
                .orElse(null);

        assertNotNull(product);
    }

    @Test
    public void testFindAll() {
        assertTrue(productRepository.findAll().size() > 0);
    }

    @Test
    public void testSave() {
        Category category = new Category();
        category.setCategoryId(1);
        
        Style style = new Style();
        style.setStyleId(1);
        
        Subject subject = new Subject();
        subject.setSubjectId(1);
        
        Medium medium = new Medium();
        medium.setMediumId(1);
        
        Material material = new Material();
        material.setMaterialId(1);
        
        Product product = new Product();
        product.setTitle("Test Product");
        product.setDescription("Test Description");
        product.setPrice(new BigDecimal("0"));
        product.setQuantity(0);
        product.setPhotoUrl("test");
        product.setProductStatus(1);
        product.setCategory(category);
        product.setStyle(style);
        product.setSubject(subject);
        product.setMedium(medium);
        product.setMaterial(material);        

        product = productRepository.save(product);

        assertTrue(product.getProductId() > 0);

        product = productRepository.findById(product.getProductId()).orElse(null);

        assertNotNull(product);

        productRepository.deleteById(product.getProductId());

        product = productRepository.findById(product.getProductId()).orElse(null);

        assertNull(product);
    }

    @Test
    public void testDeleteById() {
        // already tested in testSave()
    }

    @Test
    public void testCount() {
        long count = productRepository.count();

        assertTrue(count > 0);
    }

    @Test
    public void testExistsById() {
        assertTrue(productRepository.existsById(1));
    }

    @Test
    public void testFindAllWhereSellerIsActiveAndProductIsActive() {
        assertTrue(productRepository.findAllWhereSellerIsActiveAndProductIsActive().size() > 0);
    }

}
