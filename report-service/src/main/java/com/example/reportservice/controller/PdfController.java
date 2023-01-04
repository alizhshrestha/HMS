package com.example.reportservice.controller;

import com.example.reportservice.util.PdfGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/report")
public class PdfController {

    @GetMapping("/pdf")
    public void generatePdfFile(HttpServletResponse response, @RequestParam(value = "accountDto") String accountDto) throws DocumentException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        List<Map<String, Object>> data = objectMapper.readValue(accountDto, new TypeReference<List<Map<String, Object>>>() {
        });

        PdfGenerator generator = new PdfGenerator();
        generator.generate(data, response);

    }

}
