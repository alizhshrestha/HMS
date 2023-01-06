package com.icodify.multitenant.repository;

import com.icodify.multitenant.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
