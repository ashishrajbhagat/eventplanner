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

<%
				if (user != null) {
%>

					<li class="dropdown">
						<i class="fa fa-user"></i>  
						<%= user.getName() %>
						<i class="fa fa-angle-down"></i>
						<ul class="dropdown__content">
							<li><a href="myprofile">My Profile</a></li>
							<li id="changePassword">Change Password</li>
							<li><a href="logout">Logout</a></li>
						</ul>
					</li>
	
<%
				} else {
%>

					<li id="loginModal">Login</li>
					
<%
				}
%>
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
	<div class="main-popup" id="mainPopupChange">
		<div class="popup-header">
			<div id="popup-close-button" class="popup-close-button"><a href="#"></a></div>
			<ul>
				<li class="popup-changePwd"><a href="#" id="changePwdHeader">Change Password</a></li>
			</ul>
		</div>
		<div class="popup-content">
			<%-- Change Password Form --%>
			<form action="changepassword" class="changePwd" method="POST">
				<label for="oldPwd">Old Password:</label>
				<input type="password" id="oldPwd" name="oldPwd" required>
				<label for="newPwd">New Password:</label>
				<input type="password" id="newPwd" name="newPwd" required>
				<input type="submit" id="changePwdSubmit" value="Change Password">
			</form>
		</div>
	</div>
	
<%
	if (message != null && (!message.equals("")) && (!message.equals("Login Success")) && (!message.equals("Old Password"))) {
%>

		<div id="error" class="error">
			<span>
				<%= message %>
			</span>
		</div>

<%
		session.setAttribute("MESSAGE", "");
	}
	
	Venue venue = (Venue)session.getAttribute("VENUE");
	if (venue != null) {
%>

		<main class="description">
	        <div class="description__left">
	            <section class="details">
	                <h1><%= venue.getName() %></h1>
	                <h2><i class="fa fa-map-marker" aria-hidden="true"></i> <%= venue.getLocation() %>, India</h2>
	            </section>
	            <section class="about">
	                <h2>About</h2>
	                <p>A stunning banquet hall in Kirti Nagar, <%= venue.getLocation() %> with favorable named as <%= venue.getName() %> recognized to be the best banquet in west <%= venue.getLocation() %> serving for years in the industry. A place that can accommodate thousands of people easily and allows every form of event in it. With an outstanding architect as well as interior, an eye-catching view can be witnessed by our potential customers and their guest. We cover every event from corporate one that needs a classic and elegant theme to a wedding or reception with fascinating ideas. With a highly qualified and experienced team at our banquet, we immensely accomplished hundreds of events and set our roots in the industry.</p>
	            </section>
	            <section class="space">
	                <h2>Event Space</h2>
	                <table>
	                    <thead>
	                        <tr>
	                            <th>Space Type</th>
	                            <th>Quantity</th>
	                            <th>Min Capacity</th>
	                            <th>Max Capacity</th>
	                        </tr>
	                    </thead>
	                    <tbody>
	                        <tr>
	                            <td>Banquete</td>
	                            <td>2</td>
	                            <td>200</td>
	                            <td>600</td>
	                        </tr>
	                        <tr>
	                            <td>Lawn</td>
	                            <td>1</td>
	                            <td>500</td>
	                            <td>800</td>
	                        </tr>
	                        <tr>
	                            <td>Terrace</td>
	                            <td>1</td>
	                            <td>100</td>
	                            <td>200</td>
	                        </tr>
	                        <tr>
	                            <td>Hall</td>
	                            <td>4</td>
	                            <td>200</td>
	                            <td>400</td>
	                        </tr>
	                        <tr>
	                            <td>Room</td>
	                            <td>25</td>
	                            <td>1</td>
	                            <td>4</td>
	                        </tr>
	                    </tbody>
	                </table>
	            </section>
	            <section class="facility">
	                <h2>Facilities</h2>
	                <ul>
	                    <li>Air conditioner</li>
	                    <li>Bands</li>
	                    <li>Catering</li>
	                    <li>Decorator</li>
	                    <li>DJ</li>
	                    <li>Full Bar</li>
	                    <li>Live Music</li>
	                    <li>Parking</li>
	                    <li>Photographer</li>
	                    <li>Power Backup</li>
	                    <li>Roof Top</li>
	                    <li>Wifi</li>
	                </ul>
	            </section>
	        </div>
	        <aside class="description__right">
	            <form action="book" method="POST">
	                <legend><h2>Book Venue</h2></legend>
	                <label for="date">Date</label>
	                <input type="date" name="date" id="date" required>
	                <label for="guest">No of Guest</label>
	                <input type="number" name="guest" id="guest" min="100" max="2000" required>
	                <input type="submit" value="Book">
	            </form>
	        </aside>
	        <aside class="help">
	        	<span>How may I help you?</span>
	        	<img id="help" src="images/help1.jpg" alt="Helpdesk Girl">
	        </aside>
		</main>
		
<%
	}
%>		

	<%@ include file="footer.html"%>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="js/script.js"></script>
</body>
</html>