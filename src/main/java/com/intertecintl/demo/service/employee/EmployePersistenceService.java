package com.intertecintl.demo.service.employee;

import com.intertecintl.demo.model.Employee;
import com.intertecintl.demo.persistence.EmployeeEntity;
import com.intertecintl.demo.persistence.EmployeeRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class EmployePersistenceService implements EmployeeService
{
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAllEmployees()
    {
        return employeeRepository.getAllEmployees()
            .stream()
            .map(EmployeeEntity::toDto)
            .collect(Collectors.toList());
    }

    @Override
    public Employee getEmployeeById(String employeeId)
    {
        EmployeeEntity employeeEntity = employeeRepository.findByKey(employeeId);
        if(employeeEntity != null) return EmployeeEntity.toDto(employeeEntity);
        return null;
    }

    @Override
    public Employee getEmployeeByEmail(String email)
    {
        if(email != null && !email.isBlank())
        {
            Employee employee = EmployeeEntity.toDto(employeeRepository.findByEmail(email));
            return employee;
        }
        return null;
    }
}
