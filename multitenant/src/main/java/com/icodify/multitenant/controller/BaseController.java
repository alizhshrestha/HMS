package com.icodify.multitenant.controller;

import com.icodify.multitenant.model.dto.request.AccountRequestDto;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;
import com.icodify.multitenant.service.BaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base")
public class BaseController {

    private final BaseService baseService;

    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }

    @PostMapping("/addsource")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> addDSource(@RequestBody AccountRequestDto accountRequestDto){
        AccountResponseDto createdAccount = baseService.createTenantSchema(accountRequestDto);
        return ResponseEntity.ok("success created tenant" + createdAccount.getId());
    }

}
