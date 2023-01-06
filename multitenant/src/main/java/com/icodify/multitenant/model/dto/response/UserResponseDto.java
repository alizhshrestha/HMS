package com.icodify.multitenant.model.dto.response;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {

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
