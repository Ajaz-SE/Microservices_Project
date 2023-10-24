package com.userservice.Microservices.services;

import java.util.List;

import com.userservice.Microservices.entities.User;

public interface UserService {

	//Performing all the operations related to database
	
	//1. Creating user to save in database
	User saveUser(User user);
	
	//2. Getting single User
	User getUser(String id);
	
	//3. Getting all user
	List<User> getAllUser();
	
	//4. Update user
	User updateUser(User user);
	
	//5. Delete user
	void deleteUser(String id);
}
