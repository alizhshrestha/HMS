package com.icodify.multitenant.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.repository.AccountRepository;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/multitenant/report")
public class ReportController {

    private AccountRepository accountRepository;

    public ReportController(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @GetMapping("/excel")
    public ResponseEntity<byte[]> getEntities() throws IOException{

        List<Account> accounts = accountRepository.findAll();
        
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAccounts = objectMapper.writeValueAsString(accounts);

//        ResponseEntity<byte[]> responseEntity = new RestTemplate().getForEntity("http://localhost:8080/api/report/excel/{accountDto}", byte[].class, jsonAccounts);
//        ResponseEntity<byte[]> responseEntity = new RestTemplate().getForObject("http://localhost:8080/api/report/excel", byte[].class);

        String uriTemplate = "http://localhost:8080/api/report/excel?accountDto={accountDto}";
        URI uri = UriComponentsBuilder.fromUriString(uriTemplate).build(jsonAccounts);

        RequestEntity<Void> requestEntity = RequestEntity.get(uri)
                .build();

        ResponseEntity<byte[]> responseEntity = new RestTemplate().exchange(requestEntity, byte[].class);

        return responseEntity;
    }

}
