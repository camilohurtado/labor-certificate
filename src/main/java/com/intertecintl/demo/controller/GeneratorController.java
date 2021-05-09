package com.intertecintl.demo.controller;

import com.intertecintl.demo.service.generator.GeneratorService;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/generate/pdf")
    public void generatePdf(HttpServletResponse response){
        try {
            Path file = Paths.get(generatorService.generate().getAbsolutePath());
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
    }
}
