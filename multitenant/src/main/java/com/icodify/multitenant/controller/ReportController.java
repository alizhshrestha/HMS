package com.icodify.multitenant.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;
import com.icodify.multitenant.repository.AccountRepository;
import com.icodify.multitenant.service.AccountService;
import com.icodify.multitenant.service.AdminService;
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

    private final AccountRepository accountRepository;
    private final AccountService accountService;
    private final AdminService adminService;

    public ReportController(AccountRepository accountRepository,
                            AccountService accountService,
                            AdminService adminService) {
        this.accountRepository = accountRepository;
        this.accountService = accountService;
        this.adminService = adminService;
    }

    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportAccountsToExcel() throws IOException {
        ResponseEntity<byte[]> responseEntity = getResponseEntityFromUrl("http://localhost:8085/api/report/excel?accountDto={accountDto}");
        return responseEntity;
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> exportAccountsToPdf() throws IOException {
        ResponseEntity<byte[]> responseEntity = getResponseEntityFromUrl("http://localhost:8085/api/report/pdf?accountDto={accountDto}");
        return responseEntity;
    }


    private ResponseEntity<byte[]> getResponseEntityFromUrl(String url) throws JsonProcessingException {
        //        List<Account> accounts = accountRepository.findAll();
        List<AccountResponseDto> accounts = accountService.getAllAccounts();
//        List<AdminResponseDto> admins = adminService.getAllAdmins();

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonAccounts = objectMapper.writeValueAsString(accounts);
//        String jsonAccounts = objectMapper.writeValueAsString(admins);

        String uriTemplate = url;
        URI uri = UriComponentsBuilder.fromUriString(uriTemplate).build(jsonAccounts);

        RequestEntity<Void> requestEntity = RequestEntity.get(uri)
                .build();

        ResponseEntity<byte[]> responseEntity = new RestTemplate().exchange(requestEntity, byte[].class);

        return responseEntity;
    }


}
