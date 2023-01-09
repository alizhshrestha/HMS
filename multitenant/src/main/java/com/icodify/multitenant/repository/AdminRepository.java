package com.icodify.multitenant.repository;

import com.icodify.multitenant.model.entities.Admin;
import com.icodify.multitenant.model.entities.AdminRoles;
import com.icodify.multitenant.model.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Optional<Admin> findByEmail(String email);

    Admin findByEmailAndPassword(String email, String password);

    boolean existsAdminByAdminRoles_Role(Role role);

}
