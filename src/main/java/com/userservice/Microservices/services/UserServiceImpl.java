package com.userservice.Microservices.services;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.userservice.Microservices.Repository.UserRepository;
import com.userservice.Microservices.entities.Hotel;
import com.userservice.Microservices.entities.Rating;
import com.userservice.Microservices.entities.User;
import com.userservice.Microservices.external.services.HotelService;
import com.userservice.Microservices.services.exceptions.ResourceNotFoundException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private HotelService hotelService;

	/*
	 * @Autowired private Logger logger =
	 * LoggerFactory.getLogger(UserService.class);
	 */

	@Override
	public User saveUser(User user) {
		String random = UUID.randomUUID().toString();
		user.setId(random);
		return userRepository.save(user);
	}

	@Override
	public User getUser(String id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User is not found on the server:" + id));

		@SuppressWarnings("unchecked")
		// Calling the Rating service which includes hotel and user
		Rating[] ratingOfUser = restTemplate.getForObject("http://RATING-SERVICE/ratings/users/" + user.getId(),
				Rating[].class);

		List<Rating> ratings = Arrays.stream(ratingOfUser).toList();

		// System.out.println(forObject);
		// logger.info("{}",ratingOfUser);
		
		//Calling Hotel service
		List<Rating> ratingList = ratings.stream().map(rating -> {

			// api call to hotel service to get the hotel
			// http://localhost:8082/hotel/f8676c59-e675-485d-9a3d-d41e8446d533

			//ResponseEntity<Hotel> forEntity = restTemplate
					//.getForEntity("http://HOTEL-SERVICE/hotel/" + rating.getHotelId(), Hotel.class);
			
			Hotel hotel = hotelService.getHotel(rating.getHotelId());

			// set the hotel to rating
			rating.setHotel(hotel);

			// return the rating
			return rating;

		}).collect(Collectors.toList());

		user.setRatings(ratingList);
		return user;
	}

	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(String id) {
		userRepository.deleteById(id);

	}

}
