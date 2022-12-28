package com.icodify.multitenant.service.impl;

import com.icodify.multitenant.model.dto.request.AccountRequestDto;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;
import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.service.AccountService;
import com.icodify.multitenant.service.BaseService;
import org.flywaydb.core.Flyway;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BaseServiceImpl implements BaseService {

    private final AccountService accountService;
    private final Flyway flyway;

    private RestTemplate restTemplate;

    public BaseServiceImpl(AccountService accountService,
                           Flyway flyway,
                           RestTemplate restTemplate) {
        this.accountService = accountService;
        this.flyway = flyway;
        this.restTemplate = restTemplate;
    }

    @Override
    public AccountResponseDto createTenantSchema(AccountRequestDto accountRequestDto) {
        AccountResponseDto createdAccount = accountService.createAccount(accountRequestDto);

//        String tenant = "tenant" + createdAccount.getId();
        String tenant = createdAccount.getUuid().toString();

        Flyway fly = Flyway.configure()
                .configuration(flyway.getConfiguration())
                .schemas(tenant)
                .defaultSchema(tenant)
                .load();

        fly.migrate();

        return createdAccount;

    }

    @Override
    public String register(AccountRequestDto accountRequestDto) {
        boolean exists = false;

        HttpHeaders headers = new HttpHeaders();
        ResponseEntity<Account[]> response = restTemplate.exchange("http://localhost:2027/base/getAll", HttpMethod.GET,
                new HttpEntity<Object>(headers), Account[].class);

        if(response.getBody() != null){
            Account[] accountList = response.getBody();
            if(accountList != null && accountList.length > 0){
                for (Account account : accountList){
                    if(account.getEmail().equalsIgnoreCase(accountRequestDto.getEmail())){
                        exists = true;
                    }
                }
            }
        }

        if(exists){
            return "tenant already exists";
        }

        String url = "http://localhost:2027/base/addsource";
        try{
            restTemplate.postForEntity(url, accountRequestDto, String.class);
        }catch(Exception e){

        }

        return "tenant registered successfully";
    }


}
