package com.tadigital.eventplanner.booking.controller;

import com.tadigital.eventplanner.booking.entity.Booking;
import com.tadigital.eventplanner.booking.service.BookingService;
import com.tadigital.eventplanner.user.entity.User;
import com.tadigital.eventplanner.venue.entity.Venue;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookVenueProcessController {
	private BookingService bookingService;
	
	@Autowired
	public BookVenueProcessController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@PostMapping("book")
	public String bookVenueProcess(HttpServletRequest request, HttpServletResponse response, HttpSession session) {		
		User user = (User)session.getAttribute("USER");
		Venue venue = (Venue)session.getAttribute("VENUE");
		
		if (user == null) {
			session.setAttribute("URL", "about.jsp");
			session.setAttribute("MESSAGE", "Please login before booking");
			return "about.jsp";
		}

		String name = venue.getName();
		String location = venue.getLocation();
		int id = venue.getId();
		String date = request.getParameter("date");
		int guest = Integer.parseInt(request.getParameter("guest"));
		
		Booking booking = new Booking();
		booking.setEmail(user.getEmail());
		booking.setId(id);
		booking.setName(name);
		booking.setLocation(location);
		booking.setDate(date);
		booking.setGuest(guest);
		
		boolean status = bookingService.bookVenue(booking);
		
		if (status) {
			ArrayList<Booking> myBookings = bookingService.getVenue(user.getEmail());
			
			session.setAttribute("MYBOOKINGS", myBookings);
			session.setAttribute("MESSAGE", "Booking Success");
			return "myprofile.jsp";
		} else {
			session.setAttribute("MESSAGE", "Booking Failed!");
			return "about.jsp";
		}
	}
}