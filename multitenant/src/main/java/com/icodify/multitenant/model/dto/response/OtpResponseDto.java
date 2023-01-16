package com.icodify.multitenant.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OtpResponseDto {

    private UUID id;

    private Integer otp;

    private Date otpGeneratedDate;

    private int otpValidMinutes;

    private String otpFor;

    private String otpServiceType;

    private Date verifiedDate;

}
