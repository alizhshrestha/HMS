package com.icodify.multitenant.model.dto.request;

import com.icodify.multitenant.model.entities.BaseEntity;
import com.icodify.multitenant.model.entities.UserSocialLogin;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequestDto{

    private Integer id;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String image;
    private String password;
    private Boolean status = false;
    private Boolean isVerified = false;
    private String rememberToken;
    private Date lastActive;
    private Date deletedAt;

//    @OneToMany(mappedBy = "user")
//    private Set<UserSocialLogin> userSocialLogins = new HashSet<>();
}
