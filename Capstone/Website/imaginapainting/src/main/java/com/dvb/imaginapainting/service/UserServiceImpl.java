/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.imaginapainting.service;

import com.dvb.imaginapainting.config.SecurityUtils;
import com.dvb.imaginapainting.data.UserRepository;
import com.dvb.imaginapainting.entities.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Daniel Bart
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public boolean existsById(int userId) {
        return userRepository.existsById(userId);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void validateUsername(String username) throws UserAlreadyExistsException {
        List<User> userList = userRepository.findAll();
        String currentUsername = SecurityUtils.getUserName();
        User currentUser = userRepository.findByUsername(currentUsername);
        userList.remove(currentUser);

        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                throw new UserAlreadyExistsException("Username is already taken.");
            }
        }
    }
    
    @Override
    public void validateUsernameForRegistration(String username) throws UserAlreadyExistsException {
        List<User> userList = userRepository.findAll();

        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                throw new UserAlreadyExistsException("Username is already taken.");
            }
        }
    }

    @Override
    public void validatePassword(String currentPassword, String newPassword, String newPassword2) throws InvalidPasswordException {
        String currentUsername = SecurityUtils.getUserName();
        User currentUser = userRepository.findByUsername(currentUsername);

        if (!newPassword.equals(newPassword2)) {
            throw new InvalidPasswordException("New passwords do NOT match.");
        }

        if (!passwordEncoder.matches(currentPassword, currentUser.getPassword())) {
            throw new InvalidPasswordException("Current password does NOT match the database.");
        }
    }

}
