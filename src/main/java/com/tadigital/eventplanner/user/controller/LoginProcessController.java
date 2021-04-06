package com.tadigital.eventplanner.user.controller;

import com.tadigital.eventplanner.user.entity.User;
import com.tadigital.eventplanner.user.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginProcessController {
	private UserService userService;
	
	@Autowired
	public LoginProcessController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/login")
	public String processLoginUsingCookieValue(HttpServletResponse response, HttpSession session) {
		String cValue = (String)session.getAttribute("COOKIEVALUE");
		
		if (cValue != null) {
			User user = userService.signInUserByCookieValue(cValue);
			if (user != null) {
				session.setAttribute("USER", user);
				session.setAttribute("MESSAGE", "Login Success");
			} else {
				session.setAttribute("MESSAGE", "Old Password");
				
				// In case of password change in other browser
				Cookie cookie = userService.removeCookie();
				response.addCookie(cookie);
			}
		}
		
		return "index.jsp";
	}
	
	@PostMapping("/login")
	public String processLogin(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		String email = request.getParameter("email");
		String pwd = request.getParameter("password");
		
		User user = new User();
		user.setEmail(email);
		user.setPassword(pwd);
	
		boolean status = userService.signInUserByEmailAndPassword(user);
		
		if (status) {
			session.setAttribute("USER", user);
			session.setAttribute("MESSAGE", "Login Success");
			String remember = request.getParameter("rememberMe");
			if (remember != null) {
				Cookie cookie = userService.createCookie(session.getId(), email);
				response.addCookie(cookie);
			}
		} else {
			session.setAttribute("MESSAGE", "Invalid Credentials");
		}
		
		String url = (String)session.getAttribute("URL");
		
		if (url != null && url.equals("about.jsp")) {
			session.removeAttribute("URL");
			System.out.println("url");
			return "about.jsp";
		}
		
		return "index.jsp";
	}
}