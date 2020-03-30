/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.controller;

import com.dvb.imaginapainting.config.SecurityUtils;
import com.dvb.imaginapainting.entities.Buyer;
import com.dvb.imaginapainting.entities.Category;
import com.dvb.imaginapainting.entities.Material;
import com.dvb.imaginapainting.entities.Medium;
import com.dvb.imaginapainting.entities.Product;
import com.dvb.imaginapainting.entities.Seller;
import com.dvb.imaginapainting.entities.Style;
import com.dvb.imaginapainting.entities.Subject;
import com.dvb.imaginapainting.entities.User;
import com.dvb.imaginapainting.service.AlreadyInCartException;
import com.dvb.imaginapainting.service.AlreadyInFavoriteListException;
import com.dvb.imaginapainting.service.BuyerCartService;
import com.dvb.imaginapainting.service.BuyerService;
import com.dvb.imaginapainting.service.FavoriteListService;
import com.dvb.imaginapainting.service.LookupService;
import com.dvb.imaginapainting.service.OrderService;
import com.dvb.imaginapainting.service.OutOfStockException;
import com.dvb.imaginapainting.service.ProductService;
import com.dvb.imaginapainting.service.SellerService;
import com.dvb.imaginapainting.service.UserService;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Daniel Bart
 */
//@RestController
//@RequestMapping("/") // ADD PATH!!!!!!!!!!!!!
@CrossOrigin
@Controller
public class ProductController {

    @Autowired
    LookupService lookupService;

    @Autowired
    UserService userService;

    @Autowired
    BuyerService buyerService;

    @Autowired
    OrderService orderService;

    @Autowired
    BuyerCartService buyerCartService;

    @Autowired
    FavoriteListService favoriteListService;

    @Autowired
    ProductService productService;

    @Autowired
    SellerService sellerService;

    @GetMapping({"/products"})
    public String displayProductsPage(Model model) {
        List<Category> categoryList = lookupService.findAllCategories();
        List<Style> styleList = lookupService.findAllStyles();
        List<Subject> subjectList = lookupService.findAllSubjects();
        List<Medium> mediumList = lookupService.findAllMediums();
        List<Material> materialList = lookupService.findAllMaterials();

        model.addAttribute("categories", categoryList);
        model.addAttribute("styles", styleList);
        model.addAttribute("subjects", subjectList);
        model.addAttribute("mediums", mediumList);
        model.addAttribute("materials", materialList);

        return "products";
    }

    @ResponseBody
    @GetMapping("/products/load-products")
    public List<Product> getAllProducts() {
        return productService.findAllWhereSellerIsActiveAndProductIsActive();
    }

    @ResponseBody
    @PostMapping("/products/filter-products")
//    @RequestMapping(value = "/products/filter-products", method = RequestMethod.POST)
    public List<Product> getProductsFiltered(@RequestBody Map<String, String> map) {

        String category = map.get("category");
        String style = map.get("style");
        String subject = map.get("subject");
        String medium = map.get("medium");
        String material = map.get("material");
        String selectPriceMin = map.get("selectPriceMin");
        String selectPriceMax = map.get("selectPriceMax");

        List<Product> productList = productService.findAllProductsFiltered(category, style, subject, medium, material, selectPriceMin, selectPriceMax);

        return productList;
    }

    @GetMapping({"/individual-product"})
    public String displayIndividualProductPage(String productIdString, Model model) {

        if (productIdString != null) {
            int productId = Integer.parseInt(productIdString);
            Product product = productService.findById(productId);
            model.addAttribute("product", product);

            Seller seller = sellerService.findByProduct(productId);
            model.addAttribute("seller", seller);

            try {
                String buyerUsername = SecurityUtils.getUserName();
                User user = userService.findByUsername(buyerUsername);
                int userId = user.getUserId();
                Buyer buyer = buyerService.findByUserId(userId);
                model.addAttribute("buyer", buyer);
            } catch (ClassCastException e) {

            }

        } else {
            return "error";
        }

        return "individual-product";
    }

    @GetMapping({"/individual-product-admin-view"})
    public String displayIndividualProductPageAdminView(String productIdString, Model model) {

        if (productIdString != null) {
            int productId = Integer.parseInt(productIdString);
            Product product = productService.findById(productId);
            model.addAttribute("product", product);

            Seller seller = sellerService.findByProduct(productId);
            model.addAttribute("seller", seller);
        } else {
            return "error";
        }

        return "individual-product-admin-view";
    }

    @PostMapping("/add-to-favorite-list")
    public String addToFavoriteList(String favoriteProductIdString, Model model) {

        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);
        int userId = user.getUserId();
        Buyer buyer = buyerService.findByUserId(userId);
        
        int favoriteProductId = Integer.parseInt(favoriteProductIdString);
        Product product = productService.findById(favoriteProductId);
        
        List<Product> favoriteProductsList = buyer.getFavoriteList().getProducts();
        
        try {
            buyerService.checkFavoriteListForDuplicate(product, buyer);
        } catch (AlreadyInFavoriteListException e) {
            return "redirect:/individual-product?productIdString=" + favoriteProductIdString + "&already-in-favorites-error=1";
        }
        
        favoriteProductsList.add(product);
        
        buyer.getFavoriteList().setProducts(favoriteProductsList);
        
        buyerService.save(buyer);

        return "redirect:/individual-product?productIdString=" + favoriteProductIdString;
    }
    
    @PostMapping("/add-to-cart")
    public String addToCart(String cartProductIdString, Model model) {

        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);
        int userId = user.getUserId();
        Buyer buyer = buyerService.findByUserId(userId);
        
        int cartProductId = Integer.parseInt(cartProductIdString);
        Product product = productService.findById(cartProductId);
        
        List<Product> cartProductsList = buyer.getBuyerCart().getProducts();
        
        try {
            buyerService.checkCartForDuplicate(product, buyer);
        } catch (AlreadyInCartException e) {
            return "redirect:/individual-product?productIdString=" + cartProductIdString + "&already-in-cart-error=1";
        }
        
        try {
            buyerService.checkProductInStock(product);
        } catch (OutOfStockException e) {
            return "redirect:/individual-product?productIdString=" + cartProductIdString + "&product-out-of-stock-error=1";
        }
        
        cartProductsList.add(product);
        
        buyer.getBuyerCart().setProducts(cartProductsList);
        
        BigDecimal currentCartTotal = buyer.getBuyerCart().getTotal();
        BigDecimal newCartTotal = currentCartTotal.add(product.getPrice());
        
        buyer.getBuyerCart().setTotal(newCartTotal);
        
        buyerService.save(buyer);

        return "redirect:/individual-product?productIdString=" + cartProductIdString;
    }

}
