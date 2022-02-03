package com.paypal.bfs.test.bookingserv.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paypal.bfs.test.bookingserv.model.Bookings;

@Repository
public interface BookingsRepository extends JpaRepository<Bookings,Long>{

	Bookings findFirstByOrderByIdDesc();

	@Query("SELECT booking FROM Bookings booking WHERE booking.bookingId=?1")
	Bookings findByBookingId(Integer id);
	
	
}
