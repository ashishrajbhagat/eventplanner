package com.tadigital.eventplanner.user.controller;

import com.tadigital.eventplanner.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutProcessController {	
	private UserService userService;

	@Autowired
	public LogoutProcessController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/logout")
	public String processLogout(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		session.invalidate();
		
		Cookie cookie = userService.removeCookie();
		response.addCookie(cookie);
		
		session = request.getSession(true);
		session.setAttribute("LOGINSTATUS", "loggedoff");
		
		return "index.jsp";
	}
}