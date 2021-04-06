package com.tadigital.eventplanner.user.entity;

public class User {
	private String name;
	private String mobile;
	private String email;
	private String password;
	private String profilePicture;
	
	public String getName() {
		return name;
	}
	public String getMobile() {
		return mobile;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public String getProfilePicture() {
		return profilePicture;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setProfilePicture(String profilePicture) {
		this.profilePicture = profilePicture;
	}
}