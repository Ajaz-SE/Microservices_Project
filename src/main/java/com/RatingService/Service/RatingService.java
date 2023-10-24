package com.RatingService.Service;

import java.util.List;

import com.RatingService.entities.Rating;

public interface RatingService {
	//create Rating
	Rating createRating(Rating rating);
	
	//getAll Rating
	List<Rating> getAllRating();
	
	//Get All ratings by Users
	List<Rating> getRatingByUsers(String userId);
	
	//Get all ratings by Hotel
	List<Rating> getRatingByHotel(String hotelId);
		
	
}
