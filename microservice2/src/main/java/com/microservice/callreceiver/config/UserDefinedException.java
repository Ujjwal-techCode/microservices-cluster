package com.microservice.callreceiver.config;

public class UserDefinedException extends RuntimeException{

    public UserDefinedException(String message) {
        super(message);
    }
}
