package com.microservice.HotelService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.HotelService.entities.Hotel;

public interface HotelRepository extends JpaRepository<Hotel, String>{

}
