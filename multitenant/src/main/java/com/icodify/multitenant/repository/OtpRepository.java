package com.icodify.multitenant.repository;

import com.icodify.multitenant.model.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OtpRepository extends JpaRepository<Otp, UUID> {

    Optional<Otp> findByOtpFor(String email);
}
