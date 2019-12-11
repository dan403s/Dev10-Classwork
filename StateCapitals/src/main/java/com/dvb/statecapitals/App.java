/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.statecapitals;

/**
 *
 * @author Daniel Bart
 */
public class App {
    
    public static void main(String[] args) {
        // instantiate new StateCapitals object
        StateCapitals stateCapitalsInstance = new StateCapitals();
        
        // call methods
        stateCapitalsInstance.loadHashMap();
        stateCapitalsInstance.loadSet();
        stateCapitalsInstance.loadCollection();
        stateCapitalsInstance.printStateNames();
        stateCapitalsInstance.printCapitalNames();
        stateCapitalsInstance.printStateAndCapitalNames();
    }
    
}
