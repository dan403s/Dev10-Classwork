/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.entities.User;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface UserService {

    // CRUD for Admin objects
    /**
     * Find User by userId
     * @param userId
     * @return 
     */
    User findById(int userId);

    /**
     * Find all User objects
     * @return 
     */
    List<User> findAll();

    /**
     * Save User
     * @param user
     * @return 
     */
    User save(User user);

    /**
     * Delete User by userId
     * @param userId
     */
    void deleteById(int userId);

    /**
     * Count User objects
     * @return 
     */
    long count();

    /**
     * Check if User exists by userId
     * @param userId
     * @return 
     */
    boolean existsById(int userId);

    /**
     * Find User by username
     * @param username
     * @return 
     */
    User findByUsername(String username);

    /**
     * Validate that username is not currently in existence for changing username
     * @param username
     * @throws com.dvb.imaginapainting.service.UserAlreadyExistsException
     */
    void validateUsername(String username) throws UserAlreadyExistsException;

    /**
     * Validate that username is not currently in existence for registering username
     * @param username
     * @throws UserAlreadyExistsException
     */
    void validateUsernameForRegistration(String username) throws UserAlreadyExistsException;

    /**
     * Validate password
     * @param currentPassword
     * @param newPassword
     * @param newPassword2
     * @throws InvalidPasswordException
     */
    void validatePassword(String currentPassword, String newPassword, String newPassword2) throws InvalidPasswordException;

}
