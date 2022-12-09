package com.icodify.multitenant.model.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "account_admins")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountAdmins extends BaseEntity{

    @Id
//    @SequenceGenerator(name = "seq_gen", sequenceName = "seq_account_admins", allocationSize = 1, initialValue = 1)
//    @GeneratedValue(generator = "seq_gen")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    private Account account;


    @Column(name = "is_invitation", length = 100)
    private String isInvitation;

    @Column(name = "invited_by_id", length = 100)
    private String invitedById;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "activated_date")
    private Date activatedDate;

    @Column(name = "activated_reason", length = 256, nullable = true)
    private String activatedReason;

}
