package com.userservice.Microservices.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userservice.Microservices.entities.User;
import com.userservice.Microservices.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
@RequestMapping("/users")
public class UserController {
	

	

	@Autowired
	private UserService userService;
	
	//Create User
	@PostMapping("/")
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1 = userService.saveUser(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
		
	}
	
	//get single User
	int retryCount = 1;
	@GetMapping("/{id}")
	//@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
	//@Retry(name = "ratingHotelService" , fallbackMethod = "ratingHotelFallback")
	@RateLimiter(name="userRateLimiter" , fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String id){
		System.out.println("Get single User Handler: UserController");
		System.out.println("RetryCount: {}"+ retryCount);
		retryCount++;
		User user = userService.getUser(id);
		return ResponseEntity.ok(user);
	}
	
	//creating fallback method for circuit breaker
	public ResponseEntity<User> ratingHotelFallback(String id, Exception ex){
		System.out.println("Fallback method is executed because service is down: "+ex.getMessage());
		User user = User.builder()
				.email("dummyemail@gmail.com")
				.name("Dummy")
				.about("This is Dummy data due to down service")
				.id("E638683")
				.build();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	
	//getAllUser
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUser(){
		return ResponseEntity.ok(userService.getAllUser());
		
	}

}
