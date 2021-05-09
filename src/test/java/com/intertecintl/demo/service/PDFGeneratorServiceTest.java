package com.intertecintl.demo.service;


import com.intertecintl.demo.service.generator.PDFGeneratorService;
import com.lowagie.text.DocumentException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class PDFGeneratorServiceTest
{

    @Mock
    private SpringTemplateEngine springTemplateEngine;

    //SUT
    private PDFGeneratorService pdfGeneratorService;

    @BeforeEach
    void beforeEach()
    {
        pdfGeneratorService = new PDFGeneratorService(springTemplateEngine);
    }

    @Test
    @DisplayName("Assure pdf file generated is not null")
    public void generatePdfTest_notNullFile() throws IOException, DocumentException
    {
        Mockito.when(springTemplateEngine.process(Mockito.anyString(), Mockito.any())).thenReturn("<h1>Hello world</h1>");
        File pdfGenerated = pdfGeneratorService.generate();
        Assertions.assertNotNull(pdfGenerated);
    }

    @Test
    @DisplayName("Assure pdf file content integrity")
    public void generatePdfTest_verifyContent() throws IOException, DocumentException
    {
        Mockito.when(springTemplateEngine.process(Mockito.anyString(), Mockito.any())).thenReturn("<h1>Hello world</h1>");Mockito.when(springTemplateEngine.process(Mockito.anyString(), Mockito.any())).thenReturn("<h1>Hello world</h1>");

        File pdfGenerated = pdfGeneratorService.generate();
        String expectedContent = "Hello world";

        String[] pdfContent = pdfLines(pdfGenerated);

        Assertions.assertTrue(Arrays.stream(pdfContent).anyMatch(s -> s.equals(expectedContent)));
    }

    private String[] pdfLines(File file)
    {
        try (PDDocument document = PDDocument.load(file)) {

            document.getClass();

            if (!document.isEncrypted()) {

                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);

                PDFTextStripper tStripper = new PDFTextStripper();

                String pdfFileInText = tStripper.getText(document);

                // split by whitespace
                String lines[] = pdfFileInText.split("\\r?\\n");
                return lines;
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
