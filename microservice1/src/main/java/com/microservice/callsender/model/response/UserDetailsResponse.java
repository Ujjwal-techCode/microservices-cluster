package com.microservice.callsender.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsResponse {

    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

}
