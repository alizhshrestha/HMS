package com.icodify.multitenant.controller;

import com.icodify.multitenant.model.dto.request.AccountRequestDto;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;
import com.icodify.multitenant.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<AccountResponseDto> createAccount(@RequestBody AccountRequestDto accountRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(accountRequestDto));
    }

    @PutMapping("/{accountId}")
    public ResponseEntity<AccountResponseDto> updateAccount(@RequestBody AccountRequestDto accountRequestDto, @PathVariable("accountId") Integer accountId) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.updateAccount(accountRequestDto, accountId));
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<AccountResponseDto> getAccount(@PathVariable("accountId") Integer accountId) {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAccountById(accountId));
    }

    @GetMapping
    public ResponseEntity<List<AccountResponseDto>> getAccount() {
        return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<String> deleteAccountById(@PathVariable("accountId") Integer accountId) {
        accountService.deleteAccount(accountId);
        return ResponseEntity.status(HttpStatus.OK).body("Account Deleted Successfully");
    }

}
