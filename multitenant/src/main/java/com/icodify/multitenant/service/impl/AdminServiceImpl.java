package com.icodify.multitenant.service.impl;

import com.icodify.multitenant.exception.ResourceNotFoundException;
import com.icodify.multitenant.model.dto.request.AdminRequestDto;
import com.icodify.multitenant.model.dto.response.AccountResponseDto;
import com.icodify.multitenant.model.dto.response.AdminResponseDto;
import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.model.entities.AccountAdmins;
import com.icodify.multitenant.model.entities.Admin;
import com.icodify.multitenant.repository.AccountRepository;
import com.icodify.multitenant.repository.AdminRepository;
import com.icodify.multitenant.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
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
    public AdminResponseDto createAdmin(AdminRequestDto adminRequestDto, Integer accountId) {

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));

        Admin admin = this.modelMapper.map(adminRequestDto, Admin.class);
        admin.setPassword(passwordEncoder.encode(adminRequestDto.getPassword()));

        AccountAdmins accountAdmins = this.modelMapper.map(adminRequestDto, AccountAdmins.class);
        accountAdmins.setUuid(UUID.randomUUID());
        accountAdmins.setAdmin(admin);
        accountAdmins.setAccount(account);

        if (admin.getAdminRoles() != null) {
            admin.getAccountAdmins().add(accountAdmins);
        } else {
            admin.setAccountAdmins(Set.of(accountAdmins));
        }

        Admin savedAdmin = this.adminRepository.save(admin);

        AccountResponseDto accountResponseDto = this.modelMapper.map(account, AccountResponseDto.class);

        return AdminResponseDto.builder()
                .id(savedAdmin.getId())
                .firstName(savedAdmin.getFirstName())
                .middleName(savedAdmin.getMiddleName())
                .lastName(savedAdmin.getLastName())
                .email(savedAdmin.getEmail())
                .status(savedAdmin.isStatus())
                .isVerified(savedAdmin.isVerified())
                .rememberToken(savedAdmin.getRememberToken())
                .uuid(accountAdmins.getUuid())
                .accounts(Set.of(accountResponseDto))
                .isInvitation(accountAdmins.getIsInvitation())
                .invitedById(accountAdmins.getInvitedById())
                .isActive(accountAdmins.isActive())
                .activatedDate(accountAdmins.getActivatedDate())
                .activatedReason(accountAdmins.getActivatedReason())
                .build();
    }

    @Override
    public AdminResponseDto updateAdmin(AdminRequestDto adminDto, Integer adminId, Integer accountId) {
        Admin admin = this.adminRepository.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin", "Id", adminId));
        admin.setFirstName(adminDto.getFirstName());
        admin.setMiddleName(adminDto.getMiddleName());
        admin.setLastName(adminDto.getLastName());
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(adminDto.getPassword());
        admin.setStatus(adminDto.isStatus());
        admin.setVerified(adminDto.isVerified());
        admin.setRememberToken(adminDto.getRememberToken());

        Account account = accountRepository.findById(accountId).orElseThrow(() -> new ResourceNotFoundException("Account", "Id", accountId));

        Set<AccountAdmins> accountAdmins = admin.getAccountAdmins();

        accountAdmins.stream().filter(f -> f.getAdmin().getId() == adminId && f.getAccount().getId() == accountId).forEach(accountAdmin -> {
            accountAdmin.setAdmin(admin);
            accountAdmin.setAccount(account);
            accountAdmin.setIsInvitation(adminDto.getIsInvitation());
            accountAdmin.setInvitedById(adminDto.getInvitedById());
            accountAdmin.setActive(adminDto.isActive());
            accountAdmin.setActivatedDate(adminDto.getActivatedDate());
            accountAdmin.setActivatedReason(adminDto.getActivatedReason());
        });

        admin.setAccountAdmins(accountAdmins);


        Admin updateAdmin = adminRepository.save(admin);

        AccountAdmins accAdmin = accountAdmins.stream().filter(f -> f.getAdmin().getId() == adminId && f.getAccount().getId() == accountId).findFirst().orElseThrow(ResourceNotFoundException::new);

        return AdminResponseDto.builder()
                .id(updateAdmin.getId())
                .firstName(updateAdmin.getFirstName())
                .middleName(updateAdmin.getMiddleName())
                .lastName(updateAdmin.getLastName())
                .email(updateAdmin.getEmail())
                .status(updateAdmin.isStatus())
                .isVerified(updateAdmin.isVerified())
                .rememberToken(updateAdmin.getRememberToken())
                .uuid(accAdmin.getUuid())
                .accounts(Set.of(this.modelMapper.map(account, AccountResponseDto.class)))
                .isInvitation(accAdmin.getIsInvitation())
                .invitedById(accAdmin.getInvitedById())
                .isActive(accAdmin.isActive())
                .activatedDate(accAdmin.getActivatedDate())
                .activatedReason(accAdmin.getActivatedReason())
                .build();

    }

    @Override
    public AdminResponseDto getAdminById(Integer adminId) {

        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin", "Id", adminId));
        Set<AccountAdmins> accountAdmins = admin.getAccountAdmins();

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

        accountAdmins.stream().forEach(accAdmin -> {
            AccountResponseDto accountResponseDto = this.modelMapper.map(accAdmin.getAccount(), AccountResponseDto.class);

            adminResponseDto.setUuid(accAdmin.getUuid());

            if (adminResponseDto.getAccounts() != null)
                adminResponseDto.getAccounts().add(accountResponseDto);
            else
                adminResponseDto.setAccounts(Set.of(accountResponseDto));

            adminResponseDto.setIsInvitation(accAdmin.getIsInvitation());
            adminResponseDto.setInvitedById(accAdmin.getInvitedById());
            adminResponseDto.setActive(accAdmin.isActive());
            adminResponseDto.setActivatedDate(accAdmin.getActivatedDate());
            adminResponseDto.setActivatedReason(accAdmin.getActivatedReason());

        });

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
            admin.getAccountAdmins().stream().forEach(accountAdmin -> {
                AccountResponseDto accountResponseDto = this.modelMapper.map(accountAdmin.getAccount(), AccountResponseDto.class);

                adminResponseDto.setUuid(accountAdmin.getUuid());

                if (adminResponseDto.getAccounts() != null)
                    adminResponseDto.getAccounts().add(accountResponseDto);
                else
                    adminResponseDto.setAccounts(Set.of(accountResponseDto));

                adminResponseDto.setIsInvitation(accountAdmin.getIsInvitation());
                adminResponseDto.setInvitedById(accountAdmin.getInvitedById());
                adminResponseDto.setActive(accountAdmin.isActive());
                adminResponseDto.setActivatedDate(accountAdmin.getActivatedDate());
                adminResponseDto.setActivatedReason(accountAdmin.getActivatedReason());
            });

            return adminResponseDto;

        }).collect(Collectors.toList());


//        return adminResponseDtos;
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        Admin admin = adminRepository.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin", "Id", adminId));

        adminRepository.deleteById(adminId);
    }
}
