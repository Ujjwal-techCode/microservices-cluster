package com.microservice.callreceiver.controller;

import com.microservice.callreceiver.config.UserDefinedException;
import com.microservice.callreceiver.model.entity.UserDetails;
import com.microservice.callreceiver.model.request.UserDetailsRequest;
import com.microservice.callreceiver.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/createUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<UserDetails> createUser(@Valid @RequestBody UserDetailsRequest userDetailsRequest){
        UserDetails userDetails = userService.createUser(userDetailsRequest);
        return new ResponseEntity<UserDetails>(userDetails, new HttpHeaders(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDetails>> getAllUsers(){
        List<UserDetails> userDetailsList = userService.getAllUser();
        return new ResponseEntity<List<UserDetails>> (userDetailsList, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDetails> getParticularUser(@PathVariable("userId") int userId){
        UserDetails userDetails = userService.getParticularUser(userId);
        return new ResponseEntity<UserDetails>(userDetails, new HttpHeaders(), HttpStatus.FOUND);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDetails> updateUserFields(@PathVariable("userId") int userId, @RequestBody Map<String, String> userFields){
        UserDetails patchedUserDetails = userService.patchFields(userId, userFields);
        return new ResponseEntity<UserDetails>(patchedUserDetails, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateUser")
    public ResponseEntity<UserDetails> updateUser(@RequestBody UserDetails userDetails){
        UserDetails updatedUserDetails = userService.updateUser(userDetails);
        return new ResponseEntity<UserDetails>(updatedUserDetails, new HttpHeaders(), HttpStatus.FOUND);
    }

    @DeleteMapping
    public ResponseEntity<UserDetails> deleteUser(@RequestParam String firstName, @RequestParam String lastName) {
        UserDetails deletedUser = userService.deleteUser(firstName, lastName);
        return new ResponseEntity<UserDetails>(deletedUser, new HttpHeaders(), HttpStatus.GONE);
    }

    @GetMapping("/exception1")
    public void showException1 (){
        throw new UserDefinedException("Application could not parse the input");
    }

    @DeleteMapping("/exception2")
    public void showException2(){
        throw new NullPointerException("null Encountered");
    }
}
