package com.icodify.multitenant.model.dto.response;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountResponseDto {

    private int id;
    private UUID uuid;
    private String title;
    private String description;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String country;
    private String zip;
    private String logo;
    private String favicon;
    private String email;
    private String contact;
    private String phone;
    private String metaTitle;
    private String metaKeyword;
    private String metaDescription;

//    private AdminResponseDto admin;
//    private AccountResponseDto account;


}
