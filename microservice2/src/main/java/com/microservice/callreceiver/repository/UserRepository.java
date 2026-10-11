package com.microservice.callreceiver.repository;

import com.microservice.callreceiver.model.entity.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer> {

    List<UserDetails> findAllByFirstNameAndLastName(String firstName,String lastName);
}
