/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.vendingmachine.service;

import java.math.BigDecimal;

/**
 *
 * @author Daniel Bart
 */
public class Change {

    // declare and initialize variables if necessary
    private BigDecimal changeDue;
    private int quartersDue = 0;
    private int dimesDue = 0;
    private int nickelsDue = 0;
    private int penniesDue = 0;

    // constructor takes changeDue
    public Change(BigDecimal changeDue) {
        this.changeDue = changeDue;
    }

    // getters and setters
    public BigDecimal getChangeDue() {
        return changeDue;
    }

    public void setChangeDue(BigDecimal changeDue) {
        this.changeDue = changeDue;
    }

    public int getQuartersDue() {
        return quartersDue;
    }

    public int getDimesDue() {
        return dimesDue;
    }

    public int getNickelsDue() {
        return nickelsDue;
    }

    public int getPenniesDue() {
        return penniesDue;
    }

//    // generate change due to user;
    public void createChange() throws UnsupportedCoinException {

        // whill changeDue > 0, add to coins counts
        while (changeDue.compareTo(new BigDecimal(0)) > 0) {

            if (changeDue.compareTo(getCoinValue("Quarter")) >= 0) {
                quartersDue++;
                changeDue = changeDue.subtract(getCoinValue("Quarter"));
            } else if (changeDue.compareTo(getCoinValue("Dime")) >= 0) {
                dimesDue++;
                changeDue = changeDue.subtract(getCoinValue("Dime"));
            } else if (changeDue.compareTo(getCoinValue("Nickel")) >= 0) {
                nickelsDue++;
                changeDue = changeDue.subtract(getCoinValue("Nickel"));
            } else {
                penniesDue++;
                changeDue = changeDue.subtract(getCoinValue("Penny"));
            }

        }

    }
    
    // generate change due to user;
//    public void createChange() throws UnsupportedCoinException {
//
//        while (changeDue.compareTo(new BigDecimal(0)) > 0) {
//
//            if (changeDue.compareTo(quarterValue) >= 0) {
//                quartersDue++;
//                changeDue.subtract(quarterValue);
//            } else if (changeDue.compareTo(dimeValue) >= 0) {
//                dimesDue++;
//                changeDue.subtract(dimeValue);
//            } else if (changeDue.compareTo(nickelValue) >= 0) {
//                nickelsDue++;
//                changeDue.subtract(nickelValue);
//            } else {
//                penniesDue++;
//                changeDue.subtract(pennyValue);
//            }
//
//        }
//
//    }

    // determine how much each coin is worth
    public BigDecimal getCoinValue(String coinName) throws UnsupportedCoinException {

        try {
            // match enum value with paramter passed in
            CoinValues coin = CoinValues.valueOf(coinName.toUpperCase());

            switch (coin) {
                case QUARTER:
                    return new BigDecimal("0.25");
                case DIME:
                    return new BigDecimal("0.10");
                case NICKEL:
                    return new BigDecimal("0.05");
                case PENNY:
                    return new BigDecimal("0.01");
                default:
                    throw new UnsupportedCoinException("This will never run...");
            }
        } catch (IllegalArgumentException e) {
            throw new UnsupportedCoinException("Could not find coin in enum...", e);
        }

    }
    
//    public void setCoinValues() throws UnsupportedCoinException {
//        for(CoinValues coin : CoinValues.values()) {
//            switch (coin) {
//                case QUARTER:
//                    quarterValue = new BigDecimal("0.25");
//                    break;
//                case DIME:
//                    dimeValue = new BigDecimal("0.10");
//                    break;
//                case NICKEL:
//                    nickelValue = new BigDecimal("0.05");
//                    break;
//                case PENNY:
//                    pennyValue = new BigDecimal("0.01");
//                    break;
//                default:
//                    throw new UnsupportedCoinException("This will never run because the parameter passed into the method is not typed by the user");
//            }
//        }
//    }

}
