/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.entities.Product;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface ProductService {

    // CRUD for Product objects
    /**
     * Find Product with productId
     * @param productId
     * @return 
     */
    Product findById(int productId);

    /**
     * Find all Product objects
     * @return 
     */
    List<Product> findAll();

    /**
     * Save Product
     * @param product
     * @return 
     */
    Product save(Product product);

    /**
     * Delete Product by productId
     * @param productId
     */
    void deleteById(int productId);

    /**
     * Count Product objects
     * @return 
     */
    long count();

    /**
     * Check if Product exists by productId
     * @param productId
     * @return 
     */
    boolean existsById(int productId);

    /**
     * List all Product objects where seller is active and product is active
     * @return 
     */
    List<Product> findAllWhereSellerIsActiveAndProductIsActive();

    /**
     * Find all Product objects filtered by parameters
     * @param category
     * @param style
     * @param subject
     * @param medium
     * @param material
     * @param selectPriceMin
     * @param selectPriceMax
     * @return 
     */
    List<Product> findAllProductsFiltered(String category, String style, String subject, String medium, String material, String selectPriceMin, String selectPriceMax);

}
