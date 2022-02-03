package com.paypal.bfs.test.bookingserv;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.paypal.bfs.test.bookingserv.api.model.Booking;
import com.paypal.bfs.test.bookingserv.impl.BookingService;
import com.paypal.bfs.test.bookingserv.repository.*;
import com.paypal.bfs.test.bookingserv.model.*;
import com.paypal.bfs.test.bookingserv.repository.BookingsRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertEquals;
@ExtendWith(MockitoExtension.class)
public class BookingServiceTest {

	@Spy
	@InjectMocks
	private BookingService bookingService;
	
	@Mock
	private BookingsRepository bookingsRepository;
	
	 @Before
	    public void setUp() {
	        MockitoAnnotations.initMocks(this);    

	    }
	 @Test
	 public void TestCheckForBookingsWhenBookingIdIsNotNull() {
		 Booking booking= getBooking();
		 Bookings bookingsEntity= getBookings();
		 booking.setId(1);
		 when(bookingsRepository.findByBookingId(booking.getId())).thenReturn(bookingsEntity);
		 boolean validateResult= bookingService.checkOrderForBooking(booking);
		 assertEquals(true, validateResult);
	 }

	 @Test
	 public void TestCheckForBookingsWhenBookingIdIsNull() {
		 Booking booking= getBooking();
		 Bookings bookingsEntity= getBookings();
		 boolean validateResult= bookingService.checkOrderForBooking(booking);
		 assertEquals(false, validateResult);
	 }
	 
	 @Test
	 public void TestCheckForBookingsWhenBookingEntityIsNullFromDB() {
		 Booking booking= getBooking();
		 Bookings bookingsEntity= getBookings();
		 booking.setId(0);
		 when(bookingsRepository.findByBookingId(booking.getId())).thenReturn(null);
		 boolean validateResult= bookingService.checkOrderForBooking(booking);
		 assertEquals(false, validateResult);
	 }
	 
	@org.junit.Test
	public void TestCreateBookings() {
		Booking expectedbooking= getBooking();
		Bookings bookings= getBookings();
		when(bookingsRepository.findFirstByOrderByIdDesc()).thenReturn(bookings);
        when(bookingsRepository.save(Mockito.any())).thenReturn(bookings);
        Booking returnedBooking=  bookingService.create(expectedbooking);
        assertEquals(expectedbooking, returnedBooking);
	}
	
	@Test
	public void TestGetAllBookings() {
		
		List<Bookings> bookingsEntityList= new ArrayList<>();
		bookingsEntityList.add(getBookings());
		bookingsEntityList.add(getBookings());
		when(bookingsRepository.findAll()).thenReturn(bookingsEntityList);
		
		List<Booking> expectedBookingsList= new ArrayList<>();
		Booking booking1= getBooking();
		booking1.setId(1);
		Booking booking2= getBooking();
		booking2.setId(1);
		expectedBookingsList.add(booking1);
		expectedBookingsList.add(booking2);
		
		List<Booking> resultantBookingsList= bookingService.getBookings();
		assertEquals(expectedBookingsList.get(0).getId(),resultantBookingsList.get(0).getId());
		
	}
	
	private Booking getBooking() {
		Booking booking= new Booking();
		booking.setCheckinDatetime("checkInDateTime");
		booking.setCheckoutDatetime("checkoutDatetime");
		booking.setDateOfBirth("dateOfBirth");
		booking.setDeposit(12.9);
		booking.setFirstName("firstName");
		booking.setLastName("lastName");
		booking.setTotalprice(12.1);
		com.paypal.bfs.test.bookingserv.api.model.Address address= new com.paypal.bfs.test.bookingserv.api.model.Address();
		address.setCity("city");
		address.setLine1("line1");
		address.setLine2("line2");
		address.setState("state");
		address.setZipCode("zipcode");
		booking.setAddress(address);
		return booking;
		
	}

	public Bookings getBookings() {
		Bookings booking= new Bookings();
		booking.setCheckinDatetime("checkInDateTime");
		booking.setCheckoutDatetime("checkoutDatetime");
		booking.setDateOfBirth("dateOfBirth");
		booking.setDeposit(12.9);
		booking.setFirstName("firstName");
		booking.setLastName("lastName");
		booking.setTotalprice(12.1);
		booking.setBookingId(1);
		Address address= new Address();
		address.setCity("city");
		address.setLine1("line1");
		address.setLine2("line2");
		address.setState("state");
		address.setZipCode("zipcode");
		booking.setAddress(address);
		
		return booking;
	}
}
