package com.intertecintl.demo.controller;

import com.intertecintl.demo.error.exception.EmployeeNotFoundException;
import com.intertecintl.demo.model.Employee;
import com.intertecintl.demo.model.User;
import com.intertecintl.demo.service.employee.EmployeeService;
import com.intertecintl.demo.service.generator.GeneratorService;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequiredArgsConstructor
public class GeneratorController
{
    private final GeneratorService<File> generatorService;
    private final EmployeeService employeeService;

    @GetMapping("/generate/pdf")
    public void generatePdf(HttpServletResponse response, @RequestBody User user){
        if(user != null)
        {
            Employee employee = employeeService.getEmployeeByEmail(user.getUsername());

            if(employee != null)
            {
                try {
                    Path file = Paths.get(generatorService.generate(employee).getAbsolutePath());
                    if (Files.exists(file)) {
                        response.setContentType("application/pdf");
                        response.addHeader("Content-Disposition",
                                "attachment; filename=" + file.getFileName());
                        Files.copy(file, response.getOutputStream());
                        response.getOutputStream().flush();
                    }
                } catch (DocumentException | IOException ex) {
                    ex.printStackTrace();
                }
            }else
            {
                throw new EmployeeNotFoundException(user.getUsername());
            }
        }
    }
}
