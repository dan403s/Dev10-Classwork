/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.addressbook.controller;

import com.dvb.addressbook.dao.AddressBookDao;
import com.dvb.addressbook.dao.AddressBookDaoException;
import com.dvb.addressbook.dto.Address;
import com.dvb.addressbook.ui.AddressBookView;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public class AddressBookController {

    // declare AddressBookView object
    AddressBookView view;

    // declare AddressBookDaoFileImpl object
    AddressBookDao dao;

    // constructor for dependency injection
    public AddressBookController(AddressBookView view, AddressBookDao dao) {
        this.view = view;
        this.dao = dao;
    }

    // method that starts everything
    public void run() {
        // variables for menu loop and menu choice
        boolean keepGoing = true;
        int menuSelection;

        // try-catch errors and throw AddressBookDaoException if errors found
        try {
            // loop menu while keepGoing is true
            while (keepGoing) {

                // store user selection in menuSelection as int
                menuSelection = getMenuSelection();

                // switch statement to determine what to do based upon menuSelection variable
                switch (menuSelection) {
                    case 1:
                        createAddress();
                        break;
                    case 2:
                        deleteAddress();
                        break;
                    case 3:
                        findAddress();
                        break;
                    case 4:
                        listAddressCount();
                        break;
                    case 5:
                        listAllAddresses();
                        break;
                    case 6:
                        editAddress();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }
            exitMessage();
        } catch (AddressBookDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }

    }

    // coordinate menu selection
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }

    // coordinate adding new Address object
    public void createAddress() throws AddressBookDaoException {
        view.displayCreateAddressBanner();
        Address newAddress = view.getNewAddressInfo();
        dao.addAddress(newAddress.getLastName(), newAddress);
        view.displayCreateSuccessBanner();
    }

    // coordinate deleting Address object
    public void deleteAddress() throws AddressBookDaoException {
        view.displayDeleteAddressBanner();
        String deleteLastName = view.getLastNameForDeleteAddress();
        Address deleteAddress = dao.findAddress(deleteLastName);
        view.displayAddress(deleteAddress);
        if (deleteAddress != null) {
            String deleteConfirm = view.getDeleteVerification();
            if (deleteConfirm.equalsIgnoreCase("y")) {
                dao.deleteAddress(deleteLastName);
                view.displayDeleteSuccessBanner();
            } else {
                view.displayCancelDeleteBanner();
            }
        } else {
            view.displayReturnToMainMenuBanner();
        }
    }

    // coordinate finding Address object
    public void findAddress() throws AddressBookDaoException {
        view.displayFindAddressBanner();
        String findLastName = view.getLastNameForFindAddress();
        Address foundAddress = dao.findAddress(findLastName);
        view.displayAddress(foundAddress);
        view.displayReturnToMainMenuBanner();
    }

    // coordinate listing Address objects count
    public void listAddressCount() throws AddressBookDaoException {
        view.displayListAddressCountBanner();
        int addressCount = dao.listAddressCount();
        view.displayAddressCount(addressCount);
    }

    // coordinate listing all Address objects
    public void listAllAddresses() throws AddressBookDaoException {
        view.displayAllAddressesBanner();
        List<Address> addressList = dao.listAllAddresses();
        view.displayAddressList(addressList);
        view.displayReturnToMainMenuBanner();
    }

    // coording editing Address object
    public void editAddress() throws AddressBookDaoException {
        view.displayEditAddressBanner();
        String editLastName = view.getLastNameForEditAddress();
        Address editAddress = dao.findAddress(editLastName);
        view.displayAddress(editAddress);
        if (editAddress != null) {
            editAddress = view.getEditedAddressInfo(editLastName);
            dao.editAddress(editLastName, editAddress);
            view.displayEditSuccessBanner();
        } else {
            view.displayReturnToMainMenuBanner();
        }
    }

    // coordinate unknown command option
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    // coordinate exit message
    private void exitMessage() {
        view.displayExitBanner();
    }

}
