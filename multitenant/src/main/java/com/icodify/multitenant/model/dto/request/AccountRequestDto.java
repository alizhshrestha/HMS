package com.icodify.multitenant.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {

    private Integer id;

    private UUID uuid;
    private String title;
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
}
