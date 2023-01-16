package com.icodify.multitenant.model.dto.request;

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
