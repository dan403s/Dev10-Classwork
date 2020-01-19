/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dvb.jdbctemplatecomplexexample.Dao;

import com.dvb.jdbctemplatecomplexexample.DTO.Employee;
import java.util.List;

/**
 *
 * @author Daniel Bart
 */
public interface EmployeeDao {
    List<Employee> getAllEmployees();
    Employee getEmployeeById(int id);
    Employee addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployeeById(int id);
}
