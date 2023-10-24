package com.userservice.Microservices.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.userservice.Microservices.entities.Rating;

@FeignClient(name = "RATING-SERVICE")
public interface RatingService {

	//Get
	
	
	//Post
	@PostMapping("/ratings")
	public Rating createRating(Rating values);
	
	//Put
	@PutMapping("/ratings/{ratingId}")
	public Rating updateRating(@PathVariable("ratingId") String ratingId, Rating rating);
	
	//Delete
	@DeleteMapping("/ratings/{ratingId}")
	public void deleteRating(@PathVariable String ratingId);
}
