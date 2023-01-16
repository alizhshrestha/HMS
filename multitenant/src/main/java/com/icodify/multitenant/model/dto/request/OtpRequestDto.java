package com.icodify.multitenant.model.dto.request;

import com.icodify.multitenant.model.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtpRequestDto{

//    private UUID id;
//
//    private Integer otp;
//
//    private Date otpGeneratedDate;
//
//    private int otpValidMinutes;

    private String otpFor;

    private String otpServiceType;

//    private Date verifiedDate;

}
