package com.intertecintl.demo.service.generator;

import com.intertecintl.demo.model.Employee;
import com.lowagie.text.DocumentException;

import java.io.IOException;

public interface GeneratorService<T>
{
    T generate(Employee employee) throws IOException, DocumentException;
}
