/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.addressbook.dao;

import com.dvb.addressbook.dto.Address;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Daniel Bart
 */
public class AddressBookDaoTest {

    private AddressBookDao dao = new AddressBookDaoFileImpl();

    public AddressBookDaoTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws Exception {
        List<Address> addressList = dao.listAllAddresses();
        for (Address address : addressList) {
            dao.deleteAddress(address.getLastName());
        }
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addAddress method, of class AddressBookDao.
     */
    @Test
    public void testAddFindAddress() throws Exception {
        // instantiate new test object
        Address address = new Address("Bart");
        address.setFirstName("Daniel");
        address.setStreetNumber(403);
        address.setStreetName("Sapodilla Ave, Apt 512");
        address.setCity("West Palm Beach");
        address.setState("FL");
        address.setZip(33401);

        // add test object in DAO
        dao.addAddress(address.getLastName(), address);

        // retrieve test object from DAO
        Address fromDao = dao.findAddress(address.getLastName());

        // assert that object added equals object found
        assertEquals(address, fromDao);

    }

    /**
     * Test of deleteAddress method, of class AddressBookDao.
     */
    @Test
    public void testDeleteAddress() throws Exception {
        // instantiate new test object 1
        Address address1 = new Address("Bart");
        address1.setFirstName("Daniel");
        address1.setStreetNumber(403);
        address1.setStreetName("Sapodilla Ave, Apt 512");
        address1.setCity("West Palm Beach");
        address1.setState("FL");
        address1.setZip(33401);

        // add test object 1 in DAO
        dao.addAddress(address1.getLastName(), address1);

        // instantiate new test object 2
        Address address2 = new Address("Rod");
        address2.setFirstName("Rip");
        address2.setStreetNumber(1000);
        address2.setStreetName("Fast Lane");
        address2.setCity("City");
        address2.setState("State");
        address2.setZip(00000);

        // add test object 2 in DAO
        dao.addAddress(address2.getLastName(), address2);

        // remove test object 1
        dao.deleteAddress(address1.getLastName());
        // assertEquals 1 findAllAddresses.size
        assertEquals(1, dao.listAddressCount());
        // assertNull findAddress object 1
        assertNull(dao.findAddress(address1.getLastName()));

        // remove test object 2
        dao.deleteAddress(address2.getLastName());
        // assertEquals 2 findAllAddresses.size
        assertEquals(0, dao.listAddressCount());
        // assertNull findAddress object 2
        assertNull(dao.findAddress(address2.getLastName()));
    }

    /**
     * Test of findAddress method, of class AddressBookDao.
     */
    @Test
    public void testFindAddress() throws Exception {
        // already tested
    }

    /**
     * Test of listAddressCount method, of class AddressBookDao.
     */
    @Test
    public void testListAddressCount() throws Exception {
        // instantiate new test object 1
        Address address1 = new Address("Bart");
        address1.setFirstName("Daniel");
        address1.setStreetNumber(403);
        address1.setStreetName("Sapodilla Ave, Apt 512");
        address1.setCity("West Palm Beach");
        address1.setState("FL");
        address1.setZip(33401);

        // add test object 1 in DAO
        dao.addAddress(address1.getLastName(), address1);

        // instantiate new test object 2
        Address address2 = new Address("Rod");
        address2.setFirstName("Rip");
        address2.setStreetNumber(1000);
        address2.setStreetName("Fast Lane");
        address2.setCity("City");
        address2.setState("State");
        address2.setZip(00000);

        // add test object 2 in DAO
        dao.addAddress(address2.getLastName(), address2);

        // assertEquals that count is 2
        assertEquals(2, dao.listAddressCount());
    }

    /**
     * Test of listAllAddresses method, of class AddressBookDao.
     */
    @Test
    public void testListAllAddresses() throws Exception {
        // instantiate new test object 1
        Address address1 = new Address("Bart");
        address1.setFirstName("Daniel");
        address1.setStreetNumber(403);
        address1.setStreetName("Sapodilla Ave, Apt 512");
        address1.setCity("West Palm Beach");
        address1.setState("FL");
        address1.setZip(33401);

        // add test object 1 in DAO
        dao.addAddress(address1.getLastName(), address1);

        // instantiate new test object 2
        Address address2 = new Address("Rod");
        address2.setFirstName("Rip");
        address2.setStreetNumber(1000);
        address2.setStreetName("Fast Lane");
        address2.setCity("City");
        address2.setState("State");
        address2.setZip(00000);

        // add test object 2 in DAO
        dao.addAddress(address2.getLastName(), address2);
        
        // assertEquals that count is 2
        assertEquals(2, dao.listAllAddresses().size());
        // test that object 1 added equals object 1 found
        assertEquals(address1, dao.listAllAddresses().get(1));
        // test that object 2 added equals object 2 found
        assertEquals(address2, dao.listAllAddresses().get(0));
    }
    
    /**
     * Test of listAllAddresses method, of class AddressBookDao.
     */
    @Test
    public void testEditAddress() throws Exception {
        
        // instantiate new test object
        Address address = new Address("Bart");
        address.setFirstName("Daniel");
        address.setStreetNumber(403);
        address.setStreetName("Sapodilla Ave, Apt 512");
        address.setCity("West Palm Beach");
        address.setState("FL");
        address.setZip(33401);

        // add test object in DAO
        dao.addAddress(address.getLastName(), address);

        // edit test object with new fields but same title field
        Address editedAddress = new Address("Bart");
        editedAddress.setFirstName("Ralph");
        editedAddress.setStreetNumber(999);
        editedAddress.setStreetName("Test");
        editedAddress.setCity("Test");
        editedAddress.setState("Test");
        editedAddress.setZip(00000);
                
        // get edited address from DAO
        Address oldAddress = dao.editAddress(editedAddress.getLastName(), editedAddress);
        
        // assert that object added equals object found
        assertNotEquals(address, dao.findAddress(address.getLastName()));
        
        assertEquals(address, oldAddress);
        
        
    }

}
