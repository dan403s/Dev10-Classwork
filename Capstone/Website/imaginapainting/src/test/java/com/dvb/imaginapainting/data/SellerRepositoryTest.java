/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.Order;
import com.dvb.imaginapainting.entities.Product;
import com.dvb.imaginapainting.entities.Seller;
import com.dvb.imaginapainting.entities.State;
import com.dvb.imaginapainting.entities.User;
import java.util.ArrayList;
import java.util.List;
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
public class SellerRepositoryTest {
    
    @Autowired
    private SellerRepository sellerRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    public SellerRepositoryTest() {
    }
    
    @Test
    public void testFindById() {
        Seller seller = sellerRepository.findById(1)
                .orElse(null);

        assertNotNull(seller);
    }

    @Test
    public void testFindAll() {
        assertTrue(sellerRepository.findAll().size() > 0);
    }

    @Test
    public void testSave() {
        Seller seller = new Seller();
        
        List<Order> orderList = new ArrayList<>();
        List<Product> productList = new ArrayList<>();
        
        State state = new State();
        state.setStateId("AK");
        
        User user = new User();
        user.setAcctStatus(1);
        user.setAptUnit("Unit 100");
        user.setCity("Charlotte");
        user.setFirstName("Test First Name");
        user.setLastName("Test Last Name");
        user.setPassword("TEST");
        user.setState(state);
        user.setStreetAddress("123 Test Lane");
        user.setUserRole("ROLE_SELLER");
        user.setUsername("Test@test.com");
        user.setZip("28255");
        
        user = userRepository.save(user);
        
        seller.setPublicName("Test Name");
        seller.setPublicCompany("Test Company");
        seller.setPublicIntroduction("HI IM TEST.");
        seller.setPhotoUrl("TEST URL");
        seller.setOrders(orderList);
        seller.setProducts(productList);
        seller.setUser(user);

        seller = sellerRepository.save(seller);
        
        assertTrue(seller.getSellerId() > 0);
        
        seller = sellerRepository.findById(seller.getSellerId()).orElse(null);

        assertNotNull(seller);

        sellerRepository.deleteById(seller.getSellerId());
        userRepository.deleteById(user.getUserId());
        
        seller = sellerRepository.findById(seller.getSellerId()).orElse(null);
        
        assertNull(seller);
    }

    @Test
    public void testDeleteById() {
        // already tested in testSave()
    }

    @Test
    public void testCount() {
        long count = sellerRepository.count();
        
        assertTrue(count > 0);
    }

    @Test
    public void testExistsById() {
        assertTrue(sellerRepository.existsById(1));
    }
    
    @Test
    public void testFindByProduct() {
        Seller seller = sellerRepository.findByProduct(1);
        
        assertNotNull(seller);
    }
    
    @Test
    public void testFindByUserId() {
        Seller seller = sellerRepository.findByUserId(3);
    }
    
}
