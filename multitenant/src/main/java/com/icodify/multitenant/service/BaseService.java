package com.icodify.multitenant.service;

import com.icodify.multitenant.model.dto.request.AccountRequestDto;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;

public interface BaseService {

    AccountResponseDto createTenantSchema(AccountRequestDto accountRequestDto);
}
