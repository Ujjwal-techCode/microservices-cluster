package com.microservice.callreceiver.service.impl;

import com.microservice.callreceiver.model.entity.UserDetails;
import com.microservice.callreceiver.model.request.UserDetailsRequest;
import com.microservice.callreceiver.repository.UserRepository;
import com.microservice.callreceiver.service.UserService;
import com.microservice.callreceiver.util.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    Utils utils;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails createUser(UserDetailsRequest userDetailsRequest) {
        UserDetails userDetails = new UserDetails(userDetailsRequest.getFirstName(), userDetailsRequest.getLastName(),userDetailsRequest.getEmail(),userDetailsRequest.getPhone());
        UserDetails savedUserDetails = userRepository.save(userDetails);
        return savedUserDetails;
    }

    @Override
    public List<UserDetails> getAllUser(){
        return userRepository.findAll();
    }

    @Override
    public UserDetails getParticularUser(int userId){
        UserDetails foundUserDetails = null;
        Optional<UserDetails> optionalUserDetails = userRepository.findById(userId);
        if(optionalUserDetails.isPresent()){
            foundUserDetails = optionalUserDetails.get();
        }
        return foundUserDetails;
    }

    @Override
    public UserDetails patchFields(int userId, Map<String,String> userFields){
        UserDetails existingUser = null;
        Optional<UserDetails> existingUserOptional =  userRepository.findById(userId);
        if(existingUserOptional.isPresent()){
            existingUser = existingUserOptional.get();
            Set<String> keySet = userFields.keySet();
            for(String key : keySet){
                if(key.equals("firstName")) existingUser.setFirstName(userFields.get(key));
                if(key.equals("lastName")) existingUser.setLastName(userFields.get(key));
                if(key.equals("email")) existingUser.setEmail(userFields.get(key));
                if(key.equals("phone")) existingUser.setPhone(userFields.get(key));
            }
            existingUser =  userRepository.save(existingUser);
        }
        return existingUser;
    }

    @Override
    public UserDetails updateUser(UserDetails userDetails) {
        UserDetails result = null;
        List<UserDetails> userDetailsList = userRepository.findAllByFirstNameAndLastName(userDetails.getFirstName(), userDetails.getLastName());
        if(userDetailsList != null && userDetailsList.size() == 1){
            UserDetails existingUser = userDetailsList.get(0);
            existingUser.setFirstName(userDetails.getFirstName());
            existingUser.setLastName(userDetails.getLastName());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPhone(userDetails.getPhone());
            result = userRepository.save(existingUser);
        }
        return result;
    }

    @Override
    public UserDetails deleteUser(String firstName, String lastName){
        UserDetails deletedUser = null;
        List<UserDetails> userDetailsList = userRepository.findAllByFirstNameAndLastName(firstName,lastName);
        if(userDetailsList != null && userDetailsList.size() == 1){
            userRepository.deleteById(userDetailsList.get(0).getUserId());
            deletedUser = userDetailsList.get(0);
        }
        return deletedUser;
    }
}
