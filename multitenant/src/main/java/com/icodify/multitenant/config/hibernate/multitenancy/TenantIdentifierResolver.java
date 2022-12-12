package com.icodify.multitenant.config.hibernate.multitenancy;

import com.icodify.multitenant.config.multitenancy.context.TenantContext;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import java.util.Optional;

public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    @Override
    public String resolveCurrentTenantIdentifier() {
        return Optional.ofNullable(TenantContext.getCurrentTenant())
                .orElse("tenant1");
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
