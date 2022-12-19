package com.icodify.multitenant.service.impl;

import com.icodify.multitenant.model.dto.request.AccountRequestDto;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;
import com.icodify.multitenant.service.AccountService;
import com.icodify.multitenant.service.BaseService;
import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class BaseServiceImpl implements BaseService {

    private final AccountService accountService;
    private final Flyway flyway;

    public BaseServiceImpl(AccountService accountService, Flyway flyway) {
        this.accountService = accountService;
        this.flyway = flyway;
    }

    @Override
    public AccountResponseDto createTenantSchema(AccountRequestDto accountRequestDto) {
        AccountResponseDto createdAccount = accountService.createAccount(accountRequestDto);

        String tenant = "tenant" + createdAccount.getId();

        Flyway fly = Flyway.configure()
                .configuration(flyway.getConfiguration())
                .schemas(tenant)
                .defaultSchema(tenant)
                .load();

        fly.migrate();

        return createdAccount;

    }
}
