package com.icodify.multitenant.model.dto.response;

import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.model.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponseDto {
    protected Integer id;

    private String first_name;
    private String middle_name;
    private String last_name;
    private String email;
    private String password;
    private final boolean status = false;
    private final boolean is_verified = false;
    private String remember_token;

    private final Set<Account> accounts = new HashSet<>();

    private final Set<Role> roles = new HashSet<>();
}
