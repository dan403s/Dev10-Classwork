/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.addressbook.dao;

import com.dvb.addressbook.dto.Address;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface AddressBookDao {
    
    /**
     * Add given Address object to the addressMap HashMap and associates it with the given lastName.
     * If there is already an Address object associated with the given lastName it will overwrite
     * the old Address object and return it. If there is not an Address object associated with the
     * given lastName, it will return null.
     * 
     * @param lastName
     * @param Address object
     * @return previous Address object or null
     */
    Address addAddress(String lastName, Address address) throws AddressBookDaoException;
    
    /**
     * Delete Address object from addressMap HashMap associated with given lastName. Returns deleted
     * Address object if there was one. Returns null if there was none.
     * 
     * @param lastName
     * @return deleted Address object or null
     */
    Address deleteAddress(String lastName) throws AddressBookDaoException;
    
    /**
     * Find Address object from addressMap HashMap associated with given lastName. Returns Address object if it exists.
     * Returns null if it doesn't.
     * 
     * @param lastName
     * @return Address object or null
     */
    Address findAddress(String lastName) throws AddressBookDaoException;
    
    /**
     * Lists the count of all Address objects within the addressMap HashMap. Returns int count.
     * 
     * @return int count
     */
    int listAddressCount() throws AddressBookDaoException;
    
    /**
     * Lists all Address Objects within the addressMap HashMap. Returns ArrayList of all Address objects.
     * 
     * @return ArrayList of all Address objects
     */
    List<Address> listAllAddresses() throws AddressBookDaoException;
    
    /**
     * Edit Address object from adddressMap HashMap associated with given lastName. Returns previous Address object
     * associated with lastName if there is one. Returns null if there isn't.
     * 
     * @param lastName
     * @param Address address
     * 
     * @return previous Address object or null
     */
    Address editAddress(String lastName, Address address) throws AddressBookDaoException;
}
