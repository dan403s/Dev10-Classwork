/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.Category;
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
public class CategoryRepositoryTest {
    
    @Autowired
    private CategoryRepository categoryRepository;
    
    public CategoryRepositoryTest() {
    }
    
    @Test
    public void testFindById() {
        Category category = categoryRepository.findById(1)
                .orElse(null);

        assertNotNull(category);
    }

    @Test
    public void testFindAll() {
        assertTrue(categoryRepository.findAll().size() > 0);
    }

    @Test
    public void testSave() {
        Category category = new Category();
        category.setName("TEST");

        category = categoryRepository.save(category);
        
        assertTrue(category.getCategoryId()> 0);
        
        category = categoryRepository.findById(category.getCategoryId()).orElse(null);

        assertNotNull(category);

        categoryRepository.deleteById(category.getCategoryId());
        
        category = categoryRepository.findById(category.getCategoryId()).orElse(null);
        
        assertNull(category);
    }

    @Test
    public void testDeleteById() {
        // already tested in testSave()
    }

    @Test
    public void testCount() {
        long count = categoryRepository.count();
        
        assertTrue(count > 0);
    }

    @Test
    public void testExistsById() {
        assertTrue(categoryRepository.existsById(1));
    }
    
}
