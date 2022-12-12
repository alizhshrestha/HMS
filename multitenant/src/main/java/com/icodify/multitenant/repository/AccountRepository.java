package com.icodify.multitenant.repository;

import com.icodify.multitenant.model.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Integer> {


}
