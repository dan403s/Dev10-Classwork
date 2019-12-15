/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.addressbook.ui;

import com.dvb.addressbook.dto.Address;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public class AddressBookView {

    // instantiate new UserIOConsoleImpl object
    UserIO io;
    
    // constructor for dependency injection
    public AddressBookView(UserIO io) {
        this.io = io;
    }
    

    // MAIN MENU -------------------------------------------------------------------
    // print menu and return user selection as int
    public int printMenuAndGetSelection() throws IOException {

        io.print("Initial Menu: ");
        io.print("1. Add Address");
        io.print("2. Delete Address");
        io.print("3. Find Address");
        io.print("4. List Address Count");
        io.print("5. List All Addresses");
        io.print("6. Edit Address");
        io.print("7. Exit");

        // return user selection in menuSelection as int
        return io.readInt("Please select the operation you wish to perform: ", 1, 7);

    }

    // ADDING NEW ADDRESS ----------------------------------------------------------
    // displays submenu for adding new Address object
    public void displayCreateAddressBanner() {
        io.print("\nAdd Address Menu: ");
    }

    // gets user input for creation of new Address object
    public Address getNewAddressInfo() {
        // prompt and store responses for new Address object instantiation
        String lastName = io.readString("Please enter last name: ");
        String firstName = io.readString("Please enter first name: ");
        int streetNumber = io.readInt("Please enter street number: ");
        String streetName = io.readString("Please enter street name: ");
        String city = io.readString("Please enter city: ");
        String state = io.readString("Please enter state: ");
        int zip = io.readInt("Please enter zip: ");

        // instantiate new Address object and pass lastName into constructor
        Address currentAddress = new Address(lastName);

        // use setters to pass other variables into new Address object
        currentAddress.setFirstName(firstName);
        currentAddress.setStreetNumber(streetNumber);
        currentAddress.setStreetName(streetName);
        currentAddress.setCity(city);
        currentAddress.setState(state);
        currentAddress.setZip(zip);

        return currentAddress;

    }

    // displays success banner for adding new Address object
    public void displayCreateSuccessBanner() {
        io.readInt("Address added. Press 1 to go to Main Menu.\n", 1, 1);
    }

    // DELETED ADDRESS -------------------------------------------------------------
    // displays submenu for deleting Address object
    public void displayDeleteAddressBanner() {
        io.print("\nDelete Address Menu: ");
    }

    // gets user input for lastName selection for deleteAddress Method
    public String getLastNameForDeleteAddress() {
        String lastName = io.readString("Please enter last name of address to delete: ");
        return lastName;
    }

    // get user input for delete verification
    public String getDeleteVerification() {
        String deleteVerification = io.readString("Really Delete (y/n)? ");
        return deleteVerification;
    }

    // display success banner for deleting Address object
    public void displayDeleteSuccessBanner() {
        io.readInt("Address Deleted. Press 1 to go to Main Menu.", 1, 1);
    }

    // display deleting cancel banner
    public void displayCancelDeleteBanner() {
        io.readInt("Address NOT Deleted. Press 1 to go to Main Menu.\n", 1, 1);
    }

    // FINDING ADDRESS -------------------------------------------------------------
    // displays submenu for finding Address object
    public void displayFindAddressBanner() {
        io.print("\nFind Address Menu: ");
    }

    // get user input for lastName selection for findAddress method
    public String getLastNameForFindAddress() {
        String lastName = io.readString("Please enter last name of address to find: ");
        return lastName;
    }

    // display single Address object
    public void displayAddress(Address address) {
        if (address != null) {
            io.print(address.getFirstName() + " " + address.getLastName());
            io.print(address.getStreetNumber() + " " + address.getStreetName());
            io.print(address.getCity() + ", " + address.getState() + " " + address.getZip() + "\n");
        } else {
            io.print("No such address...sorry.");
        }

    }

    // display banner to return to main menu
    public void displayReturnToMainMenuBanner() {
        io.readInt("Press 1 to go to Main Menu.\n", 1, 1);
    }

    // LISTING ADDRESSES COUNT -----------------------------------------------------
    // display submenu for list address count
    public void displayListAddressCountBanner() {
        io.print("\nList Address Count Menu: ");
    }

    // display Address object count
    public void displayAddressCount(int addressCount) {
        io.readInt("There are " + addressCount + " addresses in the book. Press 1 to go to Main Menu.\n", 1, 1);
    }

    // LISTING ALL ADDRESSES -------------------------------------------------------
    // display submenu for list all addresses menu
    public void displayAllAddressesBanner() {
        io.print("\nList Addresses Menu: ");
        
    }

    // display ArrayList of Address objects from DAO and display info for each to screen
    public void displayAddressList(List<Address> addressList) {
        for (Address currentAddress : addressList) {
            displayAddress(currentAddress);
        }
    }

    // EDITING ADDRESS -------------------------------------------------------------
    // display submenu for list all addresses menu
    public void displayEditAddressBanner() {
        io.print("\nEdit Address Menu: ");
    }

    // get user input for lastName selection for editAddress method
    public String getLastNameForEditAddress() {
        String lastName = io.readString("Please enter last name of address to edit: ");
        return lastName;
    }

    // gets user input for editing of Address object
    public Address getEditedAddressInfo(String lastName) {
        // prompt and store responses for new Address object instantiation
        String firstName = io.readString("Please enter first name: ");
        int streetNumber = io.readInt("Please enter street number: ");
        String streetName = io.readString("Please enter street name: ");
        String city = io.readString("Please enter city: ");
        String state = io.readString("Please enter state: ");
        int zip = io.readInt("Please enter zip: ");

        // instantiate new Address object and pass lastName into constructor
        Address editedAddress = new Address(lastName);

        // use setters to pass other variables into new Address object
        editedAddress.setFirstName(firstName);
        editedAddress.setStreetNumber(streetNumber);
        editedAddress.setStreetName(streetName);
        editedAddress.setCity(city);
        editedAddress.setState(state);
        editedAddress.setZip(zip);

        return editedAddress;

    }

    // display banner to return to main menu
    public void displayEditSuccessBanner() {
        io.readInt("Address successfully edited. Press 1 to go to Main Menu.\n", 1, 1);
    }

    // UNKNOWN COMMAND -------------------------------------------------------------
    // display unknown command message
    public void displayUnknownCommandBanner() {
        io.print("\nUnknown Command!!!\n");
    }

    // EXITING PROGRAM -------------------------------------------------------------
    // display exit message
    public void displayExitBanner() {
        io.print("\nThank you for playing.\n");
    }
    
    // DISPLAY ERROR WHEN CATCHING EXCEPTIONS
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
}
