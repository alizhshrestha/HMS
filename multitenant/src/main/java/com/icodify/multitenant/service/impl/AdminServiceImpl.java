package com.icodify.multitenant.service.impl;

import com.icodify.multitenant.config.multitenancy.context.TenantContext;
import com.icodify.multitenant.exception.ResourceNotFoundException;
import com.icodify.multitenant.model.dto.request.AdminRequestDto;
import com.icodify.multitenant.model.dto.response.AdminResponseDto;
import com.icodify.multitenant.model.entities.Admin;
import com.icodify.multitenant.repository.AccountRepository;
import com.icodify.multitenant.repository.AdminRepository;
import com.icodify.multitenant.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository,
                            ModelMapper modelMapper,
                            AccountRepository accountRepository,
                            BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AdminResponseDto createAdmin(AdminRequestDto adminRequestDto) {

//        TenantContext.setCurrentTenant(tenant);

        Admin admin = Admin.builder()
                .firstName(adminRequestDto.getFirstName())
                .middleName(adminRequestDto.getMiddleName())
                .lastName(adminRequestDto.getLastName())
                .email(adminRequestDto.getEmail())
                .password(passwordEncoder.encode(adminRequestDto.getPassword()))
                .status(adminRequestDto.isStatus())
                .isVerified(adminRequestDto.isVerified())
                .rememberToken(adminRequestDto.getRememberToken())
                .build();

        Admin savedAdmin = this.adminRepository.save(admin);

        return AdminResponseDto.builder()
                .id(savedAdmin.getId())
                .firstName(savedAdmin.getFirstName())
                .middleName(savedAdmin.getMiddleName())
                .lastName(savedAdmin.getLastName())
                .email(savedAdmin.getEmail())
                .status(savedAdmin.isStatus())
                .isVerified(savedAdmin.isVerified())
                .rememberToken(savedAdmin.getRememberToken())
                .build();
    }

    @Override
    public AdminResponseDto updateAdmin(AdminRequestDto adminDto, Integer adminId) {
        Admin admin = this.adminRepository.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin", "Id", adminId));
        admin.setFirstName(adminDto.getFirstName());
        admin.setMiddleName(adminDto.getMiddleName());
        admin.setLastName(adminDto.getLastName());
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setStatus(adminDto.isStatus());
        admin.setVerified(adminDto.isVerified());
        admin.setRememberToken(adminDto.getRememberToken());

        Admin updateAdmin = adminRepository.save(admin);

        return AdminResponseDto.builder()
                .id(updateAdmin.getId())
                .firstName(updateAdmin.getFirstName())
                .middleName(updateAdmin.getMiddleName())
                .lastName(updateAdmin.getLastName())
                .email(updateAdmin.getEmail())
                .status(updateAdmin.isStatus())
                .isVerified(updateAdmin.isVerified())
                .rememberToken(updateAdmin.getRememberToken())
                .build();

    }

    @Override
    public AdminResponseDto getAdminById(Integer adminId) {

        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin", "Id", adminId));

        AdminResponseDto adminResponseDto = AdminResponseDto.builder()
                .id(admin.getId())
                .firstName(admin.getFirstName())
                .middleName(admin.getMiddleName())
                .lastName(admin.getLastName())
                .email(admin.getEmail())
                .status(admin.isStatus())
                .isVerified(admin.isVerified())
                .rememberToken(admin.getRememberToken())
                .build();

        return adminResponseDto;
    }

    @Override
    public List<AdminResponseDto> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();

        return admins.stream().map(admin -> {
            AdminResponseDto adminResponseDto = new AdminResponseDto();
            adminResponseDto.setId(admin.getId());
            adminResponseDto.setFirstName(admin.getFirstName());
            adminResponseDto.setMiddleName(admin.getMiddleName());
            adminResponseDto.setLastName(admin.getLastName());
            adminResponseDto.setEmail(admin.getEmail());
            adminResponseDto.setStatus(admin.isStatus());
            adminResponseDto.setVerified(admin.isVerified());
            adminResponseDto.setRememberToken(admin.getRememberToken());
            return adminResponseDto;

        }).collect(Collectors.toList());
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin", "Id", adminId));

        adminRepository.deleteById(adminId);
    }
}
