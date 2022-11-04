package com.icodify.internalservice;

import com.icodify.internalservice.entity.Product;
import com.icodify.internalservice.entity.Role;
import com.icodify.internalservice.entity.User;
import com.icodify.internalservice.repository.ProductRepository;
import com.icodify.internalservice.repository.RoleRepository;
import com.icodify.internalservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableEurekaClient
public class InternalServiceApplication {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    private PasswordEncoder passwordEncoder;

    public InternalServiceApplication(ProductRepository productRepository,
                                      UserRepository userRepository,
                                      RoleRepository roleRepository,
                                      PasswordEncoder passwordEncoder) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(InternalServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return args -> {
            Product product = Product.builder()
                    .name("Cozzy Pant")
                    .price(1500f)
                    .build();

            Role admin = Role.builder()
                    .name("ROLE_ADMIN")
                    .build();

            Role editor = Role.builder()
                    .name("ROLE_EDITOR")
                    .build();

            Role customer = Role.builder()
                    .name("ROLE_CUSTOMER")
                    .build();

            productRepository.save(product);
            roleRepository.saveAll(Set.of(admin, editor, customer));

            Set<Role> user1Roles = new HashSet<>();
            user1Roles.add(editor);

            Set<Role> user2Roles = new HashSet<>();
            user2Roles.add(editor);
            user2Roles.add(customer);

            Set<Role> user3Roles = new HashSet<>();
            user3Roles.add(admin);

            User user1 = User.builder()
                    .email("alizh@gmail.com")
                    .password(passwordEncoder.encode("root"))
                    .roles(user1Roles)
                    .build();
//            user1.addRole(admin);

            User user2 = User.builder()
                    .email("unisha@gmail.com")
                    .password(passwordEncoder.encode("root"))
                    .roles(user2Roles)
                    .build();

            User user3 = User.builder()
                    .email("ramila@gmail.com")
                    .password(passwordEncoder.encode("root"))
                    .roles(user3Roles)
                    .build();
//            user2.addRole(editor);
//            user2.addRole(customer);
            userRepository.saveAll(List.of(user1, user2, user3));

        };
    }
}
