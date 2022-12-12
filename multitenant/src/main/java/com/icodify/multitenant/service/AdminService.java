package com.icodify.multitenant.service;

import com.icodify.multitenant.model.dto.request.AdminRequestDto;
import com.icodify.multitenant.model.dto.response.AdminResponseDto;

import java.util.List;

public interface AdminService {


    AdminResponseDto createAdmin(AdminRequestDto adminRequestDto, Integer accountId);

    //update admin
    AdminResponseDto updateAdmin(AdminRequestDto adminDto, Integer adminId, Integer accountId);

    //get admin
    AdminResponseDto getAdminById(Integer adminId);

    //get all admins
    List<AdminResponseDto> getAllAdmins();

    //delete admins
    void deleteAdmin(Integer adminId);
}
