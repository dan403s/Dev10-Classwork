/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.ui;

import com.dvb.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Daniel Bart
 */
public class FlooringMasteryView {

    // declare objects to be instantiated for dependency injection
    UserIO io;

    // constructor to initialize object for dependency injection
    public FlooringMasteryView(UserIO io) {
        this.io = io;
    }

    // regex for orderNumber
    String regexOrderNumber = "^[0-9]{2}/[0-9]{2}/[0-9]{4}:[0-9]{2}:[0-9]{2}:[0-9]{2}$";

    // getting user choice for training or production mode -------------------------
    public String promptForTrainingOrProdMode() {
        io.print("\nMode Selection" + "\n1. Training" + "\n2. Production");
        int userChoice = io.readInt("Please select from the above choices.", 1, 2);
        if (userChoice == 1) {
            return "training";
        } else {
            return "prod";
        }
    }

    // getting user choice for initial menu-----------------------------------------
    public void displayInitialMenu() {
        io.print("\n* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *\n"
                + "*<<Flooring Program>>\n"
                + "* 1. Display Orders\n"
                + "* 2. Add an Order\n"
                + "* 3. Edit an Order\n"
                + "* 4. Remove an Order\n"
                + "* 5. Quit\n"
                + "*\n"
                + "* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
    }

    public int promptForUserChoice() {
        return io.readInt("\nPlease select from the above choices.", 1, 5);
    }

    // displaying orders with user choice by date or all ---------------------------
    public void displayDisplayOrderBanner() {
        io.print("\nDisplay Orders");
        io.print("1. Display All Orders");
        io.print("2. Display Orders By Date");
    }

    public int promptForDisplayOptions() {
        return io.readInt("Please select from the above choices.", 1, 2);
    }

    public LocalDate promptForDisplayOrdersDate() {
        return io.readLocalDate("Please enter the date to display orders for (MM/DD/YYYY).");
    }

    public void displayOrders(List<Order> ordersList) {
        for (Order currentOrder : ordersList) {
            displayOrder(currentOrder);
        }
    }

    public void displayOrder(Order order) {
        if (order != null) {
            if (!order.getOrderNumber().isEmpty()) {
                io.print("\nOrder Number: " + order.getOrderNumber());
            }
            if (!order.getCustomerName().isEmpty()) {
                io.print("Customer Name: " + order.getCustomerName());
            }
            if (!order.getState().isEmpty()) {
                io.print("State: " + order.getState());
            }
            if (order.getTaxRate() != null) {
                io.print("Tax Rate: " + order.getTaxRate().multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP));
            }
            if (!order.getProductType().isEmpty()) {
                io.print("Product Type: " + order.getProductType());
            }
            if (order.getArea() != null) {
                io.print("Area: " + order.getArea().setScale(2, RoundingMode.HALF_UP));
            }
            if (order.getCostPerSquareFoot() != null) {
                io.print("Cost Per Square Foot: " + order.getCostPerSquareFoot().setScale(2, RoundingMode.HALF_UP));
            }
            if (order.getLaborCostPerSquareFoot() != null) {
                io.print("Labor Cost Per Square Foot: " + order.getLaborCostPerSquareFoot().setScale(2, RoundingMode.HALF_UP));
            }
            if (order.getMaterialCost() != null) {
                io.print("Material Cost: " + order.getMaterialCost().setScale(2, RoundingMode.HALF_UP));
            }
            if (order.getLaborCost() != null) {
                io.print("Labor Cost: " + order.getLaborCost().setScale(2, RoundingMode.HALF_UP));
            }
            if (order.getTax() != null) {
                io.print("Tax: " + order.getTax().setScale(2, RoundingMode.HALF_UP));
            }
            if (order.getTotal() != null) {
                io.print("Total: " + order.getTotal().setScale(2, RoundingMode.HALF_UP));
            }
            if (order.getDate() != null) {
                io.print("Order Date: " + order.getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            }

        } else {
            io.print("\nNo such order.");
        }
    }

    // display order during edit -----------------------------------------------
    public void displayOrderEdited(Order order) {
        if (order != null) {
            io.print("\nOrder Number: " + order.getOrderNumber());
            io.print("Customer Name: " + order.getCustomerName());
            io.print("State: " + order.getState());
            io.print("Product Type: " + order.getProductType());
            io.print("Area: " + order.getArea().setScale(2, RoundingMode.HALF_UP));
            io.print("Order Date: " + order.getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        } else {
            io.print("\nNo such order.");
        }
    }

    // adding an order -------------------------------------------------------------
    public void displayAddOrderBanner() {
        io.print("\nAdd an Order");
    }

    public Order promptForAddOrderData(String state, String productType) {
        // create LocalDateTime object for orderNumber
        LocalDateTime ldt = LocalDateTime.now();
        String orderNumber = ldt.format(DateTimeFormatter.ofPattern("MM/dd/yyyy:hh:mm:ss"));
        // prompt user for Order properties
        String customerName = io.readStringNoBlanks("\nPlease enter the customer name (no blanks allowed): ");

        BigDecimal area = io.readBigDecimalPositive("\nPlease enter the area of the room (decimals are permitted): ").setScale(2, RoundingMode.HALF_UP);
        LocalDate date = io.readLocalDateOnlyPresentOrFuture("\nPlease enter the date for the order (CAN ONLY BE PRESENT OR FUTURE) (MM/DD/YYYY):");

        // instantiate new Order object DTO
        Order newOrder = new Order(orderNumber);
        // set new Order object DTO properties
        newOrder.setCustomerName(customerName);
        newOrder.setState(state);
        newOrder.setProductType(productType);
        newOrder.setArea(area);
        newOrder.setDate(date);

        displayOrder(newOrder);

        return newOrder;

    }

    public String promptForAddOrderCommit() {
        return io.readStringYesOrNo("Do you want to commit this new order to the file (Y/N)? ");
    }

    // prompt for state and productType --------------------------------------------
    public String promptForState(Set<String> stateSet) {
        // iterate through stateSet and write out to console
        io.print("\nBelow are the states we support: ");
        for (String state : stateSet) {
            io.print(state);
        }
        String state = io.readStringNoBlanks("\nPlease enter the state abbreviated like above (no blanks allowed): ");
        state = state.toUpperCase();
        return state;
    }

    public String promptForProductType(Set<String> productTypeSet) {
        // iterate through productTypeSet and write out to console
        io.print("\nBelow are the product types we install/sell: ");
        for (String productType : productTypeSet) {
            io.print(productType);
        }
        String productType = io.readStringNoBlanks("\nPlease enter the product type with all characters from above (no blanks allowed): ");
        productType = productType.substring(0, 1).toUpperCase() + productType.substring(1, productType.length()).toLowerCase();
        return productType;
    }

    public String promptForState(Set<String> stateSet, Order order) {
        // iterate through stateSet and write out to console
        io.print("\nBelow are the states we support: ");
        for (String state : stateSet) {
            io.print(state);
        }
        io.print("\nCurrent state: " + order.getState());
        String state = io.readString("\nPlease enter the state abbreviated like above (enter blank to leave unchanged): ");
        if (!state.isEmpty() && !state.isBlank()) {
            state = state.toUpperCase();
        }
        return state;
    }

    public String promptForProductType(Set<String> productTypeSet, Order order) {
        // iterate through productTypeSet and write out to console
        io.print("\nBelow are the product types we install/sell: ");
        for (String productType : productTypeSet) {
            io.print(productType);
        }
        io.print("\nCurrent product type: " + order.getProductType());
        String productType = io.readString("\nPlease enter the product type with all characters from above (enter blank to leave unchanged): ");
        if (!productType.isEmpty() && !productType.isBlank()) {
            productType = productType.substring(0, 1).toUpperCase() + productType.substring(1, productType.length()).toLowerCase();
        }
        return productType;
    }

    // editing an order ------------------------------------------------------------
    public void displayEditOrderBanner() {
        io.print("\nEdit an Order");
    }

    public Order promptForEditOrderData(String state, String productType, Order orderToEdit) {
        // prompt user for Order properties
        io.print("\nCurrent customer name: " + orderToEdit.getCustomerName());
        String customerName = io.readString("\nPlease enter the customer name (Enter blank to leave field unchanged): ");
        if (!customerName.isEmpty() && !customerName.isBlank()) {
            orderToEdit.setCustomerName(customerName);
        }

        io.print("\nCurrent area: " + orderToEdit.getArea().setScale(2, RoundingMode.HALF_UP));
        BigDecimal area = io.readBigDecimalOrNull("\nPlease enter the area of the room (decimals are permitted; enter blank to leave field unchanged): ").setScale(2, RoundingMode.HALF_UP);
        if (area.compareTo(new BigDecimal(0)) != 0) {
            orderToEdit.setArea(area);
        }

        io.print("\nCurrent date: " + orderToEdit.getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
        LocalDate date = io.readLocalDateOrNull("\nPlease enter the date for the order (MM/DD/YYYY; enter blank to leave field unchanged)");
        if (date != null) {
            orderToEdit.setDate(date);
        }

        if (!state.isEmpty() && !state.isBlank()) {
            orderToEdit.setState(state);
        }

        if (!productType.isEmpty() && !productType.isBlank()) {
            orderToEdit.setProductType(productType);
        }

        displayOrderEdited(orderToEdit);

        return orderToEdit;
    }

    public String promptForEditOrderCommit() {
        return io.readStringYesOrNo("Do you want to commit this edited order to the file (Y/N)? ");
    }

    // removing an order -----------------------------------------------------------
    public void displayRemoveOrderBanner() {
        io.print("\nRemove an Order");
    }

    public String promptForRemoveOrderCommit() {
        return io.readStringYesOrNo("Are you absolutely sure that you wish to remove this order, this cannot be undone (Y/N)? ");
    }

    // prompt for orderNumber ------------------------------------------------------
    public String promptForOrderNumber() {
        String orderNumber = "";
        boolean isValid = false;

        do {
            try {
                orderNumber = io.readStringColonsAllowed("\nPlease enter the order number of the order you want to access (MM/DD/YYYY:hh:mm:ss): ");
                if (orderNumber.matches(regexOrderNumber)) {
                    isValid = true;
                } else {
                    throw new UnsupportedIOFormatException("ERROR!!! Order Number must be in MM/DD/YYYY:hh:mm:ss format!!! ");
                }
            } catch (UnsupportedIOFormatException ex) {
                System.out.println(ex.getMessage());
            }
        } while (!isValid);

        return orderNumber;
    }

    // displaying exit message------------------------------------------------------
    public void displayExitBanner() {
        io.print("\nThank you for using our system.");
    }

    // displaying error message ----------------------------------------------------
    public void displayErrorMessage(String msg) {
        io.print("\nError!");
        io.print(msg);
    }

    // prompting user to continue --------------------------------------------------
    public void promptForContinue() {
        io.readString("\nPlease hit enter to continue");
    }

}
