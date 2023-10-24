package com.RatingService.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RatingService.Repository.RatingRepository;
import com.RatingService.entities.Rating;

@Service
public class RatingServiceImpl implements RatingService{
	
	@Autowired
	RatingRepository ratingRepository;

	@Override
	public Rating createRating(Rating rating) {
		String random = UUID.randomUUID().toString();
		rating.setRatingId(random);
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getAllRating() {
		return ratingRepository.findAll();
	}

	@Override
	public List<Rating> getRatingByUsers(String userid) {
		return ratingRepository.findByUserId(userid);
	}
	

	@Override
	public List<Rating> getRatingByHotel(String hotelid) {
		return ratingRepository.findByHotelId(hotelid);
	}




}
