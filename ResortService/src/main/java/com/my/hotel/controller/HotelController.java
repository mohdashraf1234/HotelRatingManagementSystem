package com.my.hotel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.my.hotel.entities.Hotel;
import com.my.hotel.services.HotelService;


@RestController
@RequestMapping("/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService; // make sure HotelService is implemented and annotated @Service

    // Create a hotel
    @PostMapping
    public ResponseEntity<Hotel> createHotel(@RequestBody Hotel hotel) {
    	Hotel savedHotel = hotelService.create(hotel); // actually save to DB
        return ResponseEntity.status(HttpStatus.CREATED).body(savedHotel);
    }

    // Get a single hotel by ID
    @GetMapping("/{hotelId}")
    public ResponseEntity<Hotel> getHotel(@PathVariable("hotelId") String hotelId
) {
        Hotel hotel = (Hotel)hotelService.get(hotelId); // call service to get
        System.out.println("31====="+hotel+" :inside controller ");
        return ResponseEntity.ok(hotel);
    }
    
 

    


    // Get all hotels
    @GetMapping
    public ResponseEntity<List<Hotel>> getAll() {
        List<Hotel> hotels = hotelService.getAll(); // call service to get all
        return ResponseEntity.ok(hotels);
    }
}
