package com.icodify.multitenant.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "admins")
@SequenceGenerator(name = "seq_gen", sequenceName = "seq_admin", initialValue = 1, allocationSize = 1)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin extends BaseEntity{
    @Id
    @GeneratedValue(generator = "seq_gen")
    protected Integer id;

    private String first_name;
    private String middle_name;
    private String last_name;
    private String email;
    private String password;
    private boolean status = false;
    private boolean is_verified = false;
    private String remember_token;

    @OneToMany(mappedBy = "admin")
    private Set<AccountAdmins> accountAdmins = new HashSet<>();

    @OneToMany(mappedBy = "admin")
    private Set<AdminRoles> adminRoles = new HashSet<>();

}
