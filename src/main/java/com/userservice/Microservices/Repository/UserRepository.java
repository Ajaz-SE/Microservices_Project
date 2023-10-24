package com.userservice.Microservices.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userservice.Microservices.entities.User;

public interface UserRepository extends JpaRepository<User, String>  {

}
