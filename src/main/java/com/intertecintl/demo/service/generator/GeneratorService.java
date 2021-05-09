package com.intertecintl.demo.service.generator;

import com.lowagie.text.DocumentException;

import java.io.IOException;

public interface GeneratorService<T>
{
    T generate() throws IOException, DocumentException;
}
