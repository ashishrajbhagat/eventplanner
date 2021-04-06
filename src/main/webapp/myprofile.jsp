<%-- <!DOCTYPE html> --%>

<%@ page import="com.tadigital.eventplanner.user.entity.User, com.tadigital.eventplanner.booking.entity.Booking, java.util.ArrayList" %>

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
	if (message != null && (!message.equals("")) && (!message.equals("Update Success")) && (!message.equals("Login Success")) && (!message.equals("Old Password"))) {
%>

		<div id="error" class="error">
			<span>
				<%= message %>
			</span>
		</div>

<%
		session.setAttribute("MESSAGE", "");
	}
%>

    <main>
        <section class="container profile">
			<%-- Profile left section --%>
            <div class="left">

<%
				if (user != null && user.getProfilePicture() != null) {
%>

					<img src="images/<%= user.getProfilePicture() %>" alt="My profile photo">
					
<%
				} else {
%>

					<img src="images/user.png" alt="My profile photo">

<%
				}
%>

                <form action="changephoto" method="post" enctype="multipart/form-data">
                    <label for="photo">Change Profile Picture :</label>
                    <input type="file" name="photo" required>
                    <input type="submit" value="Change">
                </form>

<%
				if (user != null) {
%>

	                <h2><%= user.getName() %></h2>
	                <h3>Mobile : <span><%= user.getMobile() %></span></h3>
	                <h3>Email : <span><%= user.getEmail() %></span></h3>
                
<%
				} else {
%>

					<h2>Anonymous</h2>
	                <h3>Mobile :</h3>
	                <h3>Email :</h3>

<%
				}
%>

            </div>
            <%-- Profile right section --%>
            <div class="right">
            	<section class="bookings">
					<h2>My Bookings</h2>
            
<%
					ArrayList<Booking> myBookings = (ArrayList)session.getAttribute("MYBOOKINGS");
					
					if (myBookings.size() > 0) {
						for (Booking booking : myBookings) {
%>            
            		
	            		
							<div class="booking">
								<h3><%= booking.getName() %></h3>
								<p><%= booking.getLocation() %>, India</p>
								<ul>
									<li>Date : <span><%= booking.getDate() %></span></li>
									<li>Number of guest :  <span><%= booking.getGuest() %></span></li>
								</ul>
							</div>
            
            
<%
						}
%>

					</section>
					
					
<%
					} else {
					
 %>
            
            		<section class="message">
						<p>Your bookings will be listed here.</p>
						<h2><span>Sorry</span> No bookings !</h2>
						<p>Book your venue now to enjoy the function.</p>
					</section>
            
<%
				}
%>            

			</div>
        </section>
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