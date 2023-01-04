package com.icodify.multitenant.service;

import com.icodify.multitenant.model.dto.request.AccountRequestDto;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;

import java.util.List;

public interface AccountService {

    //save admin
    AccountResponseDto createAccount(AccountRequestDto accountDto);

    //update admin
    AccountResponseDto updateAccount(AccountRequestDto accountDto, Integer accountId);

    //get admin
    AccountResponseDto getAccountById(Integer accountId);

    //get all admins
    List<AccountResponseDto> getAllAccounts();

    //delete admins
    void deleteAccount(Integer accountId);

}
