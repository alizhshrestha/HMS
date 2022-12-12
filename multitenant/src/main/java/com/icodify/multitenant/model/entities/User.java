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
//@SequenceGenerator(name = "seq_gen", sequenceName = "seq_user", allocationSize = 1, initialValue = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity{

    @Id
//    @GeneratedValue(generator = "seq_gen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", length = 100)
    private String firstName;
    @Column(name = "middle_name", length = 100)
    private String middleName;
    @Column(name = "last_name", length = 100)
    private String lastName;
    @Column(name = "email", length = 50)
    private String email;
    @Column(name = "phone", length = 20)
    private String phone;
    @Column(name = "address", length = 100)
    private String address;
    @Column(name = "image", length = 100)
    private String image;
    @Column(name = "password", length = 100)
    private String password;
    private Boolean status = false;
    @Column(name = "is_verified")
    private Boolean isVerified = false;
    @Column(name = "remember_token", length = 256, nullable = true)
    private String rememberToken;
    @Column(name = "last_active", nullable = true)
    private Date lastActive;
    @Column(name = "deleted_at", nullable = true)
    private Date deletedAt;

    @OneToMany(mappedBy = "user")
    private Set<UserSocialLogin> userSocialLogins = new HashSet<>();
}
