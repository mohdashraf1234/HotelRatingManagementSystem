package com.my.hotel.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.my.hotel.entities.Hotel;


public interface HotelService {
	
	//create 
	Hotel  create(Hotel hotel);
	//getall
	List<Hotel> getAll();
	//get single
	Hotel get(String id);

}
