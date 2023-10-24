package com.RatingService.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RatingService.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating, String>{
	
	//Custom Finder methods
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);

}
