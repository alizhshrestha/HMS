package com.icodify.multitenant.controller;

import com.icodify.multitenant.model.dto.request.AdminRequestDto;
import com.icodify.multitenant.model.dto.response.AdminResponseDto;
import com.icodify.multitenant.service.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admins")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/{roleIds}")
//    @RolesAllowed("ROLE_SUPERADMIN")
    public ResponseEntity<AdminResponseDto> savedAdmin(@RequestBody AdminRequestDto adminRequestDto, @PathVariable("roleIds") List<UUID> roleIds) {
        return ResponseEntity.status(HttpStatus.CREATED).body(adminService.createAdmin(adminRequestDto, roleIds));
    }

    //get specific admin by id
    @GetMapping("/{adminId}")
    public ResponseEntity<AdminResponseDto> getAdminById(@PathVariable("adminId") Integer adminId){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAdminById(adminId));
    }

    //get all admin
    @GetMapping
    public ResponseEntity<List<AdminResponseDto>> getAdmins(){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.getAllAdmins());
    }

    //update admin
    @PutMapping("/{adminId}/role/{roleIds}")
    public ResponseEntity<AdminResponseDto> updateAdmin(@RequestBody AdminRequestDto adminRequestDto,
                                                        @PathVariable("adminId") Integer adminId,
                                                        @PathVariable("roleIds") List<UUID> roleIds){
        return ResponseEntity.status(HttpStatus.OK).body(adminService.updateAdmin(adminRequestDto, adminId, roleIds));
    }

    //delete admin
    @DeleteMapping("/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable("adminId") Integer adminId){
        adminService.deleteAdmin(adminId);
        return ResponseEntity.status(HttpStatus.OK).body("Admin deleted Successfully");
    }

}
