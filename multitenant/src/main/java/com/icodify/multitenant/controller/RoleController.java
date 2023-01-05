package com.icodify.multitenant.controller;

import com.icodify.multitenant.model.dto.request.RoleRequestDto;
import com.icodify.multitenant.model.dto.response.RoleResponseDto;
import com.icodify.multitenant.service.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto roleRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleRequestDto));
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<RoleResponseDto> updateRole(@RequestBody RoleRequestDto roleRequestDto, @PathVariable("roleId") UUID roleId) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.updateRole(roleRequestDto, roleId));
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<RoleResponseDto> getRoleById(@PathVariable("roleId") UUID roleId) {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getRoleById(roleId));
    }

    @GetMapping
    public ResponseEntity<List<RoleResponseDto>> getRoles() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAllRoles());
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<String> deleteRoleById(@PathVariable("roleId") UUID roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.status(HttpStatus.OK).body("Role deleted Successfully");
    }


}
