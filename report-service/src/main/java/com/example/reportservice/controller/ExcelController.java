package com.example.reportservice.controller;

import com.example.reportservice.model.Account;
import com.example.reportservice.util.ExcelFileWriter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/report")
public class ExcelController {

    @GetMapping("/excel")
    public void getExcel(HttpServletResponse response, @RequestParam(value="accountDto") String accountDto) throws Exception {
//        Account account = Account.builder()
//                .id(1)
//                .uuid(UUID.randomUUID())
//                .title("Icodify")
//                .description("This is icodify company")
//                .addressLine1("Baluwatar")
//                .addressLine2("Baluwatar")
//                .city("Ktm")
//                .country("Nepal")
//                .zip("44600")
//                .logo("logo.png")
//                .favicon("fav.ico")
//                .email("icodify@gmail.com")
//                .contact("98")
//                .phone("98")
//                .metaTitle("meta")
//                .metaKeyword("meta")
//                .metaDescription("meta")
//                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        List<Account> accounts = objectMapper.readValue(accountDto, new TypeReference<List<Account>>(){});
        System.out.println(accounts);

//        List<Account> accounts = List.of(account);

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=accounts.xls";

        response.setHeader(headerKey, headerValue);
        ExcelFileWriter.writeToExcel(response, "accounts", accounts);

        response.flushBuffer();
    }

}
