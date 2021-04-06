package com.tadigital.eventplanner.venue.service;

import com.tadigital.eventplanner.venue.entity.Venue;
import com.tadigital.eventplanner.venue.dao.VenueDao;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VenueService {
	private VenueDao venueDao;
	
	@Autowired
	public VenueService(VenueDao venueDao) {
		this.venueDao = venueDao;
	}
	
	public ArrayList<Venue> searchVenue(String function, String location) {
		ArrayList<Venue> venueList = venueDao.selectAllVenue(function, location);
		
		return venueList;
	}
}