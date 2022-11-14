package com.icodify.multitenant.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@SequenceGenerator(name = "seq_gen", sequenceName = "seq_account", allocationSize = 1, initialValue = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity{

    @Id
    @GeneratedValue(generator = "seq_gen")
    protected Integer id;

    private UUID uuid;
    private String title;
    @Column(name = "address_line1")
    private String addressLine1;
    @Column(name = "address_line2")
    private String addressLine2;
    private String city;
    private String country;
    private String zip;
    private String logo;
    private String favicon;
    private String email;
    private String contact;
    private String phone;
    @Column(name = "meta_title")
    private String metaTitle;
    @Column(name = "meta_keyword")
    private String metaKeyword;
    @Column(name = "meta_description")
    private String metaDescription;

    @OneToMany(mappedBy = "account")
    private Set<AccountAdmins> accountAdmins = new HashSet<>();


}
