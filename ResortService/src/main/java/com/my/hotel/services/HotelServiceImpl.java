package com.my.hotel.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.hotel.entities.Hotel;
import com.my.hotel.exception.ResourceNotFoundException;
import com.my.hotel.repositories.HotelRepository;


@Service
public class HotelServiceImpl implements HotelService {
	@Autowired
	private HotelRepository hotelRepository;

	
	public HotelServiceImpl(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

	@Override
	public Hotel create(Hotel hotel) {
		// TODO Auto-generated method stub
		return hotelRepository.save(hotel);
	}

	@Override
	public List<Hotel> getAll() {
		// TODO Auto-generated method stub
		return hotelRepository.findAll();
	}

//	@Override
//	public Hotel get(String id) {
//		// TODO Auto-generated method stub
//	return hotelRepository.findById(id)
//	            .orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + id));;
//	}
	
	@Override
	public Hotel get(String id) {
		System.out.println("43========get single data");
	     Hotel hotel = hotelRepository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("User with given id is not found on server !! : " + id));
	     return hotel;
	}
	
	
	
	
	

}
