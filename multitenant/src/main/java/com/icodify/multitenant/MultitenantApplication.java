package com.icodify.multitenant;

import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.model.entities.Admin;
import com.icodify.multitenant.repository.AccountRepository;
import com.icodify.multitenant.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@ComponentScan(basePackages = "com.icodify.multitenant")
@EntityScan(basePackages = "com.icodify.multitenant")
@EnableJpaRepositories(basePackages = "com.icodify.multitenant")
public class MultitenantApplication {

    private AccountRepository accountRepository;

    public MultitenantApplication(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MultitenantApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            List<Account> allaccounts = accountRepository.findAll();
            System.out.println("Accounts::::::>>>> " + allaccounts);
        };
    }

}
