package com.icodify.multitenant.model.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminRequestDto {

    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String password;
    private boolean status = false;
    private boolean verified = false;
    private String rememberToken;
//    private UUID uuid;
//    private Account account;

}
