package com.rating.RatingService.services;

import java.util.List;

import com.rating.RatingService.entities.Rating;

public interface RatingService {
	//create 
	Rating create(Rating rating);
	//get all ratings
	List<Rating> getRatings();
	//get all by userid
	List<Rating> getRatingByUserId(String userId);
	//get all by user  hotel
	List<Rating> getRatingByHotelId(String hotelId);

	

}
