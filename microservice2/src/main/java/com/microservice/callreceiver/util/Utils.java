package com.microservice.callreceiver.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Utils {

    public UUID generateUUID(){
        return UUID.randomUUID();
    }
}
