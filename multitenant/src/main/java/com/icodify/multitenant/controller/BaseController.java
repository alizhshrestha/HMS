package com.icodify.multitenant.controller;

import com.icodify.multitenant.model.dto.request.AccountRequestDto;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;
import com.icodify.multitenant.model.dto.response.BaseResponse;
import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/base/tenant")
public class BaseController {

    private final BaseService baseService;

    public BaseController(BaseService baseService) {
        this.baseService = baseService;
    }

//    @PostMapping("/addsource")
//    @CrossOrigin(origins = "*")
//    public ResponseEntity<String> addDSource(@RequestBody AccountRequestDto accountRequestDto){
//        AccountResponseDto createdAccount = baseService.createTenantSchema(accountRequestDto);
//        return ResponseEntity.ok("success created tenant" + createdAccount.getId());
//    }

    @PostMapping("/register")
    public ResponseEntity<BaseResponse> registerTenant(@RequestBody AccountRequestDto accountRequestDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(new BaseResponse(HttpStatus.CREATED, "Created tenant successfully", baseService.register(accountRequestDto)));
    }

    @GetMapping("/getAllTenants")
    public ResponseEntity<BaseResponse> getAllTenants(){
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK, "Got all tenant successfully", baseService.getAllTenants()));
    }

}
