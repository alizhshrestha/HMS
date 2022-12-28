package com.icodify.multitenant.repository;

import com.icodify.multitenant.model.entities.AdminRoles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRoleRepository extends JpaRepository<AdminRoles, UUID> {
}
