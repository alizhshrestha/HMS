package com.icodify.multitenant;

import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.model.entities.Admin;
import com.icodify.multitenant.model.entities.AdminRoles;
import com.icodify.multitenant.model.entities.Role;
import com.icodify.multitenant.repository.AccountRepository;
import com.icodify.multitenant.repository.AdminRepository;
import com.icodify.multitenant.repository.AdminRoleRepository;
import com.icodify.multitenant.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@SpringBootApplication
@ComponentScan(basePackages = "com.icodify.multitenant")
@EntityScan(basePackages = "com.icodify.multitenant")
@EnableJpaRepositories(basePackages = "com.icodify.multitenant")
public class MultitenantApplication {

    private final AccountRepository accountRepository;
    private final AdminRepository adminRepository;
    private final RoleRepository roleRepository;
    private final AdminRoleRepository adminRoleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public MultitenantApplication(AccountRepository accountRepository,
                                  AdminRepository adminRepository,
                                  RoleRepository roleRepository,
                                  AdminRoleRepository adminRoleRepository,
                                  BCryptPasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.adminRepository = adminRepository;
        this.roleRepository = roleRepository;
        this.adminRoleRepository = adminRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(MultitenantApplication.class, args);
//        final NgrokClient ngrokClient = new NgrokClient.Builder().build();
//        final CreateTunnel createTunnel = new CreateTunnel.Builder()
//                .withAddr(8096)
//                .build();
//        final Tunnel tunnel = ngrokClient.connect(createTunnel);

    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
//            List<Account> allaccounts = accountRepository.findAll();
//            System.out.println("Accounts::::::>>>> " + allaccounts);
//            System.out.println("Current Tenant>>>>>>>>>>" + TenantContext.getCurrentTenant());

            List<Admin> admins = adminRepository.findAll();
            System.out.println("Admins::::>>>>>>> " + admins);
            admins.stream().forEach(admin -> {
                List<Role> roles = admin.getAdminRoles().stream().map(adminRoles -> adminRoles.getRole()).collect(Collectors.toList());
                roles.stream().forEach(role -> {
                    System.out.println("Roles::::>>>>>>> " + role.getName());
                });
            });

            //creating default entries in database
            Account account = Account.builder()
                    .id(1)
                    .uuid(UUID.randomUUID())
                    .title("Icodify")
                    .description("This is icodify company")
                    .addressLine1("Baluwatar")
                    .addressLine2("Baluwatar")
                    .city("Ktm")
                    .country("Nepal")
                    .zip("44600")
                    .logo("logo.png")
                    .favicon("fav.ico")
                    .email("icodify@gmail.com")
                    .contact("98")
                    .phone("98")
                    .metaTitle("meta")
                    .metaKeyword("meta")
                    .metaDescription("meta")
                    .build();

            accountRepository.save(account);

            Admin admin = Admin.builder()
                    .id(1)
                    .firstName("Suzan")
                    .middleName("Thapa")
                    .lastName("Magar")
                    .email("suzan@gmail.com")
                    .password(passwordEncoder.encode("root"))
                    .status(true)
                    .isVerified(true)
                    .rememberToken("Yes")
                    .build();

            adminRepository.save(admin);

            List<Role> roles = roleRepository.findAll();
            if(roles.isEmpty()){
                Role role = Role.builder()
                        .name("ROLE_SUPERADMIN")
                        .guardName("SUPERADMIN")
                        .build();

                roleRepository.save(role);
            }

            Role role_superadmin = roleRepository.findByName("ROLE_SUPERADMIN");

            List<AdminRoles> adminRoles = adminRoleRepository.findAll();
            if(adminRoles.isEmpty()){
                AdminRoles adminRole = AdminRoles.builder()
                        .role(role_superadmin)
                        .admin(admin)
                        .build();

                adminRoleRepository.save(adminRole);
            }


        };


    }

}
