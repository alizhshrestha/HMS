package com.icodify.multitenant.model.dto.response;

import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.model.entities.Admin;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminResponseDto {

    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
//    private String password;
    private boolean status = false;
    private boolean verified = false;
    private String rememberToken;

}
