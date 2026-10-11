package com.microservice.callsender.client;

import com.microservice.callsender.model.request.UserDetailsRequest;
import com.microservice.callsender.model.response.UserDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/*@FeignClient( name = "MICROSERVICE2", url = "${receiver.api.url}")*/
@FeignClient(name = "MICROSERVICE2")
public interface UserClient {

    @PostMapping(value = "/users/createUser", produces = MediaType.APPLICATION_XML_VALUE)
    UserDetailsResponse saveUser(@RequestBody UserDetailsRequest userDetailsRequest);

    @GetMapping("/users")
    List<UserDetailsResponse> getAllUserFromDB();



}
