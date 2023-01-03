package com.example.reportservice.controller;

import com.example.reportservice.model.Account;
import com.example.reportservice.util.PdfGenerator;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/report")
public class PdfController {

    @GetMapping("/pdf")
    public void generatePdfFile(HttpServletResponse response) throws DocumentException, IOException{
        response.setContentType("application/pdf");
        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";
        response.setHeader(headerkey, headervalue);

        Account account = Account.builder()
                .id(1)
                .uuid(UUID.randomUUID())
                .title("Icodify")
                .description("This is icodify company")
                .addressLine1("Baluwatar")
                .addressLine2("Baluwatar")
                .city("Ktm")
                .country("Nepal")
                .zip("44600")
                .logo("logo.png")
                .favicon("fav.ico")
                .email("icodify@gmail.com")
                .contact("98")
                .phone("98")
                .metaTitle("meta")
                .metaKeyword("meta")
                .metaDescription("meta")
                .build();

        List<Account> accounts = List.of(account);
        PdfGenerator generator = new PdfGenerator();
        generator.generate(accounts, response);

    }

}
