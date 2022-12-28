package com.example.multitenantbaseservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_at")
    private Date createdAt = new Date();
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;
}
