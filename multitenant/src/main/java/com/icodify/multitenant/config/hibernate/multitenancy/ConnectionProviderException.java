package com.icodify.multitenant.config.hibernate.multitenancy;

public class ConnectionProviderException extends RuntimeException{

    public ConnectionProviderException(String message){
        super(message);
    }
}
