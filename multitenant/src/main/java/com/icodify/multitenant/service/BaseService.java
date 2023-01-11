package com.icodify.multitenant.service;

import com.icodify.multitenant.model.dto.request.AccountRequestDto;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;
import com.icodify.multitenant.model.entities.Account;

public interface BaseService {

    AccountResponseDto createTenantSchema(AccountRequestDto accountRequestDto);

    String register(AccountRequestDto accountRequestDto);

    Account[] getAllTenants();
}
