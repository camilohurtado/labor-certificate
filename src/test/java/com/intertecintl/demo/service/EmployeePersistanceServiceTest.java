package com.intertecintl.demo.service;

import com.intertecintl.demo.model.Employee;
import com.intertecintl.demo.persistence.EmployeeEntity;
import com.intertecintl.demo.persistence.EmployeeRepository;
import com.intertecintl.demo.service.employee.EmployePersistenceService;
import com.intertecintl.demo.service.employee.EmployeeService;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class EmployeePersistanceServiceTest
{
    @Mock
    private EmployeeRepository employeeRepository;

    //SUT
    EmployeeService employeeService;

    @BeforeEach
    public void beforeAll()
    {
        employeeService = new EmployePersistenceService(employeeRepository);
    }

    @Test
    @DisplayName("Assure data is retrieved when records exists")
    public void testGetAllEmployees_notNull()
    {
        List<EmployeeEntity> allEmployeesList = getAllEmployees();
        Mockito.when(employeeRepository.getAllEmployees()).thenReturn(allEmployeesList);

        List<Employee> result = employeeService.getAllEmployees();

        Assertions.assertNotNull(result);
    }

    @Test
    @DisplayName("Assure data is mapped to dto object")
    public void testGetAllEmployees_verifyDataTypeTransformation()
    {
        List<EmployeeEntity> allEmployeesList = getAllEmployees();
        Mockito.when(employeeRepository.getAllEmployees()).thenReturn(allEmployeesList);

        List<Employee> result = employeeService.getAllEmployees();

        MatcherAssert.assertThat(result, Matchers.everyItem(Matchers.isA(Employee.class)));
    }

    private List<EmployeeEntity> getAllEmployees()
    {
        EmployeeEntity employeeEntity1 = EmployeeEntity
                .builder()
                .employeeId("1144074925")
                .city("Cali")
                .country("Colombia")
                .email("user@intertec.com")
                .hireDate(LocalDate.of(2015, 8, 01))
                .name("Cristiano Ronaldo")
                .id(1L)
                .password("password")
                .position("Software Developer")
                .salary("USD4.780")
                .build();

        EmployeeEntity employeeEntity2 = EmployeeEntity
                .builder()
                .employeeId("1144074926")
                .city("Medellin")
                .country("Colombia")
                .email("user2@intertec.com")
                .hireDate(LocalDate.of(2010, 1, 22))
                .name("David Beckham")
                .id(2L)
                .password("passwordBeck")
                .position("Software Developer")
                .salary("USD1.200")
                .build();

        EmployeeEntity employeeEntity3 = EmployeeEntity
                .builder()
                .employeeId("1144074927")
                .city("San Jose")
                .country("Costa Rica")
                .email("user@intertec.com")
                .hireDate(LocalDate.of(2020, 12, 30))
                .name("Lionel Messi")
                .id(3L)
                .password("passwordLio")
                .position("HR Manager")
                .salary("USD6.000")
                .build();

        return List.of(employeeEntity1, employeeEntity2, employeeEntity3);
    }

}
