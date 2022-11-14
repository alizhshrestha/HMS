package com.icodify.multitenant.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "account_admins")
@SequenceGenerator(name = "seq_gen", sequenceName = "seq_account_admins", allocationSize = 1, initialValue = 1)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountAdmins extends BaseEntity{

    @Id
    @GeneratedValue(generator = "seq_gen")
    protected Integer id;

    private UUID uuid;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "is_invitation")
    private String isInvitation;

    @Column(name = "invited_by_id")
    private String invitedById;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "activated_date")
    private Date activatedDate;

    @Column(name = "activated_reason")
    private String activatedReason;

}
