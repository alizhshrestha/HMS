package com.example.multitenantbaseservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @Column(length = 100)
    private String name;

    @Column(name = "guard_name", length = 100)
    private String guardName;

    @OneToMany(mappedBy = "role")
    private Set<RolePermissions> rolePermissions = new HashSet<>();

    @OneToMany(mappedBy = "role")
    private Set<AdminRoles> adminRoles = new HashSet<>();

}
