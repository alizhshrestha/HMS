package com.icodify.multitenant.service;

import com.icodify.multitenant.model.dto.request.RoleRequestDto;
import com.icodify.multitenant.model.dto.response.RoleResponseDto;

import java.util.List;
import java.util.UUID;

public interface RoleService {

    //save admin
    RoleResponseDto createRole(RoleRequestDto roleRequestDto);

    //update admin
    RoleResponseDto updateRole(RoleRequestDto roleRequestDto, UUID roleId);

    //get admin
    RoleResponseDto getRoleById(UUID roleId);

    //get all admins
    List<RoleResponseDto> getAllRoles();

    //delete admins
    void deleteRole(UUID roleId);
}
