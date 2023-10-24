package com.microservice.HotelService.Service;

import java.util.List;

import com.microservice.HotelService.entities.Hotel;

public interface HotelService {
	
	//Create Hotel
	Hotel createHotel(Hotel hotel);
	
	//Get all Hotel
	List<Hotel> getAllHotel();
	
	//get Single hotel
	Hotel getSingleHotel(String id);
	
}
