/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.entities.Product;
import com.dvb.imaginapainting.entities.Seller;
import com.dvb.imaginapainting.entities.User;
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
public class BuyerCartServiceTest {

    @Autowired
    BuyerCartService buyerCartService;

    public BuyerCartServiceTest() {
    }

    /**
     * Test of checkCartProductInStock method, of class BuyerCartService.
     */
    @Test
    public void testCheckCartProductInStock() throws Exception {
        List<Product> buyerCartProductList = new ArrayList<>();
        Product product = new Product();
        product.setQuantity(1);

        buyerCartProductList.add(product);

        buyerCartService.checkCartProductInStock(buyerCartProductList);

    }

    /**
     * Test of checkCartProductInStock method, of class BuyerCartService.
     */
    @Test
    public void testCheckCartProductInStockThrowsException() throws Exception {

        List<Product> buyerCartProductList = new ArrayList<>();
        Product product = new Product();
        product.setQuantity(0);

        buyerCartProductList.add(product);

        try {
            buyerCartService.checkCartProductInStock(buyerCartProductList);
            fail("Supposed to throw OutOfStockException.");
        } catch (OutOfStockException e) {
            return;
        }

    }

    /**
     * Test of checkProductIsActiveAndSellerIsActive method, of class
     * BuyerCartService.
     */
    @Test
    public void testCheckProductIsActiveAndSellerIsActive() throws Exception {
        List<Product> buyerCartProductList = new ArrayList<>();
        List<Seller> cartProductListSellers = new ArrayList<>();

        Product product = new Product();
        product.setProductStatus(1);
        buyerCartProductList.add(product);

        User user = new User();
        user.setAcctStatus(1);
        Seller seller = new Seller();
        seller.setUser(user);
        cartProductListSellers.add(seller);

        buyerCartService.checkProductIsActiveAndSellerIsActive(buyerCartProductList, cartProductListSellers);

    }

    /**
     * Test of checkProductIsActiveAndSellerIsActive method, of class
     * BuyerCartService.
     */
    @Test
    public void testCheckProductIsActiveAndSellerIsActiveThrowsException1() throws Exception {
        List<Product> buyerCartProductList = new ArrayList<>();
        List<Seller> cartProductListSellers = new ArrayList<>();

        Product product = new Product();
        product.setProductStatus(2);
        buyerCartProductList.add(product);

        User user = new User();
        user.setAcctStatus(1);
        Seller seller = new Seller();
        seller.setUser(user);
        cartProductListSellers.add(seller);

        try {
            buyerCartService.checkProductIsActiveAndSellerIsActive(buyerCartProductList, cartProductListSellers);
            fail("Supposed to throw BuyerCartProductNotActiveException.");
        } catch (BuyerCartProductNotActiveException e) {
            return;
        }
    }

    /**
     * Test of checkProductIsActiveAndSellerIsActive method, of class
     * BuyerCartService.
     */
    @Test
    public void testCheckProductIsActiveAndSellerIsActiveThrowsException2() throws Exception {
        List<Product> buyerCartProductList = new ArrayList<>();
        List<Seller> cartProductListSellers = new ArrayList<>();

        Product product = new Product();
        product.setProductStatus(1);
        buyerCartProductList.add(product);

        User user = new User();
        user.setAcctStatus(2);
        Seller seller = new Seller();
        seller.setUser(user);
        cartProductListSellers.add(seller);

        try {
            buyerCartService.checkProductIsActiveAndSellerIsActive(buyerCartProductList, cartProductListSellers);
            fail("Supposed to throw BuyerCartProductNotActiveException.");
        } catch (BuyerCartProductNotActiveException e) {
            return;
        }
    }

    /**
     * Test of checkForEmptyCart method, of class BuyerCartService.
     */
    @Test
    public void testCheckForEmptyCart() throws Exception {
        List<Product> buyerCartProductList = new ArrayList<>();

        Product product = new Product();
        product.setProductStatus(1);
        buyerCartProductList.add(product);

        buyerCartService.checkForEmptyCart(buyerCartProductList);
    }

    /**
     * Test of checkForEmptyCart method, of class BuyerCartService.
     */
    @Test
    public void testCheckForEmptyCartThrowsException() throws Exception {
        List<Product> buyerCartProductList = new ArrayList<>();

        try {
            buyerCartService.checkForEmptyCart(buyerCartProductList);
            fail("Supposed to throw BuyerCartEmptyException.");
        } catch (BuyerCartEmptyException e) {
            return;
        }
    }

}
