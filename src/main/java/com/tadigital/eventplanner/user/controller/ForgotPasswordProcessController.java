package com.tadigital.eventplanner.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tadigital.eventplanner.user.entity.User;
import com.tadigital.eventplanner.user.service.UserService;

@Controller
public class ForgotPasswordProcessController {
	private UserService userService;
	
	@Autowired
	public ForgotPasswordProcessController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/forgot")
	public String displayForgotPasswordForm() {
		return "forgot.jsp";
	}
	
	@GetMapping("/sendotp")
	public String processSendOtpAgain(HttpServletRequest request, HttpSession session) {
		String email = (String)session.getAttribute("EMAIL");
		
		String otp = userService.generateOTP();
		
		session.setAttribute("OTP", otp);
		session.setAttribute("MESSAGE", "OTP has been send to your email");
		
		userService.sendOtp(email, "User", otp);
		return "verifyotp.jsp";
	}
	
	@PostMapping("/sendotp")
	public String processSendOtp(HttpServletRequest request, HttpSession session) {
		String email = request.getParameter("email");
		User user = userService.verifyEmail(email);
		
		if (user != null) {
			String otp = userService.generateOTP();
			
			session.setAttribute("EMAIL", email);
			session.setAttribute("OTP", otp);
			session.setAttribute("MESSAGE", "OTP has been send to your email");
			
			userService.sendOtp(user.getEmail(), user.getName(), otp);
			return "verifyotp.jsp";
		} else {
			session.setAttribute("MESSAGE", "Wrong Email Id");
			return "forgot.jsp";
		}
	}
}