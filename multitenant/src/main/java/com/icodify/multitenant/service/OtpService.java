package com.icodify.multitenant.service;

import com.icodify.multitenant.model.dto.request.OtpRequestDto;
import com.icodify.multitenant.model.dto.response.OtpResponseDto;

public interface OtpService {

    OtpResponseDto generateOtp(OtpRequestDto otpRequestDto);

    boolean validateOtp(int otp, String email);

    void clearOtp(String otpOf);
}
