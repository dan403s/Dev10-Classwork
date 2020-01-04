/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.dao;

import com.dvb.flooringmastery.dto.Order;
import com.dvb.flooringmastery.dto.Product;
import com.dvb.flooringmastery.dto.Taxes;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 *
 * @author Daniel Bart
 */
public class FlooringMasteryDaoFileImpl implements FlooringMasteryDao {

    // declare variables/constants and initialize them
    public static final String DELIMITER = "::";

    // map to store orders with orderNumber as key and Order object as value
    private SortedMap<String, Order> ordersMap = new TreeMap<>(new Comparator<String>() {
        @Override
        public int compare(String orderNumber1, String orderNumber2) {
//                Integer int1 = Integer.parseInt(orderNumber1);
//                Integer int2 = Integer.parseInt(orderNumber2);
            return orderNumber1.compareTo(orderNumber2);
        }
    });

    // map to store product information with productType as key and Product object as value
    private Map<String, Product> productsMap = new HashMap<>();

    // map to store tax information with state as key and taxrate as value
    private Map<String, Taxes> taxesMap = new HashMap<>();

    // public methods here
    // display all orders ----------------------------------------------------------
    @Override
    public List<Order> getOrders() throws FlooringMasteryPersistenceException {
        // get training or production mode
        String trainingOrProdMode = loadTrainingOrProdMode();
        // load map
        loadOrders(trainingOrProdMode);
        // return ArrayList of Order objects
        return new ArrayList<>(ordersMap.values());
    }

    // display orders by date user entered -----------------------------------------
    @Override
    public List<Order> getOrders(LocalDate responseLocalDate) throws FlooringMasteryPersistenceException {
        // get training or production mode
        String trainingOrProdMode = loadTrainingOrProdMode();
        // load map
        loadOrders(trainingOrProdMode);
        // return ArrayList of Order objects
        return new ArrayList<>(ordersMap.values())
                .stream()
                .filter(order -> order.getDate().equals(responseLocalDate))
                .collect(Collectors.toList());
    }

    // get productType Set from products map ---------------------------------------
    @Override
    public Set<String> getProductTypeSet() throws FlooringMasteryPersistenceException {
        loadProducts();
        return productsMap.keySet();
    }

    // get state Set from taxes map ------------------------------------------------
    @Override
    public Set<String> getStateSet() throws FlooringMasteryPersistenceException {
        loadTaxes();
        return taxesMap.keySet();
    }

    // add order -------------------------------------------------------------------
    // return productsMap ----------------------------------------------------------
    @Override
    public Map<String, Product> getProductsMap() throws FlooringMasteryPersistenceException {
        loadProducts();
        return productsMap;
    }

    // return taxesMap -------------------------------------------------------------
    @Override
    public Map<String, Taxes> getTaxesMap() throws FlooringMasteryPersistenceException {
        loadTaxes();
        return taxesMap;
    }

    // add order to ordersMap ------------------------------------------------------
    @Override
    public Order addOrder(Order newOrder) throws FlooringMasteryPersistenceException {
        // get training or production mode
        String trainingOrProdMode = loadTrainingOrProdMode();
        // load map
        loadOrders(trainingOrProdMode);
        // add to ordersMap
        Order order = ordersMap.put(newOrder.getOrderNumber(), newOrder);
        // write map
        writeOrders(trainingOrProdMode);

        return order;
    }

    // display order by orderNumber ------------------------------------------------
    @Override
    public Order getOrderByOrderNumber(String orderNumber) throws FlooringMasteryPersistenceException {
        // get training or production mode
        String trainingOrProdMode = loadTrainingOrProdMode();
        // load map
        loadOrders(trainingOrProdMode);
        return ordersMap.get(orderNumber);
    }

    // remove order ----------------------------------------------------------------
    @Override
    public Order removeOrder(Order orderToRemove) throws FlooringMasteryPersistenceException {
        // get training or production mode
        String trainingOrProdMode = loadTrainingOrProdMode();
        // load map
        loadOrders(trainingOrProdMode);
        // remove from ordersMap
        Order removedOrder = ordersMap.remove(orderToRemove.getOrderNumber());
        // write map
        writeOrders(trainingOrProdMode);

        return removedOrder;
    }

    // edit order ------------------------------------------------------------------
    @Override
    public Order editOrder(Order orderToEdit) throws FlooringMasteryPersistenceException {
        // get training or production mode
        String trainingOrProdMode = loadTrainingOrProdMode();
        // load map
        loadOrders(trainingOrProdMode);
        // add to ordersMap
        Order oldOrder = ordersMap.replace(orderToEdit.getOrderNumber(), orderToEdit);
        // write map
        writeOrders(trainingOrProdMode);

        return oldOrder;
    }

