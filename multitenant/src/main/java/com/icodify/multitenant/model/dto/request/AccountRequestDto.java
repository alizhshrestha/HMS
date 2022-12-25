package com.icodify.multitenant.model.dto.request;

import com.icodify.multitenant.model.entities.BaseEntity;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccountRequestDto extends BaseEntity {

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
//    private Set<AccountAdmins> accountAdmins = new HashSet<>();


}
