package com.icodify.multitenant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.icodify.multitenant")
@EntityScan(basePackages = "com.icodify.multitenant")
@EnableJpaRepositories(basePackages = "com.icodify.multitenant")
public class MultitenantApplication {

    public static void main(String[] args) {
        SpringApplication.run(MultitenantApplication.class, args);
    }

}
