/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.controller;

import com.dvb.imaginapainting.config.SecurityUtils;
import com.dvb.imaginapainting.entities.User;
import com.dvb.imaginapainting.service.BuyerCartService;
import com.dvb.imaginapainting.service.BuyerService;
import com.dvb.imaginapainting.service.FavoriteListService;
import com.dvb.imaginapainting.service.LookupService;
import com.dvb.imaginapainting.service.OrderService;
import com.dvb.imaginapainting.service.ProductService;
import com.dvb.imaginapainting.service.SellerService;
import com.dvb.imaginapainting.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author Daniel Bart
 */
@Controller
public class LoginController {

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

    @GetMapping({"/user-login"})
    public String displayLoginPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {

            String username = SecurityUtils.getUserName();

            User userEntity = userService.findByUsername(username);

            String role = userEntity.getUserRole();

            if (role.equals("ROLE_ADMIN")) {
                return "redirect:/admin";
            } else if (role.equals("ROLE_BUYER")) {
                return "redirect:/buyer-account";
            } else if (role.equals("ROLE_SELLER")) {
                return "redirect:/seller-account";
            } else {
                return "redirect:/admin";
            }

        }

        return "user-login";
    }

}
