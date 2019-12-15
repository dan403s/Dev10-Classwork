/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.addressbook.dao;

import com.dvb.addressbook.dto.Address;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Daniel Bart
 */
public class AddressBookDaoFileImpl implements AddressBookDao {

    // declare and initialize constants for file marshalling and unmarshalling
    public static final String BOOK_FILE = "book.txt";
    public static final String DELIMITER = "::";

    // declare and initialize addressMap HashMap that will store lastName as keys and Address objects as values; make private as only DAO implementation will have access
    private Map<String, Address> addressMap = new HashMap<>();

    /**
     * Add given Address object to the addressMap HashMap and associates it with
     * the given lastName. If there is already an Address object associated with
     * the given lastName it will overwrite the old Address object and return
     * it. If there is not an Address object associated with the given lastName,
     * it will return null.
     *
     * @param lastName
     * @param Address object
     * @return previous Address object or null
     */
    @Override
    public Address addAddress(String lastName, Address address) throws AddressBookDaoException {
        loadBook();
        Address newAddress = addressMap.put(lastName, address);
        writeBook();
        return newAddress;
    }

    /**
     * Delete Address object from addressMap HashMap associated with given
     * lastName. Returns deleted Address object if there was one. Returns null
     * if there was none.
     *
     * @param lastName
     * @return deleted Address object or null
     */
    @Override
    public Address deleteAddress(String lastName) throws AddressBookDaoException {
        loadBook();
        Address deletedAddress = addressMap.remove(lastName);
        writeBook();
        return deletedAddress;
    }

    /**
     * Find Address object from addressMap HashMap associated with given
     * lastName. Returns Address object if it exists. Returns null if it
     * doesn't.
     *
     * @param lastName
     * @return Address object or null
     */
    @Override
    public Address findAddress(String lastName) throws AddressBookDaoException {
        loadBook();
        Address foundAddress = addressMap.get(lastName);
        return foundAddress;
    }

    /**
     * Lists the count of all Address objects within the addressMap HashMap.
     * Returns int count.
     *
     * @return int count
     */
    @Override
    public int listAddressCount() throws AddressBookDaoException {
        loadBook();
        int addressCount = addressMap.size();
        return addressCount;
    }

    /**
     * Lists all Address Objects within the addressMap HashMap. Returns
     * ArrayList of all Address objects.
     *
     * @return ArrayList of all Address objects
     */
    @Override
    public List<Address> listAllAddresses() throws AddressBookDaoException {
        loadBook();
        List<Address> allAddresses = new ArrayList<>(addressMap.values());
        return allAddresses;
    }

    /**
     * Edit Address object from adddressMap HashMap associated with given
     * lastName. Returns previous Address object associated with lastName if
     * there is one. Returns null if there isn't.
     *
     * @param lastName
     * @param Address address
     *
     * @return previous Address object or null
     */
    @Override
    public Address editAddress(String lastName, Address address) throws AddressBookDaoException {
        loadBook();
        Address editedAddress = addressMap.replace(lastName, address);
        writeBook();
        return editedAddress;
    }

    // convert line of text in file to Address object
    private Address unmarshallAddress(String addressAsText) {
        // split each line on the delimiter
        String[] addressTokens = addressAsText.split(DELIMITER);

        // get HashMap key value from addressToken array
        String lastName = addressTokens[0];

        // instantiate new Address object with lastName passed in the constructor
        Address addressFromFile = new Address(lastName);

        // use setters to input file text into Address object fields
        addressFromFile.setFirstName(addressTokens[1]);
        addressFromFile.setStreetNumber(Integer.parseInt(addressTokens[2]));
        addressFromFile.setStreetName(addressTokens[3]);
        addressFromFile.setCity(addressTokens[4]);
        addressFromFile.setState(addressTokens[5]);
        addressFromFile.setZip(Integer.parseInt(addressTokens[6]));

        return addressFromFile;
    }

    // read book file into memory in HashMap
    private void loadBook() throws AddressBookDaoException {
        // declare new Scanner object to read file
        Scanner sc;

        // try to load file, if not, throw our custom error and not the default 
        // (this hides implementation details and maintains encapsulation)
        try {
            // instantiate new Scanner object
            sc = new Scanner(new BufferedReader(new FileReader(BOOK_FILE)));
        } catch (FileNotFoundException e) {
            throw new AddressBookDaoException("-_- Could not load book data into memory.", e);
        }

        // currentLine holds most recent line read from file
        String currentLine;

        // currentAddress holds most recent Address unmarshalled
        Address currentAddress;

        // go through BOOK_FILE line by line, decoding each line into an Address object
        // add Address objects to HashMap
        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentAddress = unmarshallAddress(currentLine);
            addressMap.put(currentAddress.getLastName(), currentAddress);
        }

        sc.close();

    }
    
    // convert Address object to line of text
    private String marshallAddress(Address anAddress) {
        // get lastName first because that is the key when we unmarshall
        String addressAsText = anAddress.getLastName() + DELIMITER;
        
        // get all other variables and convert to string for file insertion
        addressAsText += anAddress.getFirstName() + DELIMITER;
        addressAsText += anAddress.getStreetNumber() + DELIMITER;
        addressAsText += anAddress.getStreetName() + DELIMITER;
        addressAsText += anAddress.getCity() + DELIMITER;
        addressAsText += anAddress.getState() + DELIMITER;
        addressAsText += anAddress.getZip();
        
        return addressAsText;
        
    }
    
    // write HashMap memory into book file
    private void writeBook() throws AddressBookDaoException {
        // declare PrintWriter
        PrintWriter out;
        
        // try to save to book file, if cannot throw our error
        try{
            out = new PrintWriter(new FileWriter(BOOK_FILE));
        } catch(IOException e) {
            throw new AddressBookDaoException("Could not save address data.", e);
        }
        
        // write out Address objects to book file
        String addressAsText;
        // call listAllAddresses method to return ArrayList of Address objects and
        // store in variable
        List<Address> addressList = listAllAddresses();
        // loop through all Address objects in ArrayList and write each to file
        for(Address currentAddress : addressList) {
            addressAsText = marshallAddress(currentAddress);
            out.println(addressAsText);
            out.flush();
        }
        out.close();
    }

}
