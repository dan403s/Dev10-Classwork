/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.vendingmachine;

import com.dvb.vendingmachine.controller.VendingMachineController;
import com.dvb.vendingmachine.dao.VendingMachineAuditDao;
import com.dvb.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.dvb.vendingmachine.dao.VendingMachineDao;
import com.dvb.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.dvb.vendingmachine.service.VendingMachineServiceLayer;
import com.dvb.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.dvb.vendingmachine.ui.UserIO;
import com.dvb.vendingmachine.ui.UserIOConsoleImpl;
import com.dvb.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Daniel Bart
 */
public class App {
    
    // main method
    public static void main(String[] args) {
        
        // instantiate new objects to inject dependency
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myView, myService);
        
        // call methods
        controller.run();
    }
    
}
