package com.intertecintl.demo.service.employee;

import com.intertecintl.demo.model.Employee;

import java.util.List;

public interface EmployeeService
{
    public List<Employee> getAllEmployees();
    public Employee getEmployeeById(String employeeId);
    public Employee getEmployeeByEmail(String email);
}
