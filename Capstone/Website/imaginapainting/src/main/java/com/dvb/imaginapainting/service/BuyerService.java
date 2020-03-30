/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.entities.Buyer;
import com.dvb.imaginapainting.entities.Product;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface BuyerService {

    // CRUD for Buyer objects
    /**
     * Find Buyer by buyerId
     * @param buyerId
     * @return 
     */
    Buyer findById(int buyerId);

    /**
     * Find all Buyer objects
     * @return 
     */
    List<Buyer> findAll();

    /**
     * Save Buyer
     * @param buyer
     * @return 
     */
    Buyer save(Buyer buyer);

    /**
     * Delete Buyer by buyerId
     * @param buyerId
     */
    void deleteById(int buyerId);

    /**
     * Count Buyer objects
     * @return 
     */
    long count();

    /**
     * Check if Buyer exists by buyerId
     * @param buyerId
     * @return 
     */
    boolean existsById(int buyerId);

    /**
     * Change acct_status in DB for User assigned to Buyer object
     * @param buyerId
     * @param newStatus
     */
    void changeBuyerAccountStatus(int buyerId, int newStatus);

    /**
     * Find Buyer object by userId
     * @param userId
     * @return 
     */
    Buyer findByUserId(int userId);

    /**
     * Validate that FavoriteList does not have duplicates
     * @param product
     * @param buyer
     * @throws com.dvb.imaginapainting.service.AlreadyInFavoriteListException
     */
    void checkFavoriteListForDuplicate(Product product, Buyer buyer) throws AlreadyInFavoriteListException;

    /**
     * Validate that BuyerCart does not have duplicates
     * @param product
     * @param buyer
     * @throws com.dvb.imaginapainting.service.AlreadyInCartException
     */
    void checkCartForDuplicate(Product product, Buyer buyer) throws AlreadyInCartException;

    /**
     * Validate that BuyerCart product is in stock
     * @param product
     * @throws com.dvb.imaginapainting.service.OutOfStockException
     */
    void checkProductInStock(Product product) throws OutOfStockException;

}
