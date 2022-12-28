package com.example.multitenantbaseservice.repository;


import com.example.multitenantbaseservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {


}
