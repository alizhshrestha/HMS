package com.icodify.multitenant.controller;

import com.icodify.multitenant.model.dto.request.Email;
import com.icodify.multitenant.model.dto.request.OtpRequestDto;
import com.icodify.multitenant.model.dto.response.BaseResponse;
import com.icodify.multitenant.model.dto.response.OtpResponseDto;
import com.icodify.multitenant.service.OtpService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    private final OtpService otpService;
    private final WebClient webClient;

    public OtpController(OtpService otpService,
                         WebClient.Builder webClientBuilder) {
        this.otpService = otpService;
        this.webClient = webClientBuilder.baseUrl("http://localhost:8093/api/email").build();
    }

    @PostMapping("/generate")
    public ResponseEntity<?> generateOtp(@RequestBody OtpRequestDto otpRequestDto) {

        OtpResponseDto otpResponseDto = otpService.generateOtp(otpRequestDto);

        Email email = Email.builder()
                .to(otpRequestDto.getOtpFor())
                .subject("OTP Pin")
                .text("Please use this OTP pin " +
                        otpResponseDto.getOtp() +
                        " to reset password that expires after " +
                        otpResponseDto.getOtpValidMinutes() + " minutes")
                .build();

        String responseToMono = this.webClient.post()
                .uri("/send")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(email), Email.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return ResponseEntity.status(HttpStatus.OK).body(responseToMono);
    }

    @GetMapping("/validate")
    public ResponseEntity<BaseResponse> validateOtp(@RequestParam("otp") int otp, @RequestParam("email") String email) {
        boolean isValid = otpService.validateOtp(otp, email);
        if (isValid) {
            return ResponseEntity.ok(BaseResponse.builder()
                    .status(HttpStatus.OK)
                    .message("Successfully Verified")
                    .build());
        }else{
            return ResponseEntity.ok(BaseResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Please try again")
                    .build());
        }

    }

}
