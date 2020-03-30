/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.Buyer;
import com.dvb.imaginapainting.entities.BuyerCart;
import com.dvb.imaginapainting.entities.FavoriteList;
import com.dvb.imaginapainting.entities.Order;
import com.dvb.imaginapainting.entities.State;
import com.dvb.imaginapainting.entities.User;
import java.math.BigDecimal;
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
public class BuyerRepositoryTest {
    
    @Autowired
    private BuyerRepository buyerRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private BuyerCartRepository buyerCartRepository;
    
    @Autowired
    private FavoriteListRepository favoriteListRepository;
    
    public BuyerRepositoryTest() {
    }
    
    @Test
    public void testFindById() {
        Buyer buyer = buyerRepository.findById(1)
                .orElse(null);

        assertNotNull(buyer);
    }

    @Test
    public void testFindAll() {
        assertTrue(buyerRepository.findAll().size() > 0);
    }

    @Test
    public void testSave() {
        Buyer buyer = new Buyer();
        BuyerCart buyerCart = new BuyerCart();
        buyerCart.setTotal(new BigDecimal("0"));
        buyerCart.setProducts(new ArrayList<>());
        
        buyerCart = buyerCartRepository.save(buyerCart);
        
        FavoriteList favoriteList = new FavoriteList();
        favoriteList.setProducts(new ArrayList<>());
        
        favoriteList = favoriteListRepository.save(favoriteList);
        
        List<Order> orderList = new ArrayList<>();
        
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
        user.setUserRole("ROLE_BUYER");
        user.setUsername("Test@test.com");
        user.setZip("28255");
        
        user = userRepository.save(user);
        
        buyer.setBuyerCart(buyerCart);
        buyer.setFavoriteList(favoriteList);
        buyer.setOrders(orderList);
        buyer.setUser(user);

        buyer = buyerRepository.save(buyer);
        
        assertTrue(buyer.getBuyerId() > 0);
        
        buyer = buyerRepository.findById(buyer.getBuyerId()).orElse(null);

        assertNotNull(buyer);

        buyerRepository.deleteById(buyer.getBuyerId());
        userRepository.deleteById(user.getUserId());
        buyerCartRepository.deleteById(buyerCart.getBuyerCartId());
        favoriteListRepository.deleteById(favoriteList.getFavoriteListId());
        
        buyer = buyerRepository.findById(buyer.getBuyerId()).orElse(null);
        
        assertNull(buyer);
    }

    @Test
    public void testDeleteById() {
        // already tested in testSave()
    }

    @Test
    public void testCount() {
        long count = buyerRepository.count();
        
        assertTrue(count > 0);
    }

    @Test
    public void testExistsById() {
        assertTrue(buyerRepository.existsById(1));
    }
    
    @Test
    public void testFindByUserId() {
        Buyer buyer = buyerRepository.findByUserId(2);
    }
    
}
