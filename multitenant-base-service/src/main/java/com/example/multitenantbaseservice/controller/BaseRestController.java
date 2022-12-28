package com.example.multitenantbaseservice.controller;

import com.example.multitenantbaseservice.entity.Account;
import com.example.multitenantbaseservice.entity.dto.AccountRequestDto;
import com.example.multitenantbaseservice.repository.AccountRepository;
import org.flywaydb.core.Flyway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/base")
public class BaseRestController {

    private final Flyway flyway;
    private final AccountRepository accountRepository;

    public BaseRestController(Flyway flyway, AccountRepository accountRepository) {
        this.flyway = flyway;
        this.accountRepository = accountRepository;
    }

    @PostMapping("/addsource")
    public ResponseEntity<Object> addTenant(@RequestBody AccountRequestDto accountRequestDto) {
        Account account = Account.builder()
                .uuid(UUID.randomUUID())
                .title(accountRequestDto.getTitle())
                .description(accountRequestDto.getDescription())
                .addressLine1(accountRequestDto.getAddressLine1())
                .addressLine2(accountRequestDto.getAddressLine2())
                .city(accountRequestDto.getCity())
                .country(accountRequestDto.getCountry())
                .zip(accountRequestDto.getZip())
                .logo(accountRequestDto.getLogo())
                .favicon(accountRequestDto.getFavicon())
                .email(accountRequestDto.getEmail())
                .contact(accountRequestDto.getContact())
                .phone(accountRequestDto.getPhone())
                .metaTitle(accountRequestDto.getMetaTitle())
                .metaKeyword(accountRequestDto.getMetaKeyword())
                .metaDescription(accountRequestDto.getMetaDescription())
                .build();

        Account savedAdmin = accountRepository.save(account);

        String tenant = savedAdmin.getUuid().toString();

        Flyway fly = Flyway.configure()
                .configuration(flyway.getConfiguration())
                .schemas(tenant)
                .defaultSchema(tenant)
                .load();

        fly.migrate();

        return ResponseEntity.status(HttpStatus.OK).body("successfully created tenant");

    }

    @GetMapping("/getAll")
    public List<Account> getAll() {
        return accountRepository.findAll();
    }
}
