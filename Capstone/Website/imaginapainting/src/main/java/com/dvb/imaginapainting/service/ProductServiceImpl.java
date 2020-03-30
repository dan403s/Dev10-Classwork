/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.data.ProductRepository;
import com.dvb.imaginapainting.entities.Category;
import com.dvb.imaginapainting.entities.Material;
import com.dvb.imaginapainting.entities.Medium;
import com.dvb.imaginapainting.entities.Product;
import com.dvb.imaginapainting.entities.Style;
import com.dvb.imaginapainting.entities.Subject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product findById(int productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteById(int productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public long count() {
        return productRepository.count();
    }

    @Override
    public boolean existsById(int productId) {
        return productRepository.existsById(productId);
    }
    
    @Override
    public List<Product> findAllWhereSellerIsActiveAndProductIsActive() {
        return productRepository.findAllWhereSellerIsActiveAndProductIsActive();
    }

    @Override
    public List<Product> findAllProductsFiltered(String category, String style, String subject, String medium, String material, String selectPriceMin, String selectPriceMax) {
        List<Product> productListUnfiltered = productRepository.findAllWhereSellerIsActiveAndProductIsActive();
        List<Product> productListFiltered = new ArrayList<>();
        int categoryInt;
        int styleInt;
        int subjectInt;
        int mediumInt;
        int materialInt;

        try {
            categoryInt = Integer.parseInt(category);
        } catch(NumberFormatException e) {
            categoryInt = 0;
        }
        
        try {
            styleInt = Integer.parseInt(style);
        } catch(NumberFormatException e) {
            styleInt = 0;
        }
        
        try {
            subjectInt = Integer.parseInt(subject);
        } catch(NumberFormatException e) {
            subjectInt = 0;
        }
        
        try {
            mediumInt = Integer.parseInt(medium);
        } catch(NumberFormatException e) {
            mediumInt = 0;
        }
        
        try {
            materialInt = Integer.parseInt(material);
        } catch(NumberFormatException e) {
            materialInt = 0;
        }
        
        BigDecimal minPrice = new BigDecimal(selectPriceMin);
        BigDecimal maxPrice = new BigDecimal(selectPriceMax);

        for (Product product : productListUnfiltered) {
            
            if (product.getCategory() == null) {
                Category emptyCategory = new Category();
                emptyCategory.setCategoryId(0);
                product.setCategory(emptyCategory);
            }
            
            if (product.getStyle() == null) {
                Style emptyStyle = new Style();
                emptyStyle.setStyleId(0);
                product.setStyle(emptyStyle);
            }
            
            if (product.getSubject() == null) {
                Subject emptySubject = new Subject();
                emptySubject.setSubjectId(0);
                product.setSubject(emptySubject);
            }
            
            if (product.getMedium() == null) {
                Medium emptyMedium = new Medium();
                emptyMedium.setMediumId(0);
                product.setMedium(emptyMedium);
            }
            
            if (product.getMaterial() == null) {
                Material emptyMaterial = new Material();
                emptyMaterial.setMaterialId(0);
                product.setMaterial(emptyMaterial);
            }

            if ((product.getCategory().getCategoryId() == categoryInt || categoryInt == 0) &&
                    (product.getStyle().getStyleId() == styleInt || styleInt == 0) &&
                    (product.getSubject().getSubjectId() == subjectInt || subjectInt == 0) &&
                    (product.getMedium().getMediumId() == mediumInt || mediumInt == 0) &&
                    (product.getMaterial().getMaterialId() == materialInt || materialInt == 0) &&
                    product.getPrice().compareTo(minPrice) >= 0 &&
                    product.getPrice().compareTo(maxPrice) <= 0) {
                productListFiltered.add(product);
            }

        }

        return productListFiltered;
    }

}
