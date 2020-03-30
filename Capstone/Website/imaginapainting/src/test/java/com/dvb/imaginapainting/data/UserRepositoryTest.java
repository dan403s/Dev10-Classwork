/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.data;

import com.dvb.imaginapainting.entities.State;
import com.dvb.imaginapainting.entities.User;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 *
 * @author Daniel Bart
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    public UserRepositoryTest() {
    }

    @Test
    public void testFindById() {
        User user = userRepository.findById(1)
                .orElse(null);

        assertNotNull(user);
    }

    @Test
    public void testFindAll() {
        assertTrue(userRepository.findAll().size() > 0);
    }

    @Test
    public void testSave() {
        State state = new State();
        state.setStateId("AK");

        User user = new User();
        user.setAcctStatus(1);
        user.setAptUnit("Unit 100");
        user.setCity("Charlotte");
        user.setFirstName("Test First Name");
        user.setLastName("Test Last Name");
        user.setPassword("TEST");
        user.setState(state);
        user.setStreetAddress("123 Test Lane");
        user.setUserRole("ROLE_SELLER");
        user.setUsername("Test@test.com");
        user.setZip("28255");

        user = userRepository.save(user);

        assertTrue(user.getUserId() > 0);

        user = userRepository.findById(user.getUserId()).orElse(null);

        assertNotNull(user);

        userRepository.deleteById(user.getUserId());

        user = userRepository.findById(user.getUserId()).orElse(null);

        assertNull(user);
    }

    @Test
    public void testDeleteById() {
        // already tested in testSave()
    }

    @Test
    public void testCount() {
        long count = userRepository.count();

        assertTrue(count > 0);
    }

    @Test
    public void testExistsById() {
        assertTrue(userRepository.existsById(1));
    }

    @Test
    public void testFindByUsername() {
        assertNotNull(userRepository.findByUsername("danielvbart@yahoo.com"));
    }

}
