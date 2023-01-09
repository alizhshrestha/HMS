package com.icodify.multitenant.model.entities;

import lombok.*;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "admins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Admin extends BaseEntity implements UserDetails {
    @Id
    @SequenceGenerator(name = "seq_gen_admin", sequenceName = "seq_admin", initialValue = 1, allocationSize = 1)
    @GeneratedValue(generator = "seq_gen_admin")
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
    private boolean verified = false;
    @Column(name="remember_token" ,length = 256)
    private String rememberToken;

//    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<AccountAdmins> accountAdmins = new HashSet<>();

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<AdminRoles> adminRoles = new HashSet<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.adminRoles.stream()
                .map(adminRole -> new SimpleGrantedAuthority(adminRole.getRole().getName())).collect(Collectors.toList());
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
