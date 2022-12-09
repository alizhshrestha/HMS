package com.icodify.multitenant.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "user_social_login")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserSocialLogin extends BaseEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "username", length = 256)
    private String username;
    @Column(name = "provider", length = 30)
    private String provider;
    @Column(name = "provider_id", length = 100)
    private String providerId;
    private String image;
}
