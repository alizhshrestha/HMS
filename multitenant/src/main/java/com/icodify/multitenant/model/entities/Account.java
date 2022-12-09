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
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account extends BaseEntity{

    @Id
//    @SequenceGenerator(name = "seq_gen", sequenceName = "seq_account", allocationSize = 1, initialValue = 1)
//    @GeneratedValue(generator = "seq_gen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    private UUID uuid;
    @Column(length = 100)
    private String title;
    @Lob
    @Column(nullable = true)
    private String description;
    @Column(name = "address_line1", length = 100)
    private String addressLine1;
    @Column(name = "address_line2", length = 50, nullable = true)
    private String addressLine2;
    @Column(length = 100)
    private String city;
    @Column(length = 100)
    private String country;
    @Column(length = 50)
    private String zip;
    @Column(length = 256)
    private String logo;
    @Column(length = 256, nullable = true)
    private String favicon;
    @Column(length = 50)
    private String email;
    @Column(length = 50, nullable = true)
    private String contact;
    @Column(length = 50, nullable = true)
    private String phone;
    @Column(name = "meta_title", length = 100)
    private String metaTitle;
    @Column(name = "meta_keyword", length = 100, nullable = true)
    private String metaKeyword;
    @Column(name = "meta_description", nullable = true)
    @Lob
    private String metaDescription;

    @OneToMany(mappedBy = "account")
    private Set<AccountAdmins> accountAdmins = new HashSet<>();


}
