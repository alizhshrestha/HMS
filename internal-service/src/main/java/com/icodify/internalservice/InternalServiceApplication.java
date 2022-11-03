package com.icodify.internalservice;

import com.icodify.internalservice.entity.Product;
import com.icodify.internalservice.entity.User;
import com.icodify.internalservice.repository.ProductRepository;
import com.icodify.internalservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class InternalServiceApplication {

    private ProductRepository productRepository;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public InternalServiceApplication(ProductRepository productRepository,
                                      UserRepository userRepository,
                                      PasswordEncoder passwordEncoder) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
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

            User user = User.builder()
                    .email("alizh@gmail.com")
                    .password(passwordEncoder.encode("root"))
                    .build();

            productRepository.save(product);
            userRepository.save(user);
        };
    }
}
