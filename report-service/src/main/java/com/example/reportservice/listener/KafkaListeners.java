//package com.example.reportservice.listener;
//
//import com.example.reportservice.util.ExcelFileWriter;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class KafkaListeners {
//
//    private final ExcelFileWriter fileWriter;
////    private ObjectMapper objectMapper = new ObjectMapper();
//
//    public KafkaListeners(ExcelFileWriter fileWriter) {
//
//        this.fileWriter = fileWriter;
//    }
//
//    @KafkaListener(
//            topics = "account",
//            groupId = "groupId"
//    )
//    void listener(String data) {
//
//    }
//
//}
