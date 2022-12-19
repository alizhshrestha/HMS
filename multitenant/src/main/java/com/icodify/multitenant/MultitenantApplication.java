package com.icodify.multitenant;

import com.icodify.multitenant.config.multitenancy.context.TenantContext;
import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.model.entities.Admin;
import com.icodify.multitenant.model.entities.Role;
import com.icodify.multitenant.repository.AccountRepository;
import com.icodify.multitenant.repository.AdminRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@SpringBootApplication
@ComponentScan(basePackages = "com.icodify.multitenant")
@EntityScan(basePackages = "com.icodify.multitenant")
@EnableJpaRepositories(basePackages = "com.icodify.multitenant")
public class MultitenantApplication {

    private AccountRepository accountRepository;
    private AdminRepository adminRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public MultitenantApplication(AccountRepository accountRepository, AdminRepository adminRepository) {
        this.accountRepository = accountRepository;
        this.adminRepository = adminRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(MultitenantApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
//            List<Account> allaccounts = accountRepository.findAll();
//            System.out.println("Accounts::::::>>>> " + allaccounts);
//            System.out.println("Current Tenant>>>>>>>>>>" + TenantContext.getCurrentTenant());

            List<Admin> admins = adminRepository.findAll();
            System.out.println("Admins::::>>>>>>> " + admins);
            admins.stream().forEach(admin -> {
                List<Role> roles = admin.getAdminRoles().stream().map(adminRoles -> adminRoles.getRole()).collect(Collectors.toList());
                roles.stream().forEach(role->{
                    System.out.println("Roles::::>>>>>>> " + role.getName());
                });
            });
        };
    }

}
