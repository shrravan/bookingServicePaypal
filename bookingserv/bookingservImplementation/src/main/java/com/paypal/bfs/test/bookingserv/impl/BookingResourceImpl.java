package com.paypal.bfs.test.bookingserv.impl;

import com.paypal.bfs.test.bookingserv.api.BookingResource;

import com.paypal.bfs.test.bookingserv.api.model.Booking;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingResourceImpl implements BookingResource {

	@Autowired
	BookingService bookingService;

    @Override
    public @ResponseBody
    ResponseEntity create(Booking booking) {
    	if(bookingService.checkOrderForBooking(booking))
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This order with id: "+booking.getId()+" exists");	
    	if(booking.getId() == null ||booking.getFirstName() == null || booking.getLastName() == null || booking.getAddress().getCity() == null || booking.getAddress().getLine1() == null || booking.getAddress().getState() == null || booking.getAddress().getZipCode() == null)
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mandaotyr fields like id, FirstName or LastName or Address detalis are missed in the request");	
    	else
    		return new ResponseEntity<Booking>(bookingService.create(booking),HttpStatus.OK);
    }

	@Override
	public ResponseEntity<List<Booking>> getBookings() {
		return new ResponseEntity<List<Booking>>(bookingService.getBookings(),HttpStatus.OK);
	}
}
