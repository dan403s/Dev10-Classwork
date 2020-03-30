/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.data.SellerRepository;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
public class SellerServiceTest {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    SellerService sellerService;

    public SellerServiceTest() {
    }

    /**
     * Test of loadPendingApprovals method, of class SellerService.
     */
    @Test
    public void testLoadPendingApprovals() {
        assertEquals(1, sellerService.loadPendingApprovals().size());
    }
}
