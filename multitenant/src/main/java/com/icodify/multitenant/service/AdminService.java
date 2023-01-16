package com.icodify.multitenant.service;

import com.icodify.multitenant.model.dto.request.AdminRequestDto;
import com.icodify.multitenant.model.dto.response.AdminResponseDto;
import com.icodify.multitenant.model.entities.Admin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdminService {


    AdminResponseDto createAdmin(AdminRequestDto adminRequestDto, List<UUID> roleIds);

    //update admin
    AdminResponseDto updateAdmin(AdminRequestDto adminDto, Integer adminId, List<UUID> roleIds);

    //get admin
    AdminResponseDto getAdminById(Integer adminId);

    //get all admins
    List<AdminResponseDto> getAllAdmins();

    AdminResponseDto getAdminByEmail(String email);

    //delete admins
    void deleteAdmin(Integer adminId);
}
