/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.controller;

import com.dvb.imaginapainting.config.SecurityUtils;
import com.dvb.imaginapainting.entities.Category;
import com.dvb.imaginapainting.entities.Material;
import com.dvb.imaginapainting.entities.Medium;
import com.dvb.imaginapainting.entities.Order;
import com.dvb.imaginapainting.entities.Product;
import com.dvb.imaginapainting.entities.Seller;
import com.dvb.imaginapainting.entities.State;
import com.dvb.imaginapainting.entities.Style;
import com.dvb.imaginapainting.entities.Subject;
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
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Daniel Bart
 */
//@RestController
//@RequestMapping("/") // ADD PATH!!!!!!!!!!!!!
@Controller
public class SellerController {

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

    @GetMapping({"/seller-registration"})
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

        return "seller-registration";
    }

    @PostMapping("/seller-register")
    public String sellerRegistration(String sellerRegistrationFirstName, String sellerRegistrationLastName, String sellerRegistrationUsername, String sellerRegistrationPassword, Model model) {

        try {
            userService.validateUsernameForRegistration(sellerRegistrationUsername);
        } catch (UserAlreadyExistsException e) {
            return "redirect:/seller-registration?seller-registration-error=1";
        }

        Seller seller = new Seller();
        User user = new User();
        State state = new State();
        state.setStateId("AK");

        user.setFirstName(sellerRegistrationFirstName);
        user.setLastName(sellerRegistrationLastName);
        user.setUsername(sellerRegistrationUsername);
        user.setPassword(encoder.encode(sellerRegistrationPassword));
        user.setAcctStatus(4);
        user.setUserRole("ROLE_SELLER");
        user.setState(state);


        user = userService.save(user);

        seller.setUser(user);

        sellerService.save(seller);

        return "redirect:/user-login";
    }

    @GetMapping({"/seller-account"})
    public String displayAccountPage(Model model) {

        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);
        int userId = user.getUserId();
        Seller seller = sellerService.findByUserId(userId);

        model.addAttribute("user", user);
        model.addAttribute("states", lookupService.findAllStates());
        model.addAttribute("seller", seller);
        model.addAttribute("categories", lookupService.findAllCategories());
        model.addAttribute("styles", lookupService.findAllStyles());
        model.addAttribute("subjects", lookupService.findAllSubjects());
        model.addAttribute("mediums", lookupService.findAllMediums());
        model.addAttribute("materials", lookupService.findAllMaterials());

        return "seller-account";
    }

    @GetMapping({"/seller-public-page"})
    public String displaySellerPublicPage(String sellerIdString, Model model) {

        if (sellerIdString != null) {
            int sellerId = Integer.parseInt(sellerIdString);
            Seller seller = sellerService.findById(sellerId);

            model.addAttribute("seller", seller);
        } else {
            return "error";
        }

        return "seller-public-page";
    }

    @GetMapping({"/seller-public-page-admin-view"})
    public String displaySellerPublicPageAdminView(String sellerIdString, Model model) {

        if (sellerIdString != null) {
            int sellerId = Integer.parseInt(sellerIdString);
            Seller seller = sellerService.findById(sellerId);

            model.addAttribute("seller", seller);
        } else {
            return "error";
        }

        return "seller-public-page-admin-view";
    }

    @ResponseBody
    @GetMapping("/seller/load-seller-artwork/")
    public Seller loadSellerArtwork() {
        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);
        int userId = user.getUserId();
        Seller seller = sellerService.findByUserId(userId);
        List<Product> productListBefore = seller.getProducts();
        List<Product> productListAfter = new ArrayList<>();

        for (Product product : productListBefore) {
            if (product.getProductStatus() != 2) {
                productListAfter.add(product);
            }
        }

        seller.setProducts(productListAfter);

        return seller;
    }

    @ResponseBody
    @PostMapping("/seller/delete-seller-artwork/")
    public ResponseEntity deleteSellerArtwork(@RequestBody Map<String, String> map) {

        String userIdString = map.get("userId");
        String sellerIdString = map.get("sellerId");
        String productIdString = map.get("productId");
        int userId;
        int sellerId;
        int productId;

        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);
        int serverUserId = user.getUserId();
        Seller seller = sellerService.findByUserId(serverUserId);
        int serverSellerId = seller.getSellerId();

        try {
            userId = Integer.parseInt(userIdString);
            sellerId = Integer.parseInt(sellerIdString);
            productId = Integer.parseInt(productIdString);
        } catch (NumberFormatException e) {
            return new ResponseEntity("Something went wrong...:(", HttpStatus.FORBIDDEN);
        }

        if (serverUserId == userId && serverSellerId == sellerId) {
            List<Product> productList = seller.getProducts();

            for (Product product : productList) {
                if (product.getProductId() == productId) {
                    product.setProductStatus(2);
                }
            }

            seller.setProducts(productList);

            sellerService.save(seller);

            return new ResponseEntity("Successfully deleted artwork.", HttpStatus.OK);

        } else {
            try {
                throw new LoggedInUserDoesNotEqualUserIdFromAJAX("Request data from AJAX does not match server data.");
            } catch (LoggedInUserDoesNotEqualUserIdFromAJAX e) {
                return new ResponseEntity(e, HttpStatus.FORBIDDEN);
            }
        }

    }

    @ResponseBody
    @PostMapping("/seller/add-seller-artwork/")
    public ResponseEntity addSellerArtwork(@RequestParam("sellerAccountPageListArtworkSectionAddItemTitle") String title,
            @RequestParam("sellerAccountPageListArtworkSectionAddItemDescription") String description,
            @RequestParam("sellerAccountPageListArtworkSectionAddItemPrice") String priceString,
            @RequestParam("sellerAccountPageListArtworkSectionAddItemQuantity") String quantityString,
            @RequestParam("sellerAccountPageListArtworkSectionAddItemPhoto") MultipartFile photoFile,
            @RequestParam(value = "sellerAccountPageListArtworkSectionAddCategory", required = false) String categoryIdString,
            @RequestParam(value = "sellerAccountPageListArtworkSectionAddStyle", required = false) String styleIdString,
            @RequestParam(value = "sellerAccountPageListArtworkSectionAddSubject", required = false) String subjectIdString,
            @RequestParam(value = "sellerAccountPageListArtworkSectionAddMedium", required = false) String mediumIdString,
            @RequestParam(value = "sellerAccountPageListArtworkSectionAddMaterial", required = false) String materialIdString) {

        int quantity;
        int categoryId;
        int styleId;
        int subjectId;
        int mediumId;
        int materialId;
        String photoUrl = "/uploaded-photos/" + photoFile.getOriginalFilename();

        try {
            // Get the filename and build the local file path (be sure that the 
            // application have write permissions on such directory)
            String filename = photoFile.getOriginalFilename();
            String directory = "C:\\Users\\Executor\\Desktop\\public_html\\dev10-software-guild-files\\Capstone\\Website\\imaginapainting\\src\\main\\resources\\static\\uploaded-photos";
            String filepath = Paths.get(directory, filename).toString();

            // Save the file locally
            BufferedOutputStream stream
                    = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(photoFile.getBytes());
            stream.close();
        } catch (IOException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }

        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);
        int serverUserId = user.getUserId();
        Seller seller = sellerService.findByUserId(serverUserId);

        try {
            quantity = Integer.parseInt(quantityString);
        } catch (NumberFormatException e) {
            return new ResponseEntity("Something went wrong...:(", HttpStatus.FORBIDDEN);
        }

        Product newProduct = new Product();
        newProduct.setTitle(title);
        newProduct.setDescription(description);

        try {
            newProduct.setPrice(new BigDecimal(priceString));
        } catch (NumberFormatException e) {
            return new ResponseEntity("Is your price a number?", HttpStatus.FORBIDDEN);
        }

        newProduct.setQuantity(quantity);

        try {
            categoryId = Integer.parseInt(categoryIdString);
            Category category = new Category();
            category.setCategoryId(categoryId);
            newProduct.setCategory(category);
        } catch (NumberFormatException e) {
            newProduct.setCategory(null);
        }

        try {
            styleId = Integer.parseInt(styleIdString);
            Style style = new Style();
            style.setStyleId(styleId);
            newProduct.setStyle(style);
        } catch (NumberFormatException e) {
            newProduct.setStyle(null);
        }

        try {
            subjectId = Integer.parseInt(subjectIdString);
            Subject subject = new Subject();
            subject.setSubjectId(subjectId);
            newProduct.setSubject(subject);
        } catch (NumberFormatException e) {
            newProduct.setSubject(null);
        }

        try {
            mediumId = Integer.parseInt(mediumIdString);
            Medium medium = new Medium();
            medium.setMediumId(mediumId);
            newProduct.setMedium(medium);
        } catch (NumberFormatException e) {
            newProduct.setMedium(null);
        }

        try {
            materialId = Integer.parseInt(materialIdString);
            Material material = new Material();
            material.setMaterialId(materialId);
            newProduct.setMaterial(material);
        } catch (NumberFormatException e) {
            newProduct.setMaterial(null);
        }

        newProduct.setProductStatus(1);
        newProduct.setPhotoUrl(photoUrl);

        newProduct = productService.save(newProduct);

        List<Product> sellerProducts = seller.getProducts();

        sellerProducts.add(newProduct);

        seller.setProducts(sellerProducts);

        sellerService.save(seller);

        return new ResponseEntity("Successfully added artwork.", HttpStatus.OK);

    }

    @ResponseBody
    @GetMapping("/seller/edit-seller-artwork-populate-modal/{productIdString}")
    public Product editSellerArtworkPopulateModal(@PathVariable String productIdString) {
        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);
        int userId = user.getUserId();
        Seller seller = sellerService.findByUserId(userId);

        int productId = Integer.parseInt(productIdString);

        List<Product> sellerProductList = seller.getProducts();

        Product returnedProduct = null;

        for (Product product : sellerProductList) {
            if (product.getProductId() == productId) {
                returnedProduct = product;
            }
        }

        return returnedProduct;

    }

    @ResponseBody
    @PostMapping("/seller/edit-seller-artwork-submit/")
    public ResponseEntity editSellerArtworkSubmit(@RequestParam("sellerAccountPageListArtworkSectionEditItemTitle") String title,
            @RequestParam("sellerAccountPageListArtworkSectionEditItemDescription") String description,
            @RequestParam("sellerAccountPageListArtworkSectionEditItemPrice") String priceString,
            @RequestParam("sellerAccountPageListArtworkSectionEditItemQuantity") String quantityString,
            @RequestParam("sellerAccountPageListArtworkSectionEditItemPhoto") MultipartFile photoFile,
            @RequestParam(value = "sellerAccountPageListArtworkSectionEditCategory", required = false) String categoryIdString,
            @RequestParam(value = "sellerAccountPageListArtworkSectionEditStyle", required = false) String styleIdString,
            @RequestParam(value = "sellerAccountPageListArtworkSectionEditSubject", required = false) String subjectIdString,
            @RequestParam(value = "sellerAccountPageListArtworkSectionEditMedium", required = false) String mediumIdString,
            @RequestParam(value = "sellerAccountPageListArtworkSectionEditMaterial", required = false) String materialIdString,
            @RequestParam(value = "sellerEditArtworkProductId") String productIdString) {

        int quantity;
        int categoryId;
        int styleId;
        int subjectId;
        int mediumId;
        int materialId;
        int productId;
        String photoUrl = "/uploaded-photos/" + photoFile.getOriginalFilename();

        try {
            // Get the filename and build the local file path (be sure that the 
            // application have write permissions on such directory)
            String filename = photoFile.getOriginalFilename();
            String directory = "C:\\Users\\Executor\\Desktop\\public_html\\dev10-software-guild-files\\Capstone\\Website\\imaginapainting\\src\\main\\resources\\static\\uploaded-photos";
            String filepath = Paths.get(directory, filename).toString();

            // Save the file locally
            BufferedOutputStream stream
                    = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(photoFile.getBytes());
            stream.close();
        } catch (IOException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }

        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);
        int serverUserId = user.getUserId();
        Seller seller = sellerService.findByUserId(serverUserId);

        try {
            quantity = Integer.parseInt(quantityString);
            productId = Integer.parseInt(productIdString);
        } catch (NumberFormatException e) {
            return new ResponseEntity("Something went wrong...:(", HttpStatus.FORBIDDEN);
        }

        Product editedProduct = productService.findById(productId);
        editedProduct.setTitle(title);
        editedProduct.setDescription(description);

        try {
            editedProduct.setPrice(new BigDecimal(priceString));
        } catch (NumberFormatException e) {
            return new ResponseEntity("Is your price a number?", HttpStatus.FORBIDDEN);
        }

        editedProduct.setQuantity(quantity);

        try {
            categoryId = Integer.parseInt(categoryIdString);
            Category category = new Category();
            category.setCategoryId(categoryId);
            editedProduct.setCategory(category);
        } catch (NumberFormatException e) {
            editedProduct.setCategory(null);
        }

        try {
            styleId = Integer.parseInt(styleIdString);
            Style style = new Style();
            style.setStyleId(styleId);
            editedProduct.setStyle(style);
        } catch (NumberFormatException e) {
            editedProduct.setStyle(null);
        }

        try {
            subjectId = Integer.parseInt(subjectIdString);
            Subject subject = new Subject();
            subject.setSubjectId(subjectId);
            editedProduct.setSubject(subject);
        } catch (NumberFormatException e) {
            editedProduct.setSubject(null);
        }

        try {
            mediumId = Integer.parseInt(mediumIdString);
            Medium medium = new Medium();
            medium.setMediumId(mediumId);
            editedProduct.setMedium(medium);
        } catch (NumberFormatException e) {
            editedProduct.setMedium(null);
        }

        try {
            materialId = Integer.parseInt(materialIdString);
            Material material = new Material();
            material.setMaterialId(materialId);
            editedProduct.setMaterial(material);
        } catch (NumberFormatException e) {
            editedProduct.setMaterial(null);
        }

        editedProduct.setProductStatus(1);
        editedProduct.setPhotoUrl(photoUrl);

        productService.save(editedProduct);

        return new ResponseEntity("Successfully edited artwork.", HttpStatus.OK);

    }

    @ResponseBody
    @GetMapping("/seller/load-public-profile-details/")
    public Seller loadPublicProfileDetails() {
        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);
        int userId = user.getUserId();
        Seller seller = sellerService.findByUserId(userId);

        return seller;

    }

    @ResponseBody
    @PostMapping("/seller/edit-public-profile-details/")
    public ResponseEntity editPublicProfileDetails(@RequestParam("sellerAccountPagePublicProfileDetailsPublicName") String publicName,
            @RequestParam("sellerAccountPagePublicProfileDetailsPublicCompany") String publicCompany,
            @RequestParam("sellerAccountPagePublicProfileDetailsPublicIntroduction") String publicIntroduction,
            @RequestParam("sellerAccountPagePublicProfileDetailsPublicImageUpload") MultipartFile photoFile) {

        String photoUrl = "/uploaded-photos/" + photoFile.getOriginalFilename();

        try {
            // Get the filename and build the local file path (be sure that the 
            // application have write permissions on such directory)
            String filename = photoFile.getOriginalFilename();
            String directory = "C:\\Users\\Executor\\Desktop\\public_html\\dev10-software-guild-files\\Capstone\\Website\\imaginapainting\\src\\main\\resources\\static\\uploaded-photos";
            String filepath = Paths.get(directory, filename).toString();

            // Save the file locally
            BufferedOutputStream stream
                    = new BufferedOutputStream(new FileOutputStream(new File(filepath)));
            stream.write(photoFile.getBytes());
            stream.close();
        } catch (IOException e) {
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }

        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);
        int serverUserId = user.getUserId();
        Seller seller = sellerService.findByUserId(serverUserId);

        seller.getUser().setAcctStatus(4);
        seller.setPublicName(publicName);
        seller.setPublicCompany(publicCompany);
        seller.setPublicIntroduction(publicIntroduction);
        seller.setPhotoUrl(photoUrl);

        sellerService.save(seller);

        return new ResponseEntity("Successfully updated public profile details.", HttpStatus.OK);

    }

    @ResponseBody
    @GetMapping("/seller/load-seller-orders/")
    public List<Order> loadSellerOrders() {
        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);
        int userId = user.getUserId();
        Seller seller = sellerService.findByUserId(userId);
        int sellerId = seller.getSellerId();

        List<Product> allSellerProducts = seller.getProducts();

        List<Order> orderList = orderService.findBySellerId(sellerId);

        List<Order> orderListModified = new ArrayList<>();
        for (Order order : orderList) {

            List<Product> orderProducts = order.getProducts();
            List<Product> orderProductsModified = new ArrayList<>();

            for (Product orderProduct : orderProducts) {

                for (Product sellerProduct : allSellerProducts) {

                    if (orderProduct.getProductId() == sellerProduct.getProductId()) {
                        orderProductsModified.add(orderProduct);
                    }

                }

            }

            order.setProducts(orderProductsModified);
            orderListModified.add(order);

        }

        return orderListModified;

    }

    @ResponseBody
    @GetMapping("/seller/load-seller-details/")
    public User loadSellerDetails() {
        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);
        return user;
    }

    @ResponseBody
    @PostMapping("/seller/change-seller-details/")
    public ResponseEntity changeSellerDetails(@RequestBody Map<String, String> map) {
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

        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);
        int sellerUserId = user.getUserId();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setStreetAddress(streetAddress);
        user.setAptUnit(aptUnit);
        user.setCity(city);
        user.setState(state);
        user.setZip(zip);
        user.setUsername(username);

        if (sellerUserId == userId) {
            userService.save(user);

            if (!username.equals(sellerUsername)) {
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
    @PostMapping("/seller/change-seller-password/")
    public ResponseEntity changeSellerPassword(@RequestBody Map<String, String> map) {

        String currentPassword = map.get("currentPassword");
        String newPassword = map.get("newPassword");
        String newPassword2 = map.get("newPassword2");

        try {
            userService.validatePassword(currentPassword, newPassword, newPassword2);
        } catch (InvalidPasswordException e) {
            return new ResponseEntity(e, HttpStatus.FORBIDDEN);
        }

        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);

        user.setPassword(encoder.encode(newPassword2));

        userService.save(user);

        SecurityContextHolder.getContext().setAuthentication(null);

        return new ResponseEntity("Successfully edited user details.", HttpStatus.OK);

    }

    @ResponseBody
    @PostMapping("/seller/delete-seller-account/")
    public ResponseEntity deleteSellerAccount(@RequestBody Map<String, String> map) {

        String sellerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(sellerUsername);
        int serverUserId = user.getUserId();
        Seller seller = sellerService.findByUserId(serverUserId);
        int serverSellerId = seller.getSellerId();

        sellerService.changeSellerAccountStatus(serverSellerId, 3);

        SecurityContextHolder.getContext().setAuthentication(null);

        return new ResponseEntity("Successfully deleted your account.", HttpStatus.OK);

    }

}
