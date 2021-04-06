<%-- <!DOCTYPE html> --%>

<%@ page import="com.tadigital.eventplanner.user.entity.User, com.tadigital.eventplanner.venue.entity.Venue, java.util.ArrayList" %>

<%
	User user = (User)session.getAttribute("USER");
	String message = (String)session.getAttribute("MESSAGE");
%>

<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;500;600;700&display=swap" rel="stylesheet">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<link rel="icon" type="image/png" sizes="16x16" href="images/favicon-16x16.png">
	<title>Event Planner</title>
	<link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body onload=preload()>
    <div class="preloading">
        <div class="loader">
            <div class="bt-img"></div>
        </div>
    </div>
	<%-- header --%>
	<header id="myHeader" class="header header2">
		<a href="home"><img src="images/logo.PNG" alt="Logo of Event Planner"></a>
		<nav>
			<span tabindex="0" class="menuBtn" id="menuBtn" onclick="showMenu()">&#9776;</span>
			<ul id="menu1" class="menu hide-desktop">
				<li><a href="home">Home</a></li>
				<li><a href="#">Vendor</a></li>
				<li><a href="#">Gallery</a></li>
				<li><a href="#">Blog</a></li>
				<li id="loginModal">Login</li>
				<li><a href="#">Be a partner</a></li>
			</ul>
		</nav>
	</header>
	<%-- Modal for login & register --%>
	<div class="overlay"></div>
	<div class="main-popup" id="mainPopup">
		<div class="popup-header">
			<div id="popup-close-button" class="popup-close-button"><a href="#"></a></div>
			<ul>
				<li><a href="#" class="popup-login" id="login">Login</a></li>
				<li><a href="#" class="popup-register" id="register">Register</a></li>
			</ul>
		</div>
		<div class="popup-content">
			<%-- Login Form --%>
			<form action="login" class="login" method="POST">
				<label for="email">Email:</label>
				<input type="email" id="email" name="email" required>
				<label for="password">Password:</label>
				<input type="password" id="password" name="password" required>
				<p class="check-mark">
					<input type="checkbox" id="rememberMe" name="rememberMe">
					<label for="rememberMe">Remember me</label>
				</p>
				<a href="forgot">Forgotten Password?</a>
				<input type="submit" id="loginSubmit" value="Login">
			</form>
			<%-- Register Form --%>
			<form action="register" class="register" method="POST">
				<label for="name">Name:</label>
				<input type="text" id="name" name="name" required>
				<label for="mobile">Mobile:</label>
				<input type="text" id="mobile" name="mobile" required>
				<label for="emailRegister">Email:</label>
				<input type="email" id="emailRegister" name="emailRegister" required>
				<label for="passwordRegister">Password:</label>
				<input type="password" id="passwordRegister" name="passwordRegister" required>
				<p class="check-mark">
					<input type="checkbox" id="acceptTerms" required>
					<label for="acceptTerms">I agree to the <a href="#">Terms</a></label>
				</p>
				<input type="submit" id="registerSubmit" value="Register">
			</form>
		</div>
	</div>
	
<%
	if (message != null && (!message.equals(""))) {
%>

		<div id="error" class="error">
			<span>
				<%= message %>
			</span>
		</div>

<%
		session.setAttribute("MESSAGE", "");
	}

	String email = (String)session.getAttribute("EMAIL");
%>

	<main class="forgotPassword">
		<div class="popup-header">
			<ul>
				<li class="popup-changePwd"><a href="#" id="changePwdHeader">Verify OTP</a></li>
			</ul>
		</div>
		<div class="popup-content">
			<%-- Change Password Form --%>
			<form action="verifyotp" class="forgot" method="POST">
				<label for="email">Email Id:</label>
				<input type="email" id="email" name="email" value="<%= email %>" disabled required>
				<label for="otp">OTP:</label>
				<input type="text" id="otp" name="otp" required>
				<p>Didn't get OTP? <a href="sendotp">Send Again</a></p>
				<input type="submit" name="button" value="Verify OTP">
			</form>
		</div>
		<aside class="help">
	       	<span>How may I help you?</span>
	       	<img id="help" src="images/help1.jpg" alt="Helpdesk Girl">
	    </aside>
	</main>

	<%@ include file="footer.html"%>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="js/script.js"></script>
</body>
</html>