package com.icodify.multitenant.service;

import com.icodify.multitenant.model.dto.request.AdminRequestDto;
import com.icodify.multitenant.model.dto.response.AdminResponseDto;
import com.icodify.multitenant.model.entities.Admin;

public interface AdminService {

    //save admin
    AdminResponseDto saveAdmin(AdminRequestDto adminDto, Integer accountId);

    Admin createAdmin(Admin admin);

    //update admin
    AdminResponseDto updateAdmin(AdminRequestDto adminDto, Integer adminId);

    //get admin
    AdminResponseDto getAdminById(Integer adminId);

    //get all admins
    AdminResponseDto getAllAdmins();

    //delete admins
    void deleteAdmin(Integer adminId);
}
