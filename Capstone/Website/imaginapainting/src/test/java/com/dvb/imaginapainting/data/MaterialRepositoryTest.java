/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.Material;
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
public class MaterialRepositoryTest {
    
    @Autowired
    private MaterialRepository materialRepository;
    
    public MaterialRepositoryTest() {
    }
    
    @Test
    public void testFindById() {
        Material material = materialRepository.findById(1)
                .orElse(null);

        assertNotNull(material);
    }

    @Test
    public void testFindAll() {
        assertTrue(materialRepository.findAll().size() > 0);
    }

    @Test
    public void testSave() {
        Material material = new Material();
        material.setName("TEST");

        material = materialRepository.save(material);
        
        assertTrue(material.getMaterialId()> 0);
        
        material = materialRepository.findById(material.getMaterialId()).orElse(null);

        assertNotNull(material);

        materialRepository.deleteById(material.getMaterialId());
        
        material = materialRepository.findById(material.getMaterialId()).orElse(null);
        
        assertNull(material);
    }

    @Test
    public void testDeleteById() {
        // already tested in testSave()
    }

    @Test
    public void testCount() {
        long count = materialRepository.count();
        
        assertTrue(count > 0);
    }

    @Test
    public void testExistsById() {
        assertTrue(materialRepository.existsById(1));
    }
    
}
