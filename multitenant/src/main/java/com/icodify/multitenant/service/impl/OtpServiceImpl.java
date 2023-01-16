package com.icodify.multitenant.service.impl;

import com.icodify.multitenant.exception.ResourceNotFoundException;
import com.icodify.multitenant.model.dto.request.OtpRequestDto;
import com.icodify.multitenant.model.dto.response.OtpResponseDto;
import com.icodify.multitenant.model.entities.Otp;
import com.icodify.multitenant.repository.OtpRepository;
import com.icodify.multitenant.service.OtpService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.Date;
import java.util.Random;


@Service
public class OtpServiceImpl implements OtpService {

    private final OtpRepository otpRepository;
    private final ModelMapper modelMapper;
    private Random r;

    public OtpServiceImpl(OtpRepository otpRepository,
                          ModelMapper modelMapper) throws NoSuchAlgorithmException {
        this.otpRepository = otpRepository;
        this.modelMapper = modelMapper;
        r = SecureRandom.getInstanceStrong();
    }

    @Override
    public OtpResponseDto generateOtp(OtpRequestDto otpReqDto) {
        r = new Random();
        int randomOtp =(r.nextInt(9000))+1000;

        Otp otp = new Otp();
        otp.setOtp(randomOtp);
        otp.setOtpGeneratedDate(new Date());
        otp.setOtpValidMinutes(5);
        otp.setOtpFor(otpReqDto.getOtpFor());
        otp.setOtpServiceType(otpReqDto.getOtpServiceType());
        otp.setVerifiedDate(null);

        otpRepository.save(otp);

        return this.modelMapper.map(otp, OtpResponseDto.class);
    }

    @Override
    public boolean validateOtp(int otpNumber, String email) {
        boolean isExpired = false;

        Otp otp = otpRepository.findByOtpFor(email).orElseThrow(() -> new ResourceNotFoundException("Otp", "Email: " + email));

        Instant currentDate = Instant.now();
        Instant expiryDate = otp.getOtpGeneratedDate().toInstant().plusMillis((long) otp.getOtpValidMinutes() * 60 * 1000);

        if(expiryDate.isBefore(currentDate)) {
            isExpired = true;
        }

        if(otpNumber==otp.getOtp() && !isExpired){
            return true;
        }
        return false;
    }

    @Override
    public void clearOtp(String email) {
        Otp otp = otpRepository.findByOtpFor(email).orElseThrow(() -> new ResourceNotFoundException("Otp", "Email: " + email));
        otpRepository.delete(otp);
    }


}
