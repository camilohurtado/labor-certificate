package com.intertecintl.demo.error.exception;

public class EmployeeNotFoundException extends RuntimeException
{
    public EmployeeNotFoundException(String email)
    {
        super(String.format("An employee was not found. Try again"));
    }
}
