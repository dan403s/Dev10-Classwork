/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.SpringBootSecurityDemo.dao;

import com.dvb.SpringBootSecurityDemo.entities.UserRole;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Daniel Bart
 */
@Repository
public class UserRoleDaoDB implements UserRoleDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public UserRole getRoleById(int id) {
        try {
            final String SELECT_ROLE_BY_ID = "SELECT * FROM role WHERE id = ?";
            return jdbc.queryForObject(SELECT_ROLE_BY_ID, new RoleMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public UserRole getRoleByRole(String role) {
        try {
            final String SELECT_ROLE_BY_ROLE = "SELECT * FROM role WHERE role = ?";
            return jdbc.queryForObject(SELECT_ROLE_BY_ROLE, new RoleMapper(), role);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<UserRole> getAllRoles() {
        final String SELECT_ALL_ROLES = "SELECT * FROM role";
        return jdbc.query(SELECT_ALL_ROLES, new RoleMapper());
    }

    @Override
    public void deleteRole(int id) {
        final String DELETE_USER_ROLE = "DELETE FROM user_role WHERE role_id = ?";
        final String DELETE_ROLE = "DELETE FROM role WHERE id = ?";
        jdbc.update(DELETE_USER_ROLE, id);
        jdbc.update(DELETE_ROLE, id);
    }

    @Override
    public void updateRole(UserRole role) {
        final String UPDATE_ROLE = "UPDATE role SET role = ? WHERE id = ?";
        jdbc.update(UPDATE_ROLE, role.getRole(), role.getId());
    }

    @Override
    @Transactional
    public UserRole createRole(UserRole role) {
        final String INSERT_ROLE = "INSERT INTO role(role) VALUES(?)";
        jdbc.update(INSERT_ROLE, role.getRole());
        int newId = jdbc.queryForObject("select LAST_INSERT_ID()", Integer.class);
        role.setId(newId);
        return role;
    }

    public static final class RoleMapper implements RowMapper<UserRole> {

        @Override
        public UserRole mapRow(ResultSet rs, int i) throws SQLException {
            UserRole role = new UserRole();
            role.setId(rs.getInt("id"));
            role.setRole(rs.getString("role"));
            return role;
        }
    }
}
