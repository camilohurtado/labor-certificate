package com.intertecintl.demo.persistence;

import com.intertecintl.demo.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigInteger;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeEntity
{
    @Id
    @Column(name = "ID")
    private Long id;
    @Column(name = "EMPLOYEE_ID")
    private String employeeId;
    @Column(name = "EMPLOYEE_NAME")
    private String name;
    @Column(name = "EMPLOYEE_CITY")
    private String city;
    @Column(name = "EMPLOYEE_COUNTRY")
    private String country;
    @Column(name = "EMPLOYEE_POSITION")
    private String position;
    @Column(name = "EMPLOYEE_HIRE_DATE")
    private LocalDate hireDate;
    @Column(name = "EMPLOYEE_SALARY")
    private String salary;
    @Column(name = "EMPLOYEE_EMAIL")
    private String email;
    @Column(name = "EMPLOYEE_PASSWORD")
    private String password;

    public static Employee toDto(EmployeeEntity employeeEntity){
        return Employee.builder()
                .email(employeeEntity.getEmail())
                .salary(employeeEntity.getSalary())
                .id(employeeEntity.getId())
                .ssn(employeeEntity.getEmployeeId())
                .hireDate(employeeEntity.getHireDate())
                .name(employeeEntity.getName())
                .position(employeeEntity.getPosition())
                .password(employeeEntity.getPassword())
                .build();
    }
}
