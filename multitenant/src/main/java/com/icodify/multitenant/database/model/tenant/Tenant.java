package com.icodify.multitenant.database.model.tenant;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "public")
@Data
public class Tenant {

    @Id
    private String tenantId;
    private String schemaName;
}
