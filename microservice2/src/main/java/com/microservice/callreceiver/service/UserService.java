package com.microservice.callreceiver.service;

import com.microservice.callreceiver.model.entity.UserDetails;
import com.microservice.callreceiver.model.request.UserDetailsRequest;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserDetails createUser(UserDetailsRequest userDetailsRequest);

    List<UserDetails> getAllUser();

    UserDetails getParticularUser(int userId);

    UserDetails patchFields(int userId, Map<String, String> userFields);

    UserDetails updateUser(UserDetails userDetails);

    UserDetails deleteUser(String firstName, String lastName);
}
