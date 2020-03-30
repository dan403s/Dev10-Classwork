/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.controller;

import com.dvb.imaginapainting.config.SecurityUtils;
import com.dvb.imaginapainting.entities.Buyer;
import com.dvb.imaginapainting.entities.BuyerCart;
import com.dvb.imaginapainting.entities.FavoriteList;
import com.dvb.imaginapainting.entities.Order;
import com.dvb.imaginapainting.entities.Product;
import com.dvb.imaginapainting.entities.State;
import com.dvb.imaginapainting.entities.User;
import com.dvb.imaginapainting.service.BuyerCartService;
import com.dvb.imaginapainting.service.BuyerService;
import com.dvb.imaginapainting.service.FavoriteListService;
import com.dvb.imaginapainting.service.InvalidPasswordException;
import com.dvb.imaginapainting.service.LoggedInUserDoesNotEqualUserIdFromAJAX;
import com.dvb.imaginapainting.service.LookupService;
import com.dvb.imaginapainting.service.OrderService;
import com.dvb.imaginapainting.service.ProductService;
import com.dvb.imaginapainting.service.SellerService;
import com.dvb.imaginapainting.service.UserAlreadyExistsException;
import com.dvb.imaginapainting.service.UserService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@Controller
public class BuyerController {

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

    @Autowired
    PasswordEncoder encoder;

    @GetMapping({"/buyer-registration"})
    public String displayRegistrationPage(Model model) {

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

        return "buyer-registration";
    }

    @PostMapping("/buyer-register")
    public String buyerRegistration(String buyerRegistrationFirstName, String buyerRegistrationLastName, String buyerRegistrationUsername, String buyerRegistrationPassword, Model model) {

        try {
            userService.validateUsernameForRegistration(buyerRegistrationUsername);
        } catch (UserAlreadyExistsException e) {
            return "redirect:/buyer-registration?buyer-registration-error=1";
        }

        Buyer buyer = new Buyer();
        User user = new User();
        FavoriteList favoriteList = new FavoriteList();
        BuyerCart buyerCart = new BuyerCart();
        State state = new State();
        state.setStateId("AK");

        user.setFirstName(buyerRegistrationFirstName);
        user.setLastName(buyerRegistrationLastName);
        user.setUsername(buyerRegistrationUsername);
        user.setPassword(encoder.encode(buyerRegistrationPassword));
        user.setAcctStatus(1);
        user.setUserRole("ROLE_BUYER");
        user.setState(state);

        buyerCart.setTotal(new BigDecimal("0.00"));

        user = userService.save(user);
        buyerCart = buyerCartService.save(buyerCart);
        favoriteList = favoriteListService.save(favoriteList);

        buyer.setUser(user);
        buyer.setFavoriteList(favoriteList);
        buyer.setBuyerCart(buyerCart);

        buyerService.save(buyer);

        return "redirect:/user-login";
    }

    @GetMapping({"/buyer-account"})
    public String displayAccountPage(Model model) {

        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);
        int userId = user.getUserId();
        Buyer buyer = buyerService.findByUserId(userId);

        model.addAttribute("user", user);
        model.addAttribute("states", lookupService.findAllStates());
        model.addAttribute("buyer", buyer);

