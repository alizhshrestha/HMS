package com.icodify.multitenant.model.dto.response;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BaseResponse {
    private HttpStatus status;
    private String message;
    private Object body;
}
