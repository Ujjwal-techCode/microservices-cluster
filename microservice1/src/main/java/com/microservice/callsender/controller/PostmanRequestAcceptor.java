package com.microservice.callsender.controller;

import com.microservice.callsender.client.UserClient;
import com.microservice.callsender.model.request.UserDetailsRequest;
import com.microservice.callsender.model.response.UserDetailsResponse;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/postman")
public class PostmanRequestAcceptor {

    @Autowired
    UserClient userClient;

    @PostMapping("/saveUser")
    @Retryable(value = {FeignException.class}, maxAttempts = 3, backoff = @Backoff(delay = 2000))
    public ResponseEntity<UserDetailsResponse> saveUserDetails(@RequestBody UserDetailsRequest userDetailsRequest){
        System.out.println("Retry attempt made");
        UserDetailsResponse response = userClient.saveUser(userDetailsRequest);
        return new ResponseEntity<UserDetailsResponse>(response, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDetailsResponse>> getAllUserDetails(){
        List<UserDetailsResponse> allUsersDetails = userClient.getAllUserFromDB();
        return new ResponseEntity<List<UserDetailsResponse>>(allUsersDetails, new HttpHeaders(), HttpStatus.FOUND);
    }

}