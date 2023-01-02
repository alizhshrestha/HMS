package com.example.reportservice.listener;

import com.example.reportservice.util.ExcelFileWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    private ExcelFileWriter fileWriter;
//    private ObjectMapper objectMapper = new ObjectMapper();

    public KafkaListeners(ExcelFileWriter fileWriter) {

        this.fileWriter = fileWriter;
    }

    @KafkaListener(
            topics = "account",
            groupId = "groupId"
    )
    void listener(String data) {

    }

}
