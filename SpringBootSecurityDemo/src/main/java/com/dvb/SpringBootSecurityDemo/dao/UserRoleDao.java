/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.SpringBootSecurityDemo.dao;

import com.dvb.SpringBootSecurityDemo.entities.UserRole;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface UserRoleDao {

    UserRole getRoleById(int id);

    UserRole getRoleByRole(String role);

    List<UserRole> getAllRoles();

    void deleteRole(int id);

    void updateRole(UserRole role);

    UserRole createRole(UserRole role);
}
