package com.tadigital.eventplanner.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePageController {
	@GetMapping("/")
	public String displayHomePage() {
		return "index.jsp";
	}
	
	@GetMapping("/home")
	public String displayHomePage2() {
		return "index.jsp";
	}
}