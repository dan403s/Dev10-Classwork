/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.controller;

import com.dvb.imaginapainting.config.SecurityUtils;
import com.dvb.imaginapainting.entities.Buyer;
import com.dvb.imaginapainting.entities.BuyerCart;
import com.dvb.imaginapainting.entities.Order;
import com.dvb.imaginapainting.entities.Product;
import com.dvb.imaginapainting.entities.Seller;
import com.dvb.imaginapainting.entities.State;
import com.dvb.imaginapainting.entities.User;
import com.dvb.imaginapainting.service.BuyerCartEmptyException;
import com.dvb.imaginapainting.service.BuyerCartProductNotActiveException;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class BuyerCartController {

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

    @GetMapping({"/cart"})
    public String displayCartPage(Model model) {

        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);
        int userId = user.getUserId();
        Buyer buyer = buyerService.findByUserId(userId);
        BuyerCart buyerCart = buyer.getBuyerCart();

        model.addAttribute("states", lookupService.findAllStates());
        model.addAttribute("buyerCart", buyerCart);
        model.addAttribute("buyer", buyer);
        model.addAttribute("user", user);

        return "cart";
    }

    @ResponseBody
    @PostMapping("/buyer-cart/remove-from-cart/")
    public ResponseEntity removeFromCart(@RequestBody Map<String, String> map) {

        String productIdstring = map.get("productId");
        int productId = Integer.parseInt(productIdstring);

        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);
        int userId = user.getUserId();
        Buyer buyer = buyerService.findByUserId(userId);
        BuyerCart buyerCart = buyer.getBuyerCart();

        List<Product> buyerCartProductListBefore = buyerCart.getProducts();
        List<Product> buyerCartProductListAfter = new ArrayList<>();
        BigDecimal newTotal = new BigDecimal("0");

        for (Product buyerCartProduct : buyerCartProductListBefore) {

            if (buyerCartProduct.getProductId() != productId) {
                buyerCartProductListAfter.add(buyerCartProduct);
                newTotal = newTotal.add(buyerCartProduct.getPrice());
            }

        }

        buyerCart.setProducts(buyerCartProductListAfter);
        buyerCart.setTotal(newTotal);

        buyer.getBuyerCart().setProducts(buyerCartProductListAfter);
        buyer.getBuyerCart().setTotal(newTotal);

        buyerService.save(buyer);

        return new ResponseEntity("Successfully deleted item from cart.", HttpStatus.OK);

    }

    @PostMapping("/checkout-process")
    public String processCheckout(String cartPageFirstName, String cartPageLastName, String cartPageStreetAddress, String cartPageAptUnitNumber, String cartPageCity, String cartPageState, String cartPageZip, Model model) {

        String buyerUsername = SecurityUtils.getUserName();
        User user = userService.findByUsername(buyerUsername);
        int userId = user.getUserId();
        Buyer buyer = buyerService.findByUserId(userId);
        BuyerCart buyerCart = buyer.getBuyerCart();

        List<Product> buyerCartProductList = buyerCart.getProducts();

        List<Seller> cartProductListSellers = new ArrayList<>();

        for (Product buyerCartProduct : buyerCartProductList) {
            cartProductListSellers.add(sellerService.findByProduct(buyerCartProduct.getProductId()));
        }

        try {
            buyerCartService.checkCartProductInStock(buyerCartProductList);
        } catch (OutOfStockException e) {
            return "redirect:/cart?product-out-of-stock-error=1";
        }

        try {
            buyerCartService.checkProductIsActiveAndSellerIsActive(buyerCartProductList, cartProductListSellers);
        } catch (BuyerCartProductNotActiveException e) {
            return "redirect:/cart?product-not-active-error=1";
        }

        try {
            buyerCartService.checkForEmptyCart(buyerCartProductList);
        } catch (BuyerCartEmptyException e) {
            return "redirect:/cart?empty-cart-error=1";
        }

//        State state = new State();
//        state.setStateId(cartPageState);
//        
//        Order order = new Order();
        LocalDate ld = LocalDate.now();
//        order.setDate(ld);
//        order.setTotal(buyerCart.getTotal());
//        order.setFirstName(cartPageFirstName);
//        order.setLastName(cartPageLastName);
//        order.setStreetAddress(cartPageStreetAddress);
//        order.setAptUnit(cartPageAptUnitNumber);
//        order.setCity(cartPageCity);
//        order.setZip(cartPageZip);
//        order.setState(state);
//        order.setBuyer(buyer);
//        order.setSellers(cartProductListSellers);
//        order.setProducts(buyerCartProductList);
//        
//        order = orderService.save(order);

        int orderId = orderService.addOrder(ld, buyerCart.getTotal(), cartPageFirstName, cartPageLastName, cartPageStreetAddress, cartPageAptUnitNumber, cartPageCity, cartPageZip, cartPageState, buyer.getBuyerId());

        int previousProductSellerId = 0;
        for (Product purchasedProduct : buyerCartProductList) {
            orderService.addOrderProduct(orderId, purchasedProduct.getProductId());

            Seller productSeller = sellerService.findByProduct(purchasedProduct.getProductId());
            int productSellerId = productSeller.getSellerId();

            if (productSellerId != previousProductSellerId) {
                orderService.addSellerOrder(productSellerId, orderId);
            }
            previousProductSellerId = productSellerId;
        }

//        List<Order> buyerOrders = buyer.getOrders();
//        buyerOrders.add(order);
//        buyer.setOrders(buyerOrders);
        buyer.getBuyerCart().setProducts(new ArrayList<>());
        buyer.getBuyerCart().setTotal(new BigDecimal("0"));

        buyerService.save(buyer);

//        for (Seller seller : cartProductListSellers) {
//            List<Order> sellerOrders = seller.getOrders();
//            sellerOrders.add(order);
//            seller.setOrders(sellerOrders);
//            sellerService.save(seller);
//        }
        for (Product purchasedProduct : buyerCartProductList) {
            int productQuantity = purchasedProduct.getQuantity();
            productQuantity--;
            purchasedProduct.setQuantity(productQuantity);

            productService.save(purchasedProduct);
        }

        return "redirect:/order-success";
    }

}
