package com.icodify.multitenant.repository;

import com.icodify.multitenant.model.entities.Admin;
import com.icodify.multitenant.model.entities.AdminRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AdminRoleRepository extends JpaRepository<AdminRoles, UUID> {

    Optional<List<AdminRoles>> findByAdmin(Admin admin);
}
