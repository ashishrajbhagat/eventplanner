package com.tadigital.eventplanner.booking.service;

import com.tadigital.eventplanner.booking.dao.BookingDao;
import com.tadigital.eventplanner.booking.entity.Booking;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookingService {
	private BookingDao bookingDao;
	
	@Autowired
	public BookingService(BookingDao bookingDao) {
		this.bookingDao = bookingDao;
	}
	
	public boolean bookVenue(Booking booking) {
		boolean status = bookingDao.insertVenue(booking);
		
		return status;
	}
	
	public ArrayList<Booking> getVenue(String email) {
		ArrayList<Booking> myBookings = bookingDao.selectVenue(email);
		
		return myBookings;
	}
}