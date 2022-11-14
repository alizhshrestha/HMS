package com.icodify.multitenant.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@SequenceGenerator(name = "seq_gen", sequenceName = "seq_user", allocationSize = 1, initialValue = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Id
    @GeneratedValue(generator = "seq_gen")
    protected Integer id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String image;
    private String password;
    private Boolean status = false;
    @Column(name = "is_verified")
    private Boolean isVerified = false;
    @Column(name = "remember_token")
    private String rememberToken;
    @Column(name = "last_active")
    private Date lastActive;
    @Column(name = "deleted_at")
    private Date deletedAt;

    @OneToMany(mappedBy = "user")
    private Set<UserSocialLogin> userSocialLogins = new HashSet<>();
}
