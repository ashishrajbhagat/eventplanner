package com.tadigital.eventplanner.venue.controller;

import com.tadigital.eventplanner.venue.entity.Venue;
import com.tadigital.eventplanner.venue.service.VenueService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class AboutProcessController {
	private VenueService venueService;
	
	@Autowired
	public AboutProcessController(VenueService venueService) {
		this.venueService = venueService;
	}
	
	@GetMapping("/about")
	public String processAbout(HttpServletRequest request, HttpSession session) {
		int id = Integer.parseInt(request.getParameter("id"));
		
		ArrayList<Venue> venueList = (ArrayList)session.getAttribute("ALLVENUE");
		if (venueList != null) {
			for (int i=0; i<venueList.size(); i++) {
				if(venueList.get(i).getId() == id) {
					session.setAttribute("VENUE", venueList.get(i));
				}
			}
		}
		
		return "about.jsp";
	}
}