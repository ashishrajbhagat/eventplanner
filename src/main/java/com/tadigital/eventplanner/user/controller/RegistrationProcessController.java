package com.tadigital.eventplanner.user.controller;

import com.tadigital.eventplanner.user.entity.User;
import com.tadigital.eventplanner.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationProcessController {
	private UserService userService;
	
	public RegistrationProcessController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/register")
	public String processRegistration(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
			
		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String email = request.getParameter("emailRegister");
		String pwd = request.getParameter("passwordRegister");
		
		User user = new User();
		user.setName(name);
		user.setMobile(mobile);
		user.setEmail(email);
		user.setPassword(pwd);
		
		boolean status = userService.signUpUser(user);
		
		if (status) {
			session.setAttribute("MESSAGE", "Registration Success");
		} else {
			session.setAttribute("MESSAGE", "Registration Failed!");
		}
		
		return "index.jsp";
	}
}