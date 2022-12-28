package com.example.multitenantbaseservice;

import com.example.multitenantbaseservice.entity.Account;
import com.example.multitenantbaseservice.entity.Admin;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;

import java.util.UUID;

@SpringBootApplication
public class MultitenantBaseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultitenantBaseServiceApplication.class, args);
    }

    @Bean
    public FlywayMigrationStrategy flywayMigrationStrategy() {
        return flyway -> {
            // do nothing
        };
    }

}
