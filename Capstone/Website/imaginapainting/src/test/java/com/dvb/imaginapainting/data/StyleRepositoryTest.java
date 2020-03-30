/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.Style;
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
public class StyleRepositoryTest {
    
    @Autowired
    private StyleRepository styleRepository;
    
    public StyleRepositoryTest() {
    }
    
    @Test
    public void testFindById() {
        Style style = styleRepository.findById(1)
                .orElse(null);

        assertNotNull(style);
    }

    @Test
    public void testFindAll() {
        assertTrue(styleRepository.findAll().size() > 0);
    }

    @Test
    public void testSave() {
        Style style = new Style();
        style.setName("TEST");

        style = styleRepository.save(style);
        
        assertTrue(style.getStyleId()> 0);
        
        style = styleRepository.findById(style.getStyleId()).orElse(null);

        assertNotNull(style);

        styleRepository.deleteById(style.getStyleId());
        
        style = styleRepository.findById(style.getStyleId()).orElse(null);
        
        assertNull(style);
    }

    @Test
    public void testDeleteById() {
        // already tested in testSave()
    }

    @Test
    public void testCount() {
        long count = styleRepository.count();
        
        assertTrue(count > 0);
    }

    @Test
    public void testExistsById() {
        assertTrue(styleRepository.existsById(1));
    }
    
}
