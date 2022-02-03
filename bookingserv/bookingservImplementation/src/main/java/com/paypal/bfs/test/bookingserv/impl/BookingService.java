package com.paypal.bfs.test.bookingserv.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.model.Address;
import com.paypal.bfs.test.bookingserv.model.Bookings;
import com.paypal.bfs.test.bookingserv.repository.BookingsRepository;




@Service
public class BookingService {
	
	@Autowired
	BookingsRepository bookingsRepository;
	
	public boolean checkOrderForBooking(Booking booking) {
		if(booking.getId() != null) {
		 if(bookingsRepository.findByBookingId(booking.getId()) != null)
			 return true;
		 else
			 return false;
		}
		return false;
	}
	

	
	public Booking create(Booking booking) {
		
		Bookings bookingsEntity= new Bookings();
	
		bookingsEntity.setBookingId(booking.getId());
		bookingsEntity.setCheckinDatetime(booking.getCheckinDatetime());
		bookingsEntity.setCheckoutDatetime(booking.getCheckoutDatetime());
		bookingsEntity.setDateOfBirth(booking.getDateOfBirth());
		bookingsEntity.setFirstName(booking.getFirstName());
		bookingsEntity.setLastName(booking.getLastName());
		bookingsEntity.setDeposit(booking.getDeposit());
		bookingsEntity.setTotalprice(booking.getTotalprice());
		Address addressEntity= new Address();
		addressEntity.setCity(booking.getAddress().getCity());
		addressEntity.setLine1(booking.getAddress().getLine1());
		addressEntity.setLine2(booking.getAddress().getLine2());
		addressEntity.setState(booking.getAddress().getState());
		addressEntity.setZipCode(booking.getAddress().getZipCode());
		bookingsEntity.setAddress(addressEntity);
		Bookings bookings= bookingsRepository.save(bookingsEntity);
		return booking;
	}
	
	public int UnqiueBookingIdGenerator() {
		
		Bookings booking= bookingsRepository.findFirstByOrderByIdDesc();
	
		System.out.println(booking);
		if(booking == null) {
			System.out.println("I m inside Null");
			return 1;
		}
		int id= booking.getBookingId();
		System.out.println("id: "+id);
		id= id+1;
		return id;
	}

	public List<Booking> getBookings() {

		List<Bookings> bookings= bookingsRepository.findAll();
		List<Booking> bookingListFromDB= new ArrayList<Booking>();
		for(int i= 0;i<bookings.size();i++) {
			Booking bookingsEntity= new Booking();
			bookingsEntity.setId(bookings.get(i).getBookingId());
			bookingsEntity.setCheckinDatetime(bookings.get(i).getCheckinDatetime());
			bookingsEntity.setCheckoutDatetime(bookings.get(i).getCheckoutDatetime());
			bookingsEntity.setDateOfBirth(bookings.get(i).getDateOfBirth());
			bookingsEntity.setFirstName(bookings.get(i).getFirstName());
			bookingsEntity.setLastName(bookings.get(i).getLastName());
			bookingsEntity.setDeposit(bookings.get(i).getDeposit());
			bookingsEntity.setTotalprice(bookings.get(i).getTotalprice());
			com.paypal.bfs.test.bookingserv.api.model.Address addressEntity= new com.paypal.bfs.test.bookingserv.api.model.Address();
			addressEntity.setCity(bookings.get(i).getAddress().getCity());
			addressEntity.setLine1(bookings.get(i).getAddress().getLine1());
			addressEntity.setLine2(bookings.get(i).getAddress().getLine2());
			addressEntity.setState(bookings.get(i).getAddress().getState());
			addressEntity.setZipCode(bookings.get(i).getAddress().getZipCode());
			bookingsEntity.setAddress(addressEntity);
			bookingListFromDB.add(bookingsEntity);
		}
		return bookingListFromDB;
	}

}
