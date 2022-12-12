package com.icodify.multitenant.model.dto.request;

import com.icodify.multitenant.model.entities.Account;
import com.icodify.multitenant.model.entities.Admin;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

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
    private boolean isVerified = false;
    private String rememberToken;
//    private UUID uuid;
//    private Account account;
    private String isInvitation;
    private String invitedById;
    private boolean isActive;
    private Date activatedDate;
    private String activatedReason;

}
