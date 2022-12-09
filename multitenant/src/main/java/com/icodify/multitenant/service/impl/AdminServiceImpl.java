package com.icodify.multitenant.service.impl;

import com.icodify.multitenant.exception.ResourceNotFoundException;
import com.icodify.multitenant.model.dto.request.AdminRequestDto;
import com.icodify.multitenant.model.dto.response.AdminResponseDto;
import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.model.entities.AccountAdmins;
import com.icodify.multitenant.model.entities.Admin;
import com.icodify.multitenant.repository.AccountRepository;
import com.icodify.multitenant.repository.AdminRepository;
import com.icodify.multitenant.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    private AccountRepository accountRepository;
    private ModelMapper modelMapper;

    public AdminServiceImpl(AdminRepository adminRepository,
                            ModelMapper modelMapper,
                            AccountRepository accountRepository) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
    }

    @Override
    public AdminResponseDto saveAdmin(AdminRequestDto adminDto, Integer accountId) {

        Admin admin = this.modelMapper.map(adminDto, Admin.class);

//        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));
//        admin.getAccountAdmins().add(AccountAdmins.builder()
//                        .uuid(UUID.randomUUID())
//                        .account(account)
//                .build());

        Admin savedAdmin = adminRepository.save(admin);
        AdminResponseDto savedAdminResponseDto = this.modelMapper.map(savedAdmin, AdminResponseDto.class);
//        savedAdminResponseDto.getAccounts().add(account);



        return savedAdminResponseDto;
    }

    @Override
    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    @Override
    public AdminResponseDto updateAdmin(AdminRequestDto adminDto, Integer adminId) {
//        Admin admin = this.adminRepository.findById(adminId).orElseThrow(()->new ResourceNotFoundException("Admin", "id", adminId));
//        admin.setFirst_name(adminDto.getFirst_name());
//        admin.setMiddle_name(adminDto.getMiddle_name());
//        admin.setLast_name(adminDto.getLast_name());
//        admin.setEmail(adminDto.getEmail());
//        admin.setPassword(adminDto.getPassword());
//        admin.setStatus(adminDto.isStatus());
//        admin.set_verified(adminDto.is_verified());
//        admin.setRemember_token(adminDto.getRemember_token());
//        admin.set
//
//        Admin admin = this.modelMapper.map(adminDto, Admin.class);

        return null;
    }

    @Override
    public AdminResponseDto getAdminById(Integer adminId) {
        return null;
    }

    @Override
    public AdminResponseDto getAllAdmins() {
        return null;
    }

    @Override
    public void deleteAdmin(Integer adminId) {

    }
}
