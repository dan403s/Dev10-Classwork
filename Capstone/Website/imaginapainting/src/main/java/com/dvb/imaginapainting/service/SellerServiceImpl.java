/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.data.SellerRepository;
import com.dvb.imaginapainting.entities.Seller;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    SellerRepository sellerRepository;

    @Override
    public Seller findById(int sellerId) {
        return sellerRepository.findById(sellerId).orElse(null);
    }

    @Override
    public List<Seller> findAll() {
        return sellerRepository.findAll();
    }

    @Override
    public Seller save(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public void deleteById(int sellerId) {
        sellerRepository.deleteById(sellerId);
    }

    @Override
    public long count() {
        return sellerRepository.count();
    }

    @Override
    public boolean existsById(int sellerId) {
        return sellerRepository.existsById(sellerId);
    }

    @Override
    public Seller findByProduct(int productId) {
        return sellerRepository.findByProduct(productId);
    }

    @Override
    public List<Seller> loadPendingApprovals() {
        List<Seller> sellerList = sellerRepository.findAll();
        List<Seller> sellerListPendingApproval = new ArrayList<>();
        
        for (Seller seller : sellerList) {
            if (seller.getUser().getAcctStatus() == 4) {
                sellerListPendingApproval.add(seller);
            }
        }        
        
        return sellerListPendingApproval;
    }
    
    @Override
    public void approveSellerAccount(int sellerId) {
        Seller seller = findById(sellerId);
        seller.getUser().setAcctStatus(1);
        
        save(seller);
    }

    @Override
    public void rejectSellerAccount(int sellerId) {
        Seller seller = findById(sellerId);
        seller.getUser().setAcctStatus(5);
        
        save(seller);
    }

    @Override
    public void changeSellerAccountStatus(int sellerId, int newStatus) {
        Seller seller = findById(sellerId);
        seller.getUser().setAcctStatus(newStatus);
        
        save(seller);
    }

    @Override
    public Seller findByUserId(int userId) {
        return sellerRepository.findByUserId(userId);
    }

}
