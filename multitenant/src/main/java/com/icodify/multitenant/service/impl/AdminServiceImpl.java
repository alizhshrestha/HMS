package com.icodify.multitenant.service.impl;

import com.icodify.multitenant.exception.ResourceNotFoundException;
import com.icodify.multitenant.model.dto.request.AdminRequestDto;
import com.icodify.multitenant.model.dto.response.AdminResponseDto;
import com.icodify.multitenant.model.entities.Admin;
import com.icodify.multitenant.model.entities.AdminRoles;
import com.icodify.multitenant.model.entities.Role;
import com.icodify.multitenant.repository.AccountRepository;
import com.icodify.multitenant.repository.AdminRepository;
import com.icodify.multitenant.repository.AdminRoleRepository;
import com.icodify.multitenant.repository.RoleRepository;
import com.icodify.multitenant.service.AdminService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final AdminRoleRepository adminRoleRepository;

    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public AdminServiceImpl(AdminRepository adminRepository,
                            ModelMapper modelMapper,
                            AccountRepository accountRepository,
                            RoleRepository roleRepository,
                            AdminRoleRepository adminRoleRepository,
                            BCryptPasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.modelMapper = modelMapper;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.adminRoleRepository = adminRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AdminResponseDto createAdmin(AdminRequestDto adminRequestDto, List<UUID> roleIds) {

        Admin admin = Admin.builder()
                .firstName(adminRequestDto.getFirstName())
                .middleName(adminRequestDto.getMiddleName())
                .lastName(adminRequestDto.getLastName())
                .email(adminRequestDto.getEmail())
                .password(passwordEncoder.encode(adminRequestDto.getPassword()))
                .status(adminRequestDto.isStatus())
                .verified(adminRequestDto.isVerified())
                .rememberToken(adminRequestDto.getRememberToken())
                .adminRoles(Set.of())
                .build();

        Admin savedAdmin = this.adminRepository.save(admin);

        for (UUID roleId : roleIds) {
            Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", "Id " + roleId));

            AdminRoles adminRoles = new AdminRoles();
            adminRoles.setId(UUID.randomUUID());
            adminRoles.setAdmin(admin);
            adminRoles.setRole(role);

            adminRoleRepository.save(adminRoles);
        }

        return AdminResponseDto.builder()
                .id(savedAdmin.getId())
                .firstName(savedAdmin.getFirstName())
                .middleName(savedAdmin.getMiddleName())
                .lastName(savedAdmin.getLastName())
                .email(savedAdmin.getEmail())
                .status(savedAdmin.isStatus())
                .verified(savedAdmin.isVerified())
                .rememberToken(savedAdmin.getRememberToken())
                .build();
    }

    @Override
    public AdminResponseDto updateAdmin(AdminRequestDto adminDto, Integer adminId, List<UUID> roleIds) {
        Admin admin = this.adminRepository.findById(adminId).orElseThrow(() -> new ResourceNotFoundException("Admin", "Id", adminId));
        admin.setFirstName(adminDto.getFirstName());
        admin.setMiddleName(adminDto.getMiddleName());
        admin.setLastName(adminDto.getLastName());
        admin.setEmail(adminDto.getEmail());
        admin.setPassword(passwordEncoder.encode(adminDto.getPassword()));
        admin.setStatus(adminDto.isStatus());
        admin.setVerified(adminDto.isVerified());
        admin.setRememberToken(adminDto.getRememberToken());

//        List<AdminRoles> adminRolesList = adminRoleRepository.findByAdmin(admin).orElseThrow(ResourceNotFoundException::new);

        AtomicBoolean roleExists = new AtomicBoolean(false);

        for (UUID roleId : roleIds) {

            Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", "Id " + roleId));

//            List<Role> roles = admin.getAdminRoles().stream().map(adminRoles -> adminRoles.getRole()).collect(Collectors.toList());

            if(adminRoleRepository.existsAdminRolesByAdminAndRole_Id(admin, roleId)){
                roleExists.set(true);
            }

//            roles.forEach(r -> {
//                if (r.equals(role)) {
//                    roleExists.set(true);
//                }
//            });

            if (!roleExists.get()) {
                admin.getAdminRoles().add(AdminRoles.builder()
                        .id(UUID.randomUUID())
                        .admin(admin)
                        .role(role)
                        .build());
            }

            roleExists.set(false);

        }


        Admin updateAdmin = adminRepository.save(admin);

        return AdminResponseDto.builder()
                .id(updateAdmin.getId())
                .firstName(updateAdmin.getFirstName())
                .middleName(updateAdmin.getMiddleName())
                .lastName(updateAdmin.getLastName())
                .email(updateAdmin.getEmail())
                .status(updateAdmin.isStatus())
                .verified(updateAdmin.isVerified())
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
                .verified(admin.isVerified())
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
