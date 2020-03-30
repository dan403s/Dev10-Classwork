/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.controller;

import com.dvb.imaginapainting.service.BuyerCartService;
import com.dvb.imaginapainting.service.BuyerService;
import com.dvb.imaginapainting.service.FavoriteListService;
import com.dvb.imaginapainting.service.LookupService;
import com.dvb.imaginapainting.service.OrderService;
import com.dvb.imaginapainting.service.ProductService;
import com.dvb.imaginapainting.service.SellerService;
import com.dvb.imaginapainting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Daniel Bart
 */
//@RestController
//@RequestMapping("/") // ADD PATH!!!!!!!!!!!!!
@Controller
public class FavoriteListController {
    
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
    
}
