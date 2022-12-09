package com.icodify.multitenant.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "otp_services")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtpServices extends BaseEntity{
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private Integer otp;

    @Column(name = "otp_generated_date")
    private Date otpGeneratedDate;

    @Column(name = "otp_valid_minutes")
    private int otpValidMinutes;

    @Column(name = "otp_for", length = 80)
    private String otpFor;

    @Column(name = "otp_service_type", length = 50)
    private String otpServiceType;

    @Column(name = "verified_date", nullable = true)
    private Date verifiedDate;


}
