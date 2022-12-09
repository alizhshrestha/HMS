package com.icodify.multitenant.model.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Column(name = "created_at")
    private Date createdAt = new Date();
    @Column(name = "updated_at", nullable = true)
    private Date updatedAt;
}
