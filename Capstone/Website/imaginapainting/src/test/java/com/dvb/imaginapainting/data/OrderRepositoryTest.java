/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import java.math.BigDecimal;
import java.time.LocalDate;
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
public class OrderRepositoryTest {
    
    @Autowired
    private OrderRepository orderRepository;
    
    public OrderRepositoryTest() {
    }

    @Test
    public void testFindByBuyerId() {
        assertTrue(orderRepository.findByBuyerId(1).size() > 0);
    }
    
    @Test
    public void testFindBySellerId() {
        assertTrue(orderRepository.findBySellerId(1).size() > 0);
    }
    
    @Test
    public void testAddOrder() {
        LocalDate ld = LocalDate.now();
        orderRepository.addOrder(ld, new BigDecimal("0"), "Test First Name", "Test Last Name", "123 Fake Lane", "Unit 100", "Arkansas City", "99999", "AK", 1);
    
        int id = orderRepository.getLastInsertId();
        assertTrue(id > 0);
                
    }
    
}
