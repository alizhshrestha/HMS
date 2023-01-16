package com.example.emailservice.controller;

import com.example.emailservice.service.EmailService;
import com.example.emailservice.model.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody Email email){
        emailService.sendSimpleMessage(email);
//        return ResponseEntity.ok("Email Sent Successfully to: " + email.getTo());
        return "Email Sent Successfully to: " + email.getTo();
    }
}
