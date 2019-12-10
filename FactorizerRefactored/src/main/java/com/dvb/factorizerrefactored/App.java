/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.factorizerrefactored;

/**
 *
 * @author Daniel Bart
 */
public class App {
    
    // right click run on App.java, as I accidentally set run to FactorizerRefactored.java and not App.java and the main method is in App.java
    public static void main(String[] args) {
        // instantiate new FactorizerRefactored object from the class
        FactorizerRefactored factorizerRefactoredInstance = new FactorizerRefactored();
        
        // run factorANumber method
        factorizerRefactoredInstance.factorANumber();
    }
    
}
