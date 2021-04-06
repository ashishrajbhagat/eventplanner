package com.tadigital.eventplanner.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class VerifyOtpProcessController {
	
	@PostMapping("/verifyotp")
	public String processForgotPassword(HttpServletRequest request, HttpSession session) {
		String otp = request.getParameter("otp");
		
		String sessOtp = (String)session.getAttribute("OTP");
		
		if (otp.equals(sessOtp)) {
			return "changepwd.jsp";
		} else {
			session.setAttribute("MESSAGE", "Invalid OTP");
			return "verifyotp.jsp";
		}
	}
}