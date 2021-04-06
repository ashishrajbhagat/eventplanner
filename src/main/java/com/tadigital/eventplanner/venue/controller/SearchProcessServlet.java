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
public class SearchProcessServlet {
	private VenueService venueService;
	
	@Autowired
	public SearchProcessServlet(VenueService venueService) {
		this.venueService = venueService;
	}
	
	@GetMapping("/search")
	public String processSearch(HttpServletRequest request, HttpSession session){
		String function = request.getParameter("eventType");
		String location = request.getParameter("eventVenue");
		
		ArrayList<Venue> venueList = venueService.searchVenue(function, location);
		session.setAttribute("ALLVENUE", venueList);
		
		return "search.jsp";
	}
}