package com.example.reportservice.controller;

import com.example.reportservice.model.Account;
import com.example.reportservice.util.ExcelFileWriter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ExcelController {

    Logger log = LoggerFactory.getLogger(ExcelController.class);

    @GetMapping("/excel")
    public void getExcel(HttpServletResponse response, @RequestParam(value = "accountDto") String accountDto) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Account> accounts = objectMapper.readValue(accountDto, new TypeReference<List<Account>>() {
        });
        log.info("Getting accounts details from multitenant services: {}", accounts);

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment;filename=accounts.xls";

        response.setHeader(headerKey, headerValue);
        ExcelFileWriter.writeToExcel(response, "accounts", accounts);

        response.flushBuffer();
    }

}
