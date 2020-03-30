/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.controller;

import com.dvb.imaginapainting.config.SecurityUtils;
import com.dvb.imaginapainting.entities.Buyer;
import com.dvb.imaginapainting.entities.Seller;
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
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Daniel Bart
 */
@Controller
public class AdminController {

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

    @GetMapping({"/admin"})
    public String displayAdminPage(Model model) {

        String adminUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(adminUsername);

        model.addAttribute("user", user);
        model.addAttribute("states", lookupService.findAllStates());

        return "admin";
    }

    @ResponseBody
    @GetMapping("/admin/load-pending-approvals/")
    public List<Seller> loadPendingApprovals() {
        return sellerService.loadPendingApprovals();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/admin/approve-seller/")
    public void approveSellerAccount(@RequestBody Map<String, String> map) {

        String sellerIdString = map.get("sellerIdString");

        if (sellerIdString != null) {
            int sellerId = Integer.parseInt(sellerIdString);
            sellerService.approveSellerAccount(sellerId);
        }

    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/admin/reject-seller/")
    public void rejectSellerAccount(@RequestBody Map<String, String> map) {

        String sellerIdString = map.get("sellerIdString");

        if (sellerIdString != null) {
            int sellerId = Integer.parseInt(sellerIdString);
            sellerService.rejectSellerAccount(sellerId);
        }

    }

    @ResponseBody
    @GetMapping("/admin/load-manage-sellers/")
    public List<Seller> loadManageUsers() {
        return sellerService.findAll();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/admin/suspend-seller/")
    public void suspendSellerAccount(@RequestBody Map<String, String> map) {

        String sellerIdString = map.get("sellerIdString");
        String suspendedOrUnsuspendedString = map.get("suspendedOrUnsuspended");

        if (sellerIdString != null && suspendedOrUnsuspendedString != null) {
            int sellerId = Integer.parseInt(sellerIdString);
            int suspendedOrUnsuspend = Integer.parseInt(suspendedOrUnsuspendedString);
            sellerService.changeSellerAccountStatus(sellerId, suspendedOrUnsuspend);

        }

    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/admin/delete-seller/")
    public void deleteSellerAccount(@RequestBody Map<String, String> map) {

        String sellerIdString = map.get("sellerIdString");
        String deletedOrUndeletedString = map.get("deletedOrUndeleted");

        if (sellerIdString != null && deletedOrUndeletedString != null) {
            int sellerId = Integer.parseInt(sellerIdString);
            int deletedOrUndeleted = Integer.parseInt(deletedOrUndeletedString);
            sellerService.changeSellerAccountStatus(sellerId, deletedOrUndeleted);

        }

    }

    @ResponseBody
    @GetMapping("/admin/load-manage-buyers/")
    public List<Buyer> loadManageBuyers() {
        return buyerService.findAll();
    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/admin/suspend-buyer/")
    public void suspendBuyerAccount(@RequestBody Map<String, String> map) {

        String buyerIdString = map.get("buyerIdString");
        String suspendedOrUnsuspendedString = map.get("suspendedOrUnsuspended");

        if (buyerIdString != null && suspendedOrUnsuspendedString != null) {
            int buyerId = Integer.parseInt(buyerIdString);
            int suspendedOrUnsuspend = Integer.parseInt(suspendedOrUnsuspendedString);
            buyerService.changeBuyerAccountStatus(buyerId, suspendedOrUnsuspend);

        }

    }

    @ResponseStatus(value = HttpStatus.OK)
    @PostMapping("/admin/delete-buyer/")
    public void deleteBuyerAccount(@RequestBody Map<String, String> map) {

        String buyerIdString = map.get("buyerIdString");
        String deletedOrUndeletedString = map.get("deletedOrUndeleted");

        if (buyerIdString != null && deletedOrUndeletedString != null) {
            int buyerId = Integer.parseInt(buyerIdString);
            int deletedOrUndeleted = Integer.parseInt(deletedOrUndeletedString);
            buyerService.changeBuyerAccountStatus(buyerId, deletedOrUndeleted);

        }

    }

    @ResponseBody
    @GetMapping("/admin/load-admin-details/")
    public User loadAdminDetails() {
        String adminUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(adminUsername);
        return user;
    }

    @ResponseBody
    @PostMapping("/admin/change-admin-details/")
    public ResponseEntity changeAdminDetails(@RequestBody Map<String, String> map) {
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

        String adminUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(adminUsername);
        int adminUserId = user.getUserId();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setStreetAddress(streetAddress);
        user.setAptUnit(aptUnit);
        user.setCity(city);
        user.setState(state);
        user.setZip(zip);
        user.setUsername(username);

        if (adminUserId == userId) {
            userService.save(user);

            if (!username.equals(adminUsername)) {
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
    @PostMapping("/admin/change-admin-password/")
    public ResponseEntity changeAdminPassword(@RequestBody Map<String, String> map) {

        String currentPassword = map.get("currentPassword");
        String newPassword = map.get("newPassword");
        String newPassword2 = map.get("newPassword2");

        try {
            userService.validatePassword(currentPassword, newPassword, newPassword2);
        } catch (InvalidPasswordException e) {
            return new ResponseEntity(e, HttpStatus.FORBIDDEN);
        }

        String adminUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(adminUsername);

        user.setPassword(encoder.encode(newPassword2));

        userService.save(user);

        SecurityContextHolder.getContext().setAuthentication(null);

        return new ResponseEntity("Successfully edited user details.", HttpStatus.OK);

    }

}
