package com.example.multitenantbaseservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Admin extends BaseEntity {
    @Id
    @SequenceGenerator(name = "seq_gen", sequenceName = "seq_admin", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "seq_gen")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 100)
    private String firstName;
    @Column(name = "middle_name", length = 100)
    private String middleName;
    @Column(name = "last_name", length = 100, nullable = true)
    private String lastName;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "password", length = 100)
    private String password;
    private boolean status = false;
    @Column(name = "is_verified")
    private boolean isVerified = false;
    @Column(name = "remember_token", length = 256)
    private String rememberToken;

//    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<AccountAdmins> accountAdmins = new HashSet<>();

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AdminRoles> adminRoles = new HashSet<>();

}
