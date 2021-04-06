package com.tadigital.eventplanner.user.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tadigital.eventplanner.booking.entity.Booking;
import com.tadigital.eventplanner.booking.service.BookingService;
import com.tadigital.eventplanner.user.entity.User;
import com.tadigital.eventplanner.user.service.UserService;

@Controller
public class MyProfileController {
	private UserService userService;
	private BookingService bookingService;
	
	@Autowired
	public MyProfileController(UserService userService, BookingService bookingService) {
		this.userService = userService;
		this.bookingService = bookingService;
	}
	
	@GetMapping("/myprofile")
	public String displayMyProfilePage(HttpSession session) {
		User user = (User)session.getAttribute("USER");
		
		ArrayList<Booking> myBookings = bookingService.getVenue(user.getEmail());
		session.setAttribute("MYBOOKINGS", myBookings);
		
		return "myprofile.jsp";
	}
	
	@PostMapping("/changephoto")
	public String updatePhoto(HttpServletRequest request, HttpSession session) {
		Part filePart = null;
		
		try {
			filePart = request.getPart("photo");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (ServletException se) {
			se.printStackTrace();
		}
		
		String uploadedFileName = filePart.getSubmittedFileName();
		
		User user = (User)session.getAttribute("USER");
		
		user.setProfilePicture(uploadedFileName);
		
		// String uploadedFileDirectoryPath = request.getSession().getServletContext().getRealPath("") + File.separator + "images";
		
		String uploadedFileDirectoryPath = request.getSession().getServletContext().getRealPath("") + "images";
		
		boolean status = userService.changeProfile(user, uploadedFileDirectoryPath, uploadedFileName, filePart);
		
		if (status) {
			session.setAttribute("MESSAGE", "Update Success");
		} else {
			session.setAttribute("MESSAGE", "Update Failed!");
		}
		
		return "myprofile.jsp";
	}
}