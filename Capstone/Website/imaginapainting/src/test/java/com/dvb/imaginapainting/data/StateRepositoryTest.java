/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.State;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class StateRepositoryTest {
    
    @Autowired
    private StateRepository stateRepository;
    
    public StateRepositoryTest() {
    }
    
    @Test
    public void testFindById() {
        State state = stateRepository.findById("AK")
                .orElse(null);

        assertNotNull(state);
        
        assertEquals("Texas", stateRepository.findById("TX").orElse(null).getName());
        assertEquals("Florida", stateRepository.findById("FL").orElse(null).getName());
        assertEquals("North Carolina", stateRepository.findById("NC").orElse(null).getName());
    }

    @Test
    public void testFindAll() {
        assertTrue(stateRepository.findAll().size() == 52);
    }

    @Test
    public void testSave() {
        State state = new State();
        state.setStateId("TE");
        state.setName("TEST");

        state = stateRepository.save(state);
        
        assertTrue(state.getStateId().equals("TE"));
        
        state = stateRepository.findById(state.getStateId()).orElse(null);

        assertNotNull(state);

        stateRepository.deleteById(state.getStateId());
        
        state = stateRepository.findById(state.getStateId()).orElse(null);
        
        assertNull(state);
    }

    @Test
    public void testDeleteById() {
        // already tested in testSave()
    }

    @Test
    public void testCount() {
        long count = stateRepository.count();
        
        assertTrue(count > 0);
    }

    @Test
    public void testExistsById() {
        assertTrue(stateRepository.existsById("AK"));
    }
    
}
