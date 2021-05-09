package com.intertecintl.demo.persistence;


import org.springframework.data.repository.Repository;

import java.util.List;

public interface EmployeeRepository extends Repository<EmployeeEntity, Long>
{
    public List<EmployeeEntity> getAllEmployees();
    public EmployeeEntity findByKey(String id);
}
