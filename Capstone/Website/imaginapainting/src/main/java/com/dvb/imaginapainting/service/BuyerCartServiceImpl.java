/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.data.BuyerCartRepository;
import com.dvb.imaginapainting.entities.BuyerCart;
import com.dvb.imaginapainting.entities.Product;
import com.dvb.imaginapainting.entities.Seller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class BuyerCartServiceImpl implements BuyerCartService {

    @Autowired
    BuyerCartRepository buyerCartRepository;

    @Override
    public BuyerCart findById(int buyerCartId) {
        return buyerCartRepository.findById(buyerCartId).orElse(null);
    }

    @Override
    public List<BuyerCart> findAll() {
        return buyerCartRepository.findAll();
    }

    @Override
    public BuyerCart save(BuyerCart buyerCart) {
        return buyerCartRepository.save(buyerCart);
    }

    @Override
    public void deleteById(int buyerCartId) {
        buyerCartRepository.deleteById(buyerCartId);
    }

    @Override
    public long count() {
        return buyerCartRepository.count();
    }

    @Override
    public boolean existsById(int buyerCartId) {
        return buyerCartRepository.existsById(buyerCartId);
    }

    @Override
    public void checkCartProductInStock(List<Product> buyerCartProductList) throws OutOfStockException {

        for (Product product : buyerCartProductList) {
            if (product.getQuantity() == 0) {
                throw new OutOfStockException("Item is out of stock.");
            }
        }

    }

    @Override
    public void checkProductIsActiveAndSellerIsActive(List<Product> buyerCartProductList, List<Seller> cartProductListSellers) throws BuyerCartProductNotActiveException {
        
        for (Product product : buyerCartProductList) {
            if(product.getProductStatus() != 1) {
                throw new BuyerCartProductNotActiveException("Product has been deleted.");
            }            
        }
        
        for (Seller seller : cartProductListSellers) {
            if (seller.getUser().getAcctStatus() != 1) {
                throw new BuyerCartProductNotActiveException("Seller is not active.");
            }         
        }
        
    }

    @Override
    public void checkForEmptyCart(List<Product> buyerCartProductList) throws BuyerCartEmptyException {
        
        if (buyerCartProductList.isEmpty()) {
            throw new BuyerCartEmptyException("Buyer cart is empty.");
        }
        
    }

}
