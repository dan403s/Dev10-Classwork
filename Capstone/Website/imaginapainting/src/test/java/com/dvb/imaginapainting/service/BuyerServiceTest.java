/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.entities.Buyer;
import com.dvb.imaginapainting.entities.BuyerCart;
import com.dvb.imaginapainting.entities.FavoriteList;
import com.dvb.imaginapainting.entities.Product;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.fail;
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
public class BuyerServiceTest {

    @Autowired
    BuyerService buyerService;

    public BuyerServiceTest() {
    }

    /**
     * Test of checkFavoriteListForDuplicate method, of class BuyerService.
     */
    @Test
    public void testCheckFavoriteListForDuplicate() throws Exception {
        Product product1 = new Product();
        product1.setProductId(100);

        Product product2 = new Product();
        product2.setProductId(101);

        List<Product> favoriteListProducts = new ArrayList<>();
        favoriteListProducts.add(product2);

        FavoriteList favoriteList = new FavoriteList();
        favoriteList.setProducts(favoriteListProducts);

        Buyer buyer = new Buyer();
        buyer.setFavoriteList(favoriteList);

        buyerService.checkFavoriteListForDuplicate(product1, buyer);
    }

    /**
     * Test of checkFavoriteListForDuplicate method, of class BuyerService.
     */
    @Test
    public void testCheckFavoriteListForDuplicateThrowsException() throws Exception {
        Product product1 = new Product();
        product1.setProductId(100);

        Product product2 = new Product();
        product2.setProductId(101);

        List<Product> favoriteListProducts = new ArrayList<>();
        favoriteListProducts.add(product2);
        favoriteListProducts.add(product1);

        FavoriteList favoriteList = new FavoriteList();
        favoriteList.setProducts(favoriteListProducts);

        Buyer buyer = new Buyer();
        buyer.setFavoriteList(favoriteList);

        try {
            buyerService.checkFavoriteListForDuplicate(product1, buyer);
            fail("Supposed to throw AlreadyInFavoriteListException.");
        } catch (AlreadyInFavoriteListException e) {
            return;
        }
    }

    /**
     * Test of checkCartForDuplicate method, of class BuyerService.
     */
    @Test
    public void testCheckCartForDuplicate() throws Exception {
        Product product1 = new Product();
        product1.setProductId(100);

        Product product2 = new Product();
        product2.setProductId(101);

        List<Product> cartProducts = new ArrayList<>();
        cartProducts.add(product2);

        BuyerCart buyerCart = new BuyerCart();
        buyerCart.setProducts(cartProducts);

        Buyer buyer = new Buyer();
        buyer.setBuyerCart(buyerCart);

        buyerService.checkCartForDuplicate(product1, buyer);
    }

    /**
     * Test of checkCartForDuplicate method, of class BuyerService.
     */
    @Test
    public void testCheckCartForDuplicateThrowsException() throws Exception {
        Product product1 = new Product();
        product1.setProductId(100);

        Product product2 = new Product();
        product2.setProductId(101);

        List<Product> cartProducts = new ArrayList<>();
        cartProducts.add(product2);
        cartProducts.add(product1);

        BuyerCart buyerCart = new BuyerCart();
        buyerCart.setProducts(cartProducts);

        Buyer buyer = new Buyer();
        buyer.setBuyerCart(buyerCart);

        try {
            buyerService.checkCartForDuplicate(product1, buyer);
            fail("Supposed to throw AlreadyInCartException.");
        } catch (AlreadyInCartException e) {
            return;
        }
    }

    /**
     * Test of checkProductInStock method, of class BuyerService.
     */
    @Test
    public void testCheckProductInStock() throws Exception {
        Product product = new Product();
        product.setQuantity(1);

        buyerService.checkProductInStock(product);
    }

    /**
     * Test of checkProductInStock method, of class BuyerService.
     */
    @Test
    public void testCheckProductInStockThrowsException() throws Exception {
        Product product = new Product();
        product.setQuantity(0);

        try {
            buyerService.checkProductInStock(product);
            fail("Supposed to throw OutOfStockException.");
        } catch (OutOfStockException e) {
            return;
        }
    }

}
