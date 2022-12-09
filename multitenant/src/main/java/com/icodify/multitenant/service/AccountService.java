package com.icodify.multitenant.service;

import com.icodify.multitenant.model.dto.request.AccountRequestDto;
import com.icodify.multitenant.model.dto.request.AdminRequestDto;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;
import com.icodify.multitenant.model.dto.response.AdminResponseDto;

public interface AccountService {

    //save admin
    AccountResponseDto saveAdmin(AccountRequestDto accountDto);

    //update admin
    AccountResponseDto updateAdmin(AccountRequestDto accountDto, Integer accountId);

    //get admin
    AccountResponseDto getAdminById(Integer accountId);

    //get all admins
    AccountResponseDto getAllAdmins();

    //delete admins
    void deleteAdmin(Integer accountId);

}
