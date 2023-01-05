package com.icodify.multitenant.service.impl;

import com.icodify.multitenant.exception.ResourceNotFoundException;
import com.icodify.multitenant.model.dto.request.AccountRequestDto;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;
import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.repository.AccountRepository;
import com.icodify.multitenant.service.AccountService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    public AccountServiceImpl(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public AccountResponseDto createAccount(AccountRequestDto accountDto) {

        Account account = Account.builder()
                .uuid(UUID.randomUUID())
                .title(accountDto.getTitle())
                .description(accountDto.getDescription())
                .addressLine1(accountDto.getAddressLine1())
                .addressLine2(accountDto.getAddressLine2())
                .city(accountDto.getCity())
                .country(accountDto.getCountry())
                .zip(accountDto.getZip())
                .logo(accountDto.getLogo())
                .favicon(accountDto.getFavicon())
                .email(accountDto.getEmail())
                .contact(accountDto.getContact())
                .phone(accountDto.getPhone())
                .metaTitle(accountDto.getMetaTitle())
                .metaKeyword(accountDto.getMetaKeyword())
                .metaDescription(accountDto.getMetaDescription())
                .build();

        Account createdAccount = accountRepository.save(account);
        return AccountResponseDto.builder()
                .id(createdAccount.getId())
                .uuid(createdAccount.getUuid())
                .title(createdAccount.getTitle())
                .description(createdAccount.getDescription())
                .addressLine1(createdAccount.getAddressLine1())
                .addressLine2(createdAccount.getAddressLine2())
                .city(createdAccount.getCity())
                .country(createdAccount.getCountry())
                .zip(createdAccount.getZip())
                .logo(createdAccount.getLogo())
                .favicon(createdAccount.getFavicon())
                .email(createdAccount.getEmail())
                .contact(createdAccount.getContact())
                .phone(createdAccount.getPhone())
                .metaTitle(createdAccount.getMetaTitle())
                .metaKeyword(createdAccount.getMetaKeyword())
                .metaDescription(createdAccount.getMetaDescription())
                .build();
    }

    @Override
    public AccountResponseDto updateAccount(AccountRequestDto accountDto, Integer accountId) {

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));

        account.setUuid(account.getUuid());
        account.setTitle(accountDto.getTitle());
        account.setDescription(accountDto.getDescription());
        account.setAddressLine1(accountDto.getAddressLine1());
        account.setAddressLine2(accountDto.getAddressLine2());
        account.setCity(accountDto.getCity());
        account.setCountry(accountDto.getCountry());
        account.setZip(accountDto.getZip());
        account.setLogo(accountDto.getLogo());
        account.setFavicon(accountDto.getFavicon());
        account.setEmail(accountDto.getEmail());
        account.setContact(accountDto.getContact());
        account.setPhone(accountDto.getPhone());
        account.setMetaTitle(accountDto.getMetaTitle());
        account.setMetaKeyword(accountDto.getMetaKeyword());
        account.setMetaDescription(accountDto.getMetaDescription());

        Account updatedAccount = accountRepository.save(account);
        return this.modelMapper.map(updatedAccount, AccountResponseDto.class);
    }

    @Override
    public AccountResponseDto getAccountById(Integer accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));

        return this.modelMapper.map(account, AccountResponseDto.class);
    }

    @Override
    public List<AccountResponseDto> getAllAccounts() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(acc -> modelMapper.map(acc, AccountResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Integer accountId) {
        accountRepository.deleteById(accountId);
    }
}
