package com.tadigital.eventplanner.booking.dao;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.tadigital.eventplanner.booking.entity.Booking;

@Component
public class BookingDao {	
	public boolean insertVenue(Booking booking) {
		boolean status = false;
		
		Connection con = null;
		Statement stmt = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_planner", "root", "");
			stmt = con.createStatement();
			
			String sql = "INSERT INTO booking_information VALUES('" + booking.getEmail() + "', '" + booking.getId() + "', '" + booking.getName() + "', '" + booking.getLocation() + "', '" + booking.getDate() + "', '" + booking.getGuest() + "')";
			int row = stmt.executeUpdate(sql);
			
			if (row != 0) {
				status = true;
			}
		} catch (ClassNotFoundException cnfEx) {
			cnfEx.printStackTrace();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		} finally {
			try {
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
		
		return status;
	}
	
	public ArrayList<Booking> selectVenue(String email) {
		ArrayList<Booking> myBookings = new ArrayList<>();
		
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/event_planner", "root", "");
			stmt = con.createStatement();
			
			String sql = "SELECT * FROM booking_information WHERE email='"+ email +"'";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Booking booking = new Booking();				
				
				booking.setEmail(rs.getString(1));
				booking.setId(rs.getInt(2));
				booking.setName(rs.getString(3));
				booking.setLocation(rs.getString(4));
				booking.setDate(rs.getString(5));
				booking.setGuest(rs.getInt(6));
				
				myBookings.add(booking);
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
		
		return myBookings;
	}
}