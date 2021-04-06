package com.tadigital.eventplanner.user.service;

import com.tadigital.eventplanner.user.entity.User;
import com.tadigital.eventplanner.user.dao.UserDao;
import java.io.File;
import java.io.IOException;
import javax.servlet.http.Part;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Component
public class UserService {
	private UserDao userDao;
	
	@Autowired
	public UserService(UserDao userDao) {
		this.userDao = userDao;
	}
	public Cookie createCookie(String sessId, String email) {
		String newSessId = userDao.updateSessionId(sessId, email);
		Cookie cookie = new Cookie("user", newSessId);
		cookie.setMaxAge(60 * 60 * 24 * 30);
		
		return cookie;
	}
	
	public Cookie removeCookie() {
		Cookie cookie = new Cookie("user", "");
		cookie.setMaxAge(0);
				
		//userDao.updateSessionId("", email);
		
		return cookie;
	}
	
	public boolean signInUserByEmailAndPassword(User user) {
		boolean status = userDao.searchUserByEmailAndPassword(user);
		
		return status;
	}
	
	public User signInUserByCookieValue(String cValue) {
		User user = userDao.searchUserByCookieValue(cValue);
		
		return user;
	}
	
	public boolean signUpUser(User user) {
		boolean status = userDao.insertUser(user);
		
		if (status) {
			sendWelcomeEmail(user.getEmail(), user.getName());
		}
		
		return status;
	}
	
	public boolean changePassword(User user, String newPassword) {
		boolean status = userDao.updatePassword(user, newPassword);
		
		return status;
	}
	
	public boolean changeProfile(User user, String uploadedFileDirectoryPath, String uploadedFileName, Part part) {
		boolean status = userDao.updateProfile(user);
		
		try {
			if (status) {
				createFile(uploadedFileDirectoryPath, uploadedFileName, part);
			}
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		return status;
	}
	
	public User verifyEmail(String email) {
		User user = userDao.selectEmail(email);
		
		return user;
	}
	
	public void createFile(String uploadedFileDirectoryPath, String uploadedFileName, Part part) throws IOException {
		part.write(uploadedFileDirectoryPath + File.separator + uploadedFileName);
	}
	
	public void sendWelcomeEmail(String email, String name) {
		Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
                                                        protected PasswordAuthentication getPasswordAuthentication() {
                                                            return new PasswordAuthentication("rajashish46863@gmail.com", "ashishraj039");
                                                        }
                                                    });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rajashish46863@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("Welcome to Event Planner");
            message.setText("Hi " + name + ",\n\nThanks for signing up to keep in touch with Event Planner. From now on, you'll get regular updates on different venues and special offers from different hotels, banquets or lawns. And since you'll be the first to know, you can always book the best venue we've got (in addition to the different vendors like caterers, decorator, photographer etc).\nIn the meantime, check out our Price for different events. Here's to the start of a healthy digital relationship.\n\nCheers,\nEvent Planner");

            Transport.send(message);
        } catch (MessagingException msgEx) {
            msgEx.printStackTrace();
        }
	}
	
	public void sendOtp(String email, String name, String otp) {
		Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        
        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
                                                        protected PasswordAuthentication getPasswordAuthentication() {
                                                            return new PasswordAuthentication("rajashish46863@gmail.com", "ashishraj039");
                                                        }
                                                    });
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress("rajashish46863@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
            message.setSubject("One Time Password (OTP) for Forgot Password recovery on Event Planner");
            message.setText("Hi " + name + ",\n\nYour One Time Password (OTP) for Forgot Password recovery on Event Planner is " + otp + ".\n\nPlease do not share this One Time Password with anyone.\n\nCheers,\nEvent Planner");

            Transport.send(message);
        } catch (MessagingException msgEx) {
            msgEx.printStackTrace();
        }
	}
	
	public String generateOTP() {
		int randomPin =(int) (Math.random()*900000)+100000;
		String otp = String.valueOf(randomPin);
		return otp;
	}
}