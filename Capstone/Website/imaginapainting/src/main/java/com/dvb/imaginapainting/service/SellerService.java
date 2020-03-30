/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.entities.Seller;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface SellerService {

    // CRUD for Seller objects
    /**
     * Find Seller by sellerId
     * @param sellerId
     * @return 
     */
    Seller findById(int sellerId);

    /**
     * Find all Seller objects
     * @return 
     */
    List<Seller> findAll();

    /**
     * Save Seller
     * @param seller
     * @return 
     */
    Seller save(Seller seller);

    /**
     * Delete Seller by sellerId
     * @param sellerId
     */
    void deleteById(int sellerId);

    /**
     * Count Seller objects
     * @return 
     */
    long count();

    /**
     * Check if Seller exists by sellerId
     * @param sellerId
     * @return 
     */
    boolean existsById(int sellerId);

    /**
     * Find Seller by productId
     * @param productId
     * @return 
     */
    Seller findByProduct(int productId);

    /**
     * Load Sellers pending approval
     * @return 
     */
    List<Seller> loadPendingApprovals();

    /**
     * Approve Seller by sellerId
     * @param sellerId
     */
    void approveSellerAccount(int sellerId);

    /**
     * Reject Seller by sellerId
     * @param sellerId
     */
    void rejectSellerAccount(int sellerId);

    /**
     * Change Seller account status by sellerId
     * @param sellerId
     * @param newStatus
     */
    void changeSellerAccountStatus(int sellerId, int newStatus);

    /**
     * Find Seller by userId
     * @param userId
     * @return 
     */
    Seller findByUserId(int userId);

}