        return "buyer-account";
    }

    @ResponseBody
    @GetMapping("/buyer/load-orders")
    public List<Order> loadOrders() {

        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);
        int userId = user.getUserId();

        Buyer buyer = buyerService.findByUserId(userId);
        int buyerId = buyer.getBuyerId();

        List<Order> orders = orderService.findByBuyerId(buyerId);

        return orders;

    }

    @ResponseBody
    @GetMapping("/buyer/load-favorites")
    public Buyer loadFavorites() {

        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);
        int userId = user.getUserId();

        Buyer buyer = buyerService.findByUserId(userId);

        return buyer;

    }

    @ResponseBody
    @PostMapping("/buyer/remove-from-favorites/")
    public ResponseEntity removeFromFavorites(@RequestBody Map<String, String> map) {

        String userIdString = map.get("userId");
        String buyerIdString = map.get("buyerId");
        String favoriteListIdString = map.get("favoriteListId");
        String productIdString = map.get("productId");
        int userId;
        int buyerId;
        int favoriteListId;
        int productId;

        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);
        int serverUserId = user.getUserId();
        Buyer buyer = buyerService.findByUserId(serverUserId);
        int serverBuyerId = buyer.getBuyerId();
        int serverFavoriteListId = buyer.getFavoriteList().getFavoriteListId();

        try {
            userId = Integer.parseInt(userIdString);
            buyerId = Integer.parseInt(buyerIdString);
            favoriteListId = Integer.parseInt(favoriteListIdString);
            productId = Integer.parseInt(productIdString);
        } catch (NumberFormatException e) {
            return new ResponseEntity("Something went wrong...:(", HttpStatus.FORBIDDEN);
        }

        if (serverUserId == userId && serverBuyerId == buyerId && serverFavoriteListId == favoriteListId) {
            FavoriteList favoriteList = favoriteListService.findById(favoriteListId);

            List<Product> favoriteListProductsBefore = favoriteList.getProducts();
            List<Product> favoriteListProductsAfter = new ArrayList<>();

            for (Product product : favoriteListProductsBefore) {
                if (product.getProductId() != productId) {
                    favoriteListProductsAfter.add(product);
                }
            }

            favoriteList.setProducts(favoriteListProductsAfter);

            favoriteListService.save(favoriteList);

            return new ResponseEntity("Successfully removed favorite.", HttpStatus.OK);

        } else {
            try {
                throw new LoggedInUserDoesNotEqualUserIdFromAJAX("Request data from AJAX does not match server data.");
            } catch (LoggedInUserDoesNotEqualUserIdFromAJAX e) {
                return new ResponseEntity(e, HttpStatus.FORBIDDEN);
            }
        }

    }

    @ResponseBody
    @GetMapping("/buyer/load-buyer-details/")
    public User loadBuyerDetails() {
        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);
        return user;
    }

    @ResponseBody
    @PostMapping("/buyer/change-buyer-details/")
    public ResponseEntity changeBuyerDetails(@RequestBody Map<String, String> map) {
        String userIdString = map.get("userId");
        int userId = Integer.parseInt(userIdString);

        String firstName = map.get("firstName");
        String lastName = map.get("lastName");
        String streetAddress = map.get("streetAddress");
        String aptUnit = map.get("aptUnit");
        String city = map.get("city");
        String stateIDString = map.get("state");
        State state = lookupService.findStateById(stateIDString);

        String zip = map.get("zip");
        String username = map.get("username");

        try {
            userService.validateUsername(username);
        } catch (UserAlreadyExistsException e) {
            return new ResponseEntity(e, HttpStatus.FORBIDDEN);
        }

        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);
        int buyerUserId = user.getUserId();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setStreetAddress(streetAddress);
        user.setAptUnit(aptUnit);
        user.setCity(city);
        user.setState(state);
        user.setZip(zip);
        user.setUsername(username);

        if (buyerUserId == userId) {
            userService.save(user);

            if (!username.equals(buyerUsername)) {
                SecurityContextHolder.getContext().setAuthentication(null);
            }

            return new ResponseEntity("Successfully edited user details.", HttpStatus.OK);

        } else {
            try {
                throw new LoggedInUserDoesNotEqualUserIdFromAJAX("User ID from AJAX does not match logged in user's ID.");
            } catch (LoggedInUserDoesNotEqualUserIdFromAJAX e) {
                return new ResponseEntity(e, HttpStatus.FORBIDDEN);
            }
        }

    }

    @ResponseBody
    @PostMapping("/buyer/change-buyer-password/")
    public ResponseEntity changeBuyerPassword(@RequestBody Map<String, String> map) {

        String currentPassword = map.get("currentPassword");
        String newPassword = map.get("newPassword");
        String newPassword2 = map.get("newPassword2");

        try {
            userService.validatePassword(currentPassword, newPassword, newPassword2);
        } catch (InvalidPasswordException e) {
            return new ResponseEntity(e, HttpStatus.FORBIDDEN);
        }

        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);

        user.setPassword(encoder.encode(newPassword2));

        userService.save(user);

        SecurityContextHolder.getContext().setAuthentication(null);

        return new ResponseEntity("Successfully edited user details.", HttpStatus.OK);

    }

    @ResponseBody
    @PostMapping("/buyer/delete-buyer-account/")
    public ResponseEntity deleteBuyerAccount(@RequestBody Map<String, String> map) {

        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);
        int serverUserId = user.getUserId();
        Buyer buyer = buyerService.findByUserId(serverUserId);
        int serverBuyerId = buyer.getBuyerId();

        buyerService.changeBuyerAccountStatus(serverBuyerId, 3);

        SecurityContextHolder.getContext().setAuthentication(null);

        return new ResponseEntity("Successfully deleted your account.", HttpStatus.OK);

    }

}
