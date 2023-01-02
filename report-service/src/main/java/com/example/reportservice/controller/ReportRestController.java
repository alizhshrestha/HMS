package com.example.reportservice.controller;

import com.example.reportservice.util.ExcelFileWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportRestController {

    private ExcelFileWriter fileWriter;

    public ReportRestController(ExcelFileWriter fileWriter) {
        this.fileWriter = fileWriter;
    }

    public void generateExcel(HttpServletResponse response) throws Exception{

    }
}
