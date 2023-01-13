package com.example.emailservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Email {

    private String to;
    private String subject;
    private String text;
}
