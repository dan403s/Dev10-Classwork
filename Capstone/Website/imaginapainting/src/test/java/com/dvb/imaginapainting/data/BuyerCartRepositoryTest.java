/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.BuyerCart;
import java.math.BigDecimal;
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
public class BuyerCartRepositoryTest {

    @Autowired
    private BuyerCartRepository buyerCartRepository;

    public BuyerCartRepositoryTest() {
    }

    @Test
    public void testFindById() {
        BuyerCart buyerCart = buyerCartRepository.findById(1)
                .orElse(null);

        assertNotNull(buyerCart);
    }

    @Test
    public void testFindAll() {
        assertTrue(buyerCartRepository.findAll().size() > 0);
    }

    @Test
    public void testSave() {
        BuyerCart buyerCart = new BuyerCart();
        buyerCart.setProducts(new ArrayList<>());
        buyerCart.setTotal(new BigDecimal("0"));

        buyerCart = buyerCartRepository.save(buyerCart);
        
        assertTrue(buyerCart.getBuyerCartId() > 0);
        
        buyerCart = buyerCartRepository.findById(buyerCart.getBuyerCartId()).orElse(null);

        assertNotNull(buyerCart);

        buyerCartRepository.deleteById(buyerCart.getBuyerCartId());
        
        buyerCart = buyerCartRepository.findById(buyerCart.getBuyerCartId()).orElse(null);
        
        assertNull(buyerCart);
    }

    @Test
    public void testDeleteById() {
        // already tested in testSave()
    }

    @Test
    public void testCount() {
        long count = buyerCartRepository.count();
        
        assertTrue(count > 0);
    }

    @Test
    public void testExistsById() {
        assertTrue(buyerCartRepository.existsById(1));
    }

}
