package com.user.service.UserService.services;

import java.util.List;

import com.user.service.UserService.entities.User;

public interface UserService {
	//user operations
	//create
	User saveUser(User user);
	
	//get all  user
	List<User> getAllUser();
	
	//get single user
	
	User getUser(String userId);
	
	//todo:  delete 
	//todo : update -assignment
	
	

}
