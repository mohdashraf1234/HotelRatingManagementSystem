package com.user.service.UserService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.UserService.entities.User;
import com.user.service.UserService.services.UserService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;

@RestController
@RequestMapping("/users")
public class UserControllers {
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user) {
	    User savedUser = userService.saveUser(user);
	    return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
	}

	
	//single user get
	@GetMapping("/{userId}")
 //@CircuitBreaker(name = "ratingHotelBreaker", fallbackMethod = "ratingHotelFallback")
  //@Retry(name = "ratingHotelService", fallbackMethod = "ratingHotelFallback")
	 @RateLimiter(name = "userRateLimiter", fallbackMethod = "ratingHotelFallback")
	public ResponseEntity<User> getSingleUser(@PathVariable String userId) {
	    //User user = (User) userService.getUser(userId); 
	    User user = userService.getUser(userId);

	    return ResponseEntity.ok(user);
	}
	
	public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
	    System.out.println("Fallback executed: " + ex.getMessage());
	    
	    User user = new User(
	        "141234",
	        "Dummy",
	        "dummy@gmail.com",
	        "This user is created dummy because some service is down"
	    );
	    //System.out.println("56====>"+user);
	    
	    return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
	}

	
	
	//creating fall back  method for circuitbreaker
//    public ResponseEntity<User> ratingHotelFallback(String userId, Exception ex) {
//        logger.info("Fallback is executed because service is down : ", ex.getMessage());
//
//        ex.printStackTrace();
//
//        //User user = User.builder().email("dummy@gmail.com").name("Dummy").about("This user is created dummy because some service is down").userId("141234").build();
//        User user = User.builder()
//                .email("dummy@gmail.com")
//                .name("Dummy")
//                .about("This user is created dummy because some service is down")
//                .userId("141234")
//                .build();
//        return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
//    }

	


	//all user get
	@GetMapping
	public ResponseEntity<List<User>> getAllUser()
	{
		List<User> allUser=userService.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
	

}
