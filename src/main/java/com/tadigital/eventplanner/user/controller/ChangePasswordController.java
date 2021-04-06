package com.tadigital.eventplanner.user.controller;

import com.tadigital.eventplanner.user.entity.User;
import com.tadigital.eventplanner.user.service.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ChangePasswordController {
	private UserService userService;
	
	@Autowired
	public ChangePasswordController(UserService userService) {
		this.userService = userService;
	}
	
	@PostMapping("/changepassword")
	public String processChangePassword(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String oldPassword = request.getParameter("oldPwd");
		String newPassword = request.getParameter("newPwd");
		
		User user = (User)session.getAttribute("USER");
		
		if (oldPassword.equals(user.getPassword())) {
			boolean status = userService.changePassword(user, newPassword);
			
			if (status) {
				user.setPassword(newPassword);
				Cookie cookie = userService.createCookie(session.getId(), user.getEmail());
				response.addCookie(cookie);
				session.setAttribute("USER", user);
				session.setAttribute("MESSAGE", "Password changed successfully");
			} else {
				session.setAttribute("MESSAGE", "Password changed failed!");
			}
		} else {
			session.setAttribute("MESSAGE", "Incorrect old password");
		}
		
		return "index.jsp";
	}
	
	// Change password without login
	@PostMapping("/changePwd")
	public String processChangePasswordWithoutLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String email = (String)session.getAttribute("EMAIL");
		String newPassword = request.getParameter("newPwd");
		
		session.removeAttribute("EMAIL");
		
		User user = new User();
		user.setEmail(email);
		
		boolean status = userService.changePassword(user, newPassword);
			
		if (status) {
			session.setAttribute("MESSAGE", "Password changed successfully");
		} else {
			session.setAttribute("MESSAGE", "Password changed failed!");
		}
		
		return "index.jsp";
	}
}