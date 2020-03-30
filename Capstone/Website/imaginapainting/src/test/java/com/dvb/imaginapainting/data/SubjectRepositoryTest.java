/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.Subject;
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
public class SubjectRepositoryTest {
    
    @Autowired
    private SubjectRepository subjectRepository;
    
    public SubjectRepositoryTest() {
    }
    
    @Test
    public void testFindById() {
        Subject subject = subjectRepository.findById(1)
                .orElse(null);

        assertNotNull(subject);
    }

    @Test
    public void testFindAll() {
        assertTrue(subjectRepository.findAll().size() > 0);
    }

    @Test
    public void testSave() {
        Subject subject = new Subject();
        subject.setName("TEST");

        subject = subjectRepository.save(subject);
        
        assertTrue(subject.getSubjectId()> 0);
        
        subject = subjectRepository.findById(subject.getSubjectId()).orElse(null);

        assertNotNull(subject);

        subjectRepository.deleteById(subject.getSubjectId());
        
        subject = subjectRepository.findById(subject.getSubjectId()).orElse(null);
        
        assertNull(subject);
    }

    @Test
    public void testDeleteById() {
        // already tested in testSave()
    }

    @Test
    public void testCount() {
        long count = subjectRepository.count();
        
        assertTrue(count > 0);
    }

    @Test
    public void testExistsById() {
        assertTrue(subjectRepository.existsById(1));
    }
    
}
