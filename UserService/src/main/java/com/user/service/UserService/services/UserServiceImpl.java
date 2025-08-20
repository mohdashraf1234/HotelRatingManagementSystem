package com.user.service.UserService.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.UserService.entities.Hotel;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.exception.ResourceNotFoundException;
import com.user.service.UserService.external.services.HotelService;
import com.user.service.UserService.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private HotelService hotelService;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    
    @Override
    public User getUser(String userId) {
        // Fetch user from DB
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with given id is not found on server !! : " + userId));

        // Fetch ratings from Rating Service
        Rating[] ratingsArray = restTemplate.getForObject(
                "http://RATINGSERVICE/ratings/users/" + user.getUserId(),
                Rating[].class
        );

        List<Rating> ratings = Arrays.asList(ratingsArray);

        // Fetch hotel details for each rating
        List<Rating> ratingList = ratings.stream().map(rating -> {
            ResponseEntity<Hotel> forEntity =
                    restTemplate.getForEntity("http://RESORTSERVICE/hotels/" + rating.getHotelId(),
                            Hotel.class);

            Hotel hotel = forEntity.getBody();
            logger.info("Response status code : {}", forEntity.getStatusCode());

            rating.setHotel(hotel);
            return rating;
        }).collect(Collectors.toList());

        user.setRating(ratingList);

        return user;
    }

    // Get single user
//    @Override
//    public User getUser(String userId) {
//        // Fetch user from DB
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException(
//                        "User with given id is not found on server !! : " + userId));
//
//        // Fetch ratings from Rating Service
//        Rating[] ratingsArray = restTemplate.getForObject(
//                "http://RATINGSERVICE/ratings/users/" + user.getUserId(),
//                Rating[].class
//        );
//
//        List<Rating> ratings = Arrays.asList(ratingsArray);
//
//        // Fetch hotel details for each rating
//        List<Rating> ratingList = ratings.stream().map(rating -> {
////            ResponseEntity<Hotel> forEntity =
////                    restTemplate.getForEntity("http://RESORTSERVICE/hotels/" + rating.getHotelId(),
////                            Hotel.class);
//
//            Hotel hotel = hotelService.getHotel(rating.getHotelId());
//           // logger.info("Response status code : {}", forEntity.getStatusCode());
//
//            rating.setHotel(hotel);
//            return rating;
//        }).collect(Collectors.toList());
//
//        user.setRating(ratingList);
//
//        return user;
//    }
    
    
}



/*package com.user.service.UserService.services;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import com.user.service.UserService.entities.Hotel;
import com.user.service.UserService.entities.Rating;
import com.user.service.UserService.entities.User;
import com.user.service.UserService.exception.ResourceNotFoundException;
import com.user.service.UserService.repositories.UserRepository;


@Service
public class UserServiceImpl implements UserService {
	
	

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private RestTemplate restTemplate;
	   
	    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	    @Override
	    public User saveUser(User user) {
	        return userRepository.save(user);
	    }

	    @Override
	    public List<User> getAllUser() {
	    	//implement 
	        return userRepository.findAll();
	    }
	    
	     
//       //get singleuser
//	    @Override
//	    public User getUser(String userId) {
//	        User user = userRepository.findById(userId)
//	                .orElseThrow(() -> new ResourceNotFoundException(
//	                        "User with given id is not found on server !! : " + userId));
//
//	        // Fetch ratings from Rating Service
//	        Rating[] ratingsArray = restTemplate.getForObject(
//	                "http://RATINGSERVICE/ratings/users/" + user.getUserId(),
//	                Rating[].class
//	        );
//	        
//
//	        List<Rating> ratings = Arrays.asList(ratingsArray);
//
//	        List<Rating> ratingList = ratings.stream().map(rating -> {
//	            ResponseEntity<Hotel> forEntity =
//	                    restTemplate.getForEntity("http://localhost:9097/hotels/" + rating.getHotelId(),
//	                            Hotel.class);
//
//	            Hotel hotel = forEntity.getBody();
//	            logger.info("Response status code :{}", forEntity.getStatusCode());
//
//	            rating.setHotel(hotel);
//	            return rating;
//	        }).collect(Collectors.toList());
//
//	        user.setRating(ratingList);
//
//	        return user;
//	    }

}*/


/*@Override
public User getUser(String userId) {
    // Fetch user from DB
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException(
                    "User with given id is not found on server !! : " + userId));

    // Fetch ratings from Rating Service
    Rating[] ratingsArray = restTemplate.getForObject(
            "http://RATINGSERVICE/ratings/users/" + user.getUserId(),
            Rating[].class
    );

    List<Rating> ratings = Arrays.asList(ratingsArray);

    // Fetch hotel details for each rating
    List<Rating> ratingList = ratings.stream().map(rating -> {
        ResponseEntity<Hotel> forEntity =
                restTemplate.getForEntity("http://RESORTSERVICE/hotels/" + rating.getHotelId(),
                        Hotel.class);

        Hotel hotel = forEntity.getBody();
        logger.info("Response status code : {}", forEntity.getStatusCode());

        rating.setHotel(hotel);
        return rating;
    }).collect(Collectors.toList());

    user.setRating(ratingList);

    return user;
}
}*/