    // DATA MARSHALLING AND UNMARSHALLING FOR ORDERS--------------------------------
    // convert line of text in file to Order object
    private Order unmarshallOrder(String orderAsText) {
        // split each line on the delimiter
        String[] orderTokens = orderAsText.split(DELIMITER);

        // get HashMap key value from orderTokens array
        String orderNumber = orderTokens[0];

        // instantiate new Order object with title passed in the constructor
        Order orderFromFile = new Order(orderNumber);

        // use setters to input file text into Order object fields
        orderFromFile.setCustomerName(orderTokens[1]);
        orderFromFile.setState(orderTokens[2]);
        orderFromFile.setTaxRate(new BigDecimal(orderTokens[3]).divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));
        orderFromFile.setProductType(orderTokens[4]);
        orderFromFile.setArea(new BigDecimal(orderTokens[5]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setCostPerSquareFoot(new BigDecimal(orderTokens[6]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setLaborCostPerSquareFoot(new BigDecimal(orderTokens[7]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setMaterialCost(new BigDecimal(orderTokens[8]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setLaborCost(new BigDecimal(orderTokens[9]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setTax(new BigDecimal(orderTokens[10]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setTotal(new BigDecimal(orderTokens[11]).setScale(2, RoundingMode.HALF_UP));
        orderFromFile.setDate(LocalDate.parse(orderTokens[12], DateTimeFormatter.ofPattern("MM/dd/yyyy")));

        return orderFromFile;
    }

    // read orders file into memory in HashMap
    private void loadOrders(String trainingOrProd) throws FlooringMasteryPersistenceException {
        // declare new Scanner object to read file
        Scanner sc;

        // try to load file, if not, throw our custom error and not the default 
        // (this hides implementation details and maintains encapsulation)
        try {
            sc = new Scanner(new BufferedReader(new FileReader("Orders-" + trainingOrProd + ".txt")));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not load order data.", e);
        }

        // currentLine holds most recent line read from file
        String currentLine;

        // currentOrder holds most recent Order unmarshalled
        Order currentOrder;

        // go through file line by line, decoding each line into an Order object
        // add Order objects to HashMap
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentOrder = unmarshallOrder(currentLine);
            ordersMap.put(currentOrder.getOrderNumber(), currentOrder);
        }
        // close scanner
        sc.close();

    }

    // convert Order object to line of text
    private String marshallOrder(Order anOrder) {
        // get title first because that is the key when we unmarshall
        String orderAsText = anOrder.getOrderNumber() + DELIMITER;

        // get all other variables and convert to string for file insertion
        orderAsText += anOrder.getCustomerName() + DELIMITER;
        orderAsText += anOrder.getState() + DELIMITER;
        orderAsText += anOrder.getTaxRate().multiply(new BigDecimal(100)).setScale(2, RoundingMode.HALF_UP) + DELIMITER;
        orderAsText += anOrder.getProductType() + DELIMITER;
        orderAsText += anOrder.getArea().setScale(2, RoundingMode.HALF_UP) + DELIMITER;
        orderAsText += anOrder.getCostPerSquareFoot().setScale(2, RoundingMode.HALF_UP) + DELIMITER;
        orderAsText += anOrder.getLaborCostPerSquareFoot().setScale(2, RoundingMode.HALF_UP) + DELIMITER;
        orderAsText += anOrder.getMaterialCost().setScale(2, RoundingMode.HALF_UP) + DELIMITER;
        orderAsText += anOrder.getLaborCost().setScale(2, RoundingMode.HALF_UP) + DELIMITER;
        orderAsText += anOrder.getTax().setScale(2, RoundingMode.HALF_UP) + DELIMITER;
        orderAsText += anOrder.getTotal().setScale(2, RoundingMode.HALF_UP) + DELIMITER;
        orderAsText += anOrder.getDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        return orderAsText;

    }

    // write HashMap memory into file
    private void writeOrders(String trainingOrProd) throws FlooringMasteryPersistenceException {
        // declare PrintWriter
        PrintWriter out;

        // try to save to file, if cannot throw our error
        try {
            out = new PrintWriter(new FileWriter("Orders-" + trainingOrProd + ".txt"));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not save order data.", e);
        }

        // write out Order objects to file
        String orderAsText;
        // call displayOrders method to return ArrayList of Order objects and
        // store in variable
        List<Order> orderList = this.getOrders();
        // loop through all Order objects in ArrayList and write each to file
        for (Order currentOrder : orderList) {
            orderAsText = marshallOrder(currentOrder);
            out.println(orderAsText);
            // force PrintWriter to write to file
            out.flush();
        }
        out.close();
    }

    // DATA UNMARSHALLING FOR PRODUCTS ---------------------------------------------
    // convert line of text in file to Product object
    private Product unmarshallProduct(String productAsText) {
        // split each line on the delimiter
        String[] productTokens = productAsText.split(DELIMITER);

        // get HashMap key value from productTokens array
        String productType = productTokens[0];

        // instantiate new Product object with title passed in the constructor
        Product productFromFile = new Product(productType);

        // use setters to input file text into Product object fields
        productFromFile.setCostPerSquareFoot(new BigDecimal(productTokens[1]).setScale(2, RoundingMode.HALF_UP));
        productFromFile.setLaborCostPerSquareFoot(new BigDecimal(productTokens[2]).setScale(2, RoundingMode.HALF_UP));

        return productFromFile;
    }

    // read products file into memory in HashMap
    private void loadProducts() throws FlooringMasteryPersistenceException {
        // declare new Scanner object to read file
        Scanner sc;

        // try to load file, if not, throw our custom error and not the default 
        // (this hides implementation details and maintains encapsulation)
        try {
            sc = new Scanner(new BufferedReader(new FileReader("Products.txt")));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not load products.", e);
        }

        // currentLine holds most recent line read from file
        String currentLine;

        // currentProduct holds most recent Product unmarshalled
        Product currentProduct;

        // go through file line by line, decoding each line into an Product object
        // add Product objects to HashMap
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            productsMap.put(currentProduct.getProductType(), currentProduct);
        }
        // close scanner
        sc.close();

    }

    // DATA UNMARSHALLING FOR TAXES ------------------------------------------------
    // convert line of text in file to Taxes object
    private Taxes unmarshallTaxes(String taxesAsText) {
        // split each line on the delimiter
        String[] taxesTokens = taxesAsText.split(DELIMITER);

        // get HashMap key value from taxesTokens array
        String state = taxesTokens[0];

        // instantiate new Taxes object with title passed in the constructor
        Taxes taxesFromFile = new Taxes(state);

        // use setters to input file text into Taxes object fields
        taxesFromFile.setTaxRate(new BigDecimal(taxesTokens[1]).divide(new BigDecimal(100)).setScale(4, RoundingMode.HALF_UP));

        return taxesFromFile;
    }

    // read taxes file into memory in HashMap
    private void loadTaxes() throws FlooringMasteryPersistenceException {
        // declare new Scanner object to read file
        Scanner sc;

        // try to load file, if not, throw our custom error and not the default 
        // (this hides implementation details and maintains encapsulation)
        try {
            sc = new Scanner(new BufferedReader(new FileReader("Taxes.txt")));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not load taxes.", e);
        }

        // currentLine holds most recent line read from file
        String currentLine;

        // currentProduct holds most recent Product unmarshalled
        Taxes currentTaxes;

        // go through file line by line, decoding each line into an Product object
        // add Product objects to HashMap
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTaxes = unmarshallTaxes(currentLine);
            taxesMap.put(currentTaxes.getState(), currentTaxes);
        }
        // close scanner
        sc.close();

    }

    // DATA UNMARSHALLING FOR TRAINING OR PRODUCTION MODE---------------------------
    // read training or production condiguration file into memory in variable
    @Override
    public String loadTrainingOrProdMode() throws FlooringMasteryPersistenceException {
        // declare new Scanner object to read file
        Scanner sc;

        // try to load file, if not, throw our custom error and not the default 
        // (this hides implementation details and maintains encapsulation)
        try {
            sc = new Scanner(new BufferedReader(new FileReader("TrainingOrProdMode.txt")));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not load training or production configuration file.", e);
        }

        String trainingOrProd = "";

        // go through file and pull only line
        while (sc.hasNextLine()) {
            trainingOrProd = sc.nextLine();
        }
        // close scanner
        sc.close();

        return trainingOrProd;

    }

    // write training or production mode to file
    @Override
    public void writeTrainingOrProdMode(String trainingOrProd) throws FlooringMasteryPersistenceException {
        // declare PrintWriter
        PrintWriter out;

        // try to save to file, if cannot throw our error
        try {
            out = new PrintWriter(new FileWriter("TrainingOrProdMode.txt"));
        } catch (IOException e) {
            throw new FlooringMasteryPersistenceException("Could not save training or production configuration file.", e);
        }

        out.println(trainingOrProd);
        out.flush();
        out.close();
    }

}
