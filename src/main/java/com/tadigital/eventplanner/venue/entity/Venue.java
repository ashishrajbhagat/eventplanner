package com.tadigital.eventplanner.venue.entity;

public class Venue {
	private int id;
	private String name;
	private String mobile;
	private String location;
	private String rating;
	private String photo;
	private String function;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getMobile() {
		return mobile;
	}
	public String getLocation() {
		return location;
	}
	public String getRating() {
		return rating;
	}
	public String getPhoto() {
		return photo;
	}
	public String getFunction() {
		return function;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void setFunction(String function) {
		this.function = function;
	}
}