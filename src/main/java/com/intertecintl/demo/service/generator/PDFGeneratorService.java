package com.intertecintl.demo.service.generator;

import com.intertecintl.demo.model.Employee;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
@RequiredArgsConstructor
public class PDFGeneratorService implements GeneratorService<File>
{
    private static final String TEMPLATE_PATH = "/templates/";
    private final SpringTemplateEngine springTemplateEngine;

    @Override
    public File generate(Employee employee) throws IOException, DocumentException
    {
        Context thymeleafContext = getContext(employee);
        String html = springTemplateEngine.process("template", thymeleafContext);
        return render(html);
    }

    private File render(String html) throws IOException, DocumentException
    {
        File file = File.createTempFile("carta-laboral", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html, new ClassPathResource(TEMPLATE_PATH).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContext(Employee employee)
    {
        Context context = new Context();
        context.setVariable("employee", employee);
        return context;
    }
}
