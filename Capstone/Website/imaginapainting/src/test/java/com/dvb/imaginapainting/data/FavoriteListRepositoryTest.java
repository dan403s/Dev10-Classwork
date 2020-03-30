/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.FavoriteList;
import java.util.ArrayList;
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
public class FavoriteListRepositoryTest {
    
    @Autowired
    private FavoriteListRepository favoriteListRepository;
    
    public FavoriteListRepositoryTest() {
    }
    
    @Test
    public void testFindById() {
        FavoriteList favoriteList = favoriteListRepository.findById(1)
                .orElse(null);

        assertNotNull(favoriteList);
    }

    @Test
    public void testFindAll() {
        assertTrue(favoriteListRepository.findAll().size() > 0);
    }

    @Test
    public void testSave() {
        FavoriteList favoriteList = new FavoriteList();
        favoriteList.setProducts(new ArrayList<>());

        favoriteList = favoriteListRepository.save(favoriteList);
        
        assertTrue(favoriteList.getFavoriteListId() > 0);
        
        favoriteList = favoriteListRepository.findById(favoriteList.getFavoriteListId()).orElse(null);

        assertNotNull(favoriteList);

        favoriteListRepository.deleteById(favoriteList.getFavoriteListId());
        
        favoriteList = favoriteListRepository.findById(favoriteList.getFavoriteListId()).orElse(null);
        
        assertNull(favoriteList);
    }

    @Test
    public void testDeleteById() {
        // already tested in testSave()
    }

    @Test
    public void testCount() {
        long count = favoriteListRepository.count();
        
        assertTrue(count > 0);
    }

    @Test
    public void testExistsById() {
        assertTrue(favoriteListRepository.existsById(1));
    }
    
}
