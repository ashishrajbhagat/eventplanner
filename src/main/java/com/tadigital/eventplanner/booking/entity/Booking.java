package com.tadigital.eventplanner.booking.entity;

public class Booking {
	String email;
	int id;
	String name;
	String location;
	String date;
	int guest;
	
	public String getEmail() {
		return email;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public String getDate() {
		return date;
	}
	public int getGuest() {
		return guest;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setGuest(int guest) {
		this.guest = guest;
	}
}