package com.example.reportservice;

import com.example.reportservice.listener.KafkaListeners;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReportServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReportServiceApplication.class, args);
    }

    CommandLineRunner commandLineRunner(){
        return args -> {

        };
    }

}
