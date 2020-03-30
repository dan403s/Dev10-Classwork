/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.Medium;
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
public class MediumRepositoryTest {
    
    @Autowired
    private MediumRepository mediumRepository;
    
    public MediumRepositoryTest() {
    }
    
    @Test
    public void testFindById() {
        Medium medium = mediumRepository.findById(1)
                .orElse(null);

        assertNotNull(medium);
    }

    @Test
    public void testFindAll() {
        assertTrue(mediumRepository.findAll().size() > 0);
    }

    @Test
    public void testSave() {
        Medium medium = new Medium();
        medium.setName("TEST");

        medium = mediumRepository.save(medium);
        
        assertTrue(medium.getMediumId()> 0);
        
        medium = mediumRepository.findById(medium.getMediumId()).orElse(null);

        assertNotNull(medium);

        mediumRepository.deleteById(medium.getMediumId());
        
        medium = mediumRepository.findById(medium.getMediumId()).orElse(null);
        
        assertNull(medium);
    }

    @Test
    public void testDeleteById() {
        // already tested in testSave()
    }

    @Test
    public void testCount() {
        long count = mediumRepository.count();
        
        assertTrue(count > 0);
    }

    @Test
    public void testExistsById() {
        assertTrue(mediumRepository.existsById(1));
    }
    
}
