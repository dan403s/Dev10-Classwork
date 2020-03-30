/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.entities.BuyerCart;
import com.dvb.imaginapainting.entities.Product;
import com.dvb.imaginapainting.entities.Seller;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface BuyerCartService {

    // CRUD for BuyerCart objects
    /**
     * Find BuyerCart by buyerCartId
     * @param buyerCartId
     * @return 
     */
    BuyerCart findById(int buyerCartId);

    /**
     * Find all BuyerCart objects
     * @return
     */
    List<BuyerCart> findAll();

    /**
     * Save BuyerCart
     * @param buyerCart
     * @return 
     */
    BuyerCart save(BuyerCart buyerCart);

    /**
     * Delete BuyerCart by buyerCartId
     * @param buyerCartId
     */
    void deleteById(int buyerCartId);

    /**
     * Count BuyerCart objects
     * @return 
     */
    long count();

    /**
     * Check if BuyerCart exists by buyerCartId
     * @param buyerCartId
     * @return 
     */
    boolean existsById(int buyerCartId);

    /**
     * Validate that BuyerCart has products that are only in stock
     * @param buyerCartProductList
     * @throws com.dvb.imaginapainting.service.OutOfStockException
     */
    void checkCartProductInStock(List<Product> buyerCartProductList) throws OutOfStockException;

    /**
     * Validate that BuyerCart has products that are active and the seller is active too
     * @param buyerCartProductList
     * @param cartProductListSellers
     * @throws com.dvb.imaginapainting.service.BuyerCartProductNotActiveException
     */
    void checkProductIsActiveAndSellerIsActive(List<Product> buyerCartProductList, List<Seller> cartProductListSellers) throws BuyerCartProductNotActiveException;

    /**
     * Validate that BuyerCart is not empty
     * @param buyerCartProductList
     * @throws com.dvb.imaginapainting.service.BuyerCartEmptyException
     */
    void checkForEmptyCart(List<Product> buyerCartProductList) throws BuyerCartEmptyException;

}
