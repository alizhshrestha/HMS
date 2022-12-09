package com.icodify.multitenant.controller;

import com.icodify.multitenant.model.dto.request.AdminRequestDto;
import com.icodify.multitenant.model.dto.response.AdminResponseDto;
import com.icodify.multitenant.model.entities.Admin;
import com.icodify.multitenant.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    //save admin
//    @PostMapping("/{accountId}")
//    public ResponseEntity<AdminResponseDto> savedAdmin(@RequestBody AdminRequestDto adminRequestDto, @PathVariable Integer accountId){
//        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.saveAdmin(adminRequestDto, accountId));
//    }

    @PostMapping
    public ResponseEntity<Admin> savedAdmin(@RequestBody Admin admin){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createAdmin(admin));
    }

    //get specific admin by id

    //get all admin

    //update admin

    //delete admin
}
