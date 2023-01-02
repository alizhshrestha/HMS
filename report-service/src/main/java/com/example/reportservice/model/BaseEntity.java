package com.example.reportservice.model;

import lombok.Data;

import java.util.Date;

@Data
public class BaseEntity {

    private Date createdAt = new Date();
    private Date updatedAt;
}
