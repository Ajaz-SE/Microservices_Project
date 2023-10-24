package com.microservice.HotelService.Service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.HotelService.Exception.ResourceNotFoundException;
import com.microservice.HotelService.entities.Hotel;
import com.microservice.HotelService.repository.HotelRepository;

@Service
public class HotelServiceImpl implements HotelService{

	@Autowired
	HotelRepository hotelRepository;
	
	@Override
	public Hotel createHotel(Hotel hotel) {
		String random = UUID.randomUUID().toString();
		hotel.setId(random);
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAllHotel() {
		return hotelRepository.findAll();
	}

	@Override
	public Hotel getSingleHotel(String id) {
		return hotelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Hotel Not found:"+id));
	}

}
