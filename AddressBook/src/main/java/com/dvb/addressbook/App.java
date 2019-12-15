/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.addressbook;

import com.dvb.addressbook.controller.AddressBookController;
import com.dvb.addressbook.dao.AddressBookDao;
import com.dvb.addressbook.dao.AddressBookDaoFileImpl;
import com.dvb.addressbook.ui.AddressBookView;
import com.dvb.addressbook.ui.UserIO;
import com.dvb.addressbook.ui.UserIOConsoleImpl;

/**
 *
 * @author Daniel Bart
 */
public class App {
    
    // main method
    public static void main(String[] args) {
        
        // instantiate and assemble all objects in program
        UserIO myIO = new UserIOConsoleImpl();
        AddressBookView myView = new AddressBookView(myIO);
        AddressBookDao myDao = new AddressBookDaoFileImpl();
        AddressBookController controller = new AddressBookController(myView, myDao);
        
        // run method
        controller.run();
        
    }
    
}
