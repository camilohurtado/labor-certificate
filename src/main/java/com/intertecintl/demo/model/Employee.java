package com.intertecintl.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee
{
    String email;
    String password;

    String name;
    Long id;
    String ssn;
    String position;
    String salary;
    LocalDate hireDate;

}
