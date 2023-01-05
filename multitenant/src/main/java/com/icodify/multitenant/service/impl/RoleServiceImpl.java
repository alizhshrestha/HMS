package com.icodify.multitenant.service.impl;

import com.icodify.multitenant.exception.ResourceNotFoundException;
import com.icodify.multitenant.model.dto.request.RoleRequestDto;
import com.icodify.multitenant.model.dto.response.RoleResponseDto;
import com.icodify.multitenant.model.entities.Role;
import com.icodify.multitenant.repository.RoleRepository;
import com.icodify.multitenant.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {
        Role role = Role.builder()
                .id(UUID.randomUUID())
                .name(roleRequestDto.getName())
                .guardName(roleRequestDto.getGuardName())
                .build();
        roleRepository.save(role);
        return this.modelMapper.map(role, RoleResponseDto.class);
    }

    @Override
    public RoleResponseDto updateRole(RoleRequestDto roleRequestDto, UUID roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", "Id: " + roleId));
        role.setName(roleRequestDto.getName());
        role.setGuardName(roleRequestDto.getGuardName());
        roleRepository.save(role);
        return this.modelMapper.map(role, RoleResponseDto.class);
    }

    @Override
    public RoleResponseDto getRoleById(UUID roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new ResourceNotFoundException("Role", "Id: " + roleId));
        return this.modelMapper.map(role, RoleResponseDto.class);
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream().map(r -> this.modelMapper.map(r, RoleResponseDto.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteRole(UUID roleId) {
        roleRepository.deleteById(roleId);
    }
}
