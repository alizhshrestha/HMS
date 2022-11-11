package com.icodify.multitenant.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
import java.util.Date;
import java.util.UUID;

@Entity
@SequenceGenerator(name = "seq_gen", sequenceName = "account", allocationSize = 1, initialValue = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity{

    private UUID uuid;
    private String title;
    private String address_line1;
    private String address_line2;
    private String city;
    private String country;
    private String zip;
    private String logo;
    private String favicon;
    private String email;
    private String contact;
    private String phone;
    private String meta_title;
    private String meta_keyword;
    private String meta_description;
    private Date created_at;
    private Date updated_at;

}
