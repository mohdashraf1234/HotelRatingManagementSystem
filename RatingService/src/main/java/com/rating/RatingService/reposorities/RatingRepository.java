package com.rating.RatingService.reposorities;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rating.RatingService.entities.Rating;

public interface RatingRepository extends JpaRepository<Rating,String>{
	//when u workwith RDBM then u can use JpaRepository however, when u use mongoDB 
	//or @Document then u should use MongoRepositore intead  of  JpaRepository
	
	//custom finder method
	List<Rating> findByUserId(String userId);
	List<Rating> findByHotelId(String hotelId);
	
	

}
