/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.data.BuyerRepository;
import com.dvb.imaginapainting.entities.Buyer;
import com.dvb.imaginapainting.entities.Product;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    BuyerRepository buyerRepository;

    @Override
    public Buyer findById(int buyerId) {
        return buyerRepository.findById(buyerId).orElse(null);
    }

    @Override
    public List<Buyer> findAll() {
        return buyerRepository.findAll();
    }

    @Override
    public Buyer save(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    @Override
    public void deleteById(int buyerId) {
        buyerRepository.deleteById(buyerId);
    }

    @Override
    public long count() {
        return buyerRepository.count();
    }

    @Override
    public boolean existsById(int buyerId) {
        return buyerRepository.existsById(buyerId);
    }

    @Override
    public void changeBuyerAccountStatus(int buyerId, int newStatus) {
        Buyer buyer = findById(buyerId);
        buyer.getUser().setAcctStatus(newStatus);

        save(buyer);
    }

    @Override
    public Buyer findByUserId(int userId) {
        return buyerRepository.findByUserId(userId);
    }

    @Override
    public void checkFavoriteListForDuplicate(Product product, Buyer buyer) throws AlreadyInFavoriteListException {

        List<Product> productsInFavoriteList = buyer.getFavoriteList().getProducts();
        int productIdToBeAdded = product.getProductId();

        for (Product existingFavoriteProduct : productsInFavoriteList) {
            
            if (existingFavoriteProduct.getProductId() == productIdToBeAdded) {
                throw new AlreadyInFavoriteListException("This item is already in your favorite list.");
            }

        }

    }

    @Override
    public void checkCartForDuplicate(Product product, Buyer buyer) throws AlreadyInCartException {
        
        List<Product> productsInCart = buyer.getBuyerCart().getProducts();
        int productIdToBeAdded = product.getProductId();

        for (Product existingCartProduct : productsInCart) {
            
            if (existingCartProduct.getProductId() == productIdToBeAdded) {
                throw new AlreadyInCartException("This item is already in your cart.");
            }

        }
        
    }

    @Override
    public void checkProductInStock(Product product) throws OutOfStockException {
        
        if (product.getQuantity() == 0) {
            throw new OutOfStockException("Item is out of stock.");
        }
        
    }

}
