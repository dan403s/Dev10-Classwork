/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.flooringmastery.dao;

/**
 *
 * @author Daniel Bart
 */
public interface FlooringMasteryAuditDao {

    // write next entry in audit log
    public void writeAuditEntry(String entry) throws FlooringMasteryPersistenceException;

}
