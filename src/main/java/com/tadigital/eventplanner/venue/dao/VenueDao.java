package com.tadigital.eventplanner.venue.dao;

import com.tadigital.eventplanner.venue.entity.Venue;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class VenueDao {
	public ArrayList<Venue> selectAllVenue(String function, String location) {
		ArrayList<Venue> venueList = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_planner", "root", "");
			stmt = con.createStatement();
			
			String sql = null;
			if (function == null) {
				sql = "SELECT * FROM venue_information WHERE location='"+ location +"'";
			} else if (location == null) {
				sql = "SELECT * FROM venue_information WHERE function='" + function + "'";
			} else {
				sql = "SELECT * FROM venue_information WHERE function='" + function + "' AND location='"+ location +"'";
			}
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Venue venue = new Venue();				
				
				venue.setId(rs.getInt(1));
				venue.setName(rs.getString(2));
				venue.setMobile(rs.getString(3));
				venue.setLocation(rs.getString(4));
				venue.setRating(rs.getString(5));
				venue.setPhoto(rs.getString(6));
				venue.setFunction(rs.getString(7));
				
				venueList.add(venue);
			}
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (stmt != null) {
					stmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
		return venueList;
	}
}