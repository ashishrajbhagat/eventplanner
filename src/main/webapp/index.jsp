<%-- <!DOCTYPE html> --%>

<%@ page import="com.tadigital.eventplanner.user.entity.User" %>

<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
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
	<header id="myHeader" class="header">
		<a href="home"><img src="images/logo.PNG" alt="Logo of Event Planner"></a>
		<nav>
			<span tabindex="0" class="menuBtn" id="menuBtn" onclick="showMenu()">&#9776;</span>
			<ul id="menu1" class="menu hide-desktop">
				<li><a class="active" href="home">Home</a></li>
				<li><a href="#">Vendor</a></li>
				<li><a href="#">Gallery</a></li>
				<li><a href="#">Blog</a></li>
				
<%
				String message = (String)session.getAttribute("MESSAGE");
				User user = (User)session.getAttribute("USER");
				
				Cookie[] allCookies = request.getCookies();
				if (allCookies != null) {
					for (Cookie cookie : allCookies) {
						if (cookie.getName().equals("user")) {
							String loginStatus = (String) session.getAttribute("LOGINSTATUS");
							if (user == null && loginStatus == null) {
								session.setAttribute("COOKIEVALUE", cookie.getValue());
								response.sendRedirect("login");
							}
						}
						break;
					}
				}
				
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
%>

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
	<main>
		<%-- Hero image --%>
		<section class="hero">
			<h1>Find perfect venue for every event</h1>
			<form action="search">
				<label for="eventType">Event Type</label>
				<select name="eventType" id="eventType" required>
					<option value="" selected disabled>I am planning for</option>
					<option value="Wedding">Wedding</option>
					<option value="Engagement">Engagement</option>
					<option value="Birthday">Birthday</option>
					<option value="Seminar">Seminar</option>
				</select>
				<label for="eventVenue">Event Venue</label>
				<select name="eventVenue" id="eventVenue" required>
					<option value="" selected disabled>Near</option>
					<option value="Delhi">Delhi</option>
					<option value="Hyderabad">Hyderabad</option>
					<option value="Mumbai">Mumbai</option>
					<option value="Banglore">Banglore</option>
				</select>
				<input type="submit" value="Search">
			</form>
		</section>
		<%-- How it works? section --%>
		<section class="work container">
			<h2 class="sectionHeading">How it works?</h2>
			<div class="grid">
				<div class="workCard">
					<img src="images/search.png" alt="Browse Venues">
					<h3>Browse venues</h3>
					<p>Check out the best suited Venues, compare photos, special offers and function packages.</p>
				</div>
				<div class="workCard">
					<img src="images/rupee.png" alt="Request Quote">
					<h3>Request Quote</h3>
					<p>Get custom quotes of your short-listed Venues at the click of GET FREE QUOTE button.</p>
				</div>
				<div class="workCard">
					<img src="images/calender.png" alt="Book a Venue">
					<h3>Book a Venue</h3>
					<p>Select and Book the perfect venue in no time at all. Time is money, save both.</p>
				</div>
			</div>
		</section>
		<%-- Browse by function section --%>
		<section class="container function">
			<h2 class="sectionHeading">Browse by functions</h2>
            <div class="card">
                <div class="card__wrapper">
                    <a href="search?eventType=Wedding">
                        <div class="card__item">
                            <div class="card__image"></div>
                            <div class="card__info">
                                <h3 class="card__title">Wedding</h3>
                            </div>
                        </div>
                    </a>
                </div>
				<div class="card__wrapper">
                    <a href="search?eventType=Engagement">
                        <div class="card__item">
                            <div class="card__image"></div>
                            <div class="card__info">
                                <h3 class="card__title">Engagement</h3>
                            </div>
                        </div>
                    </a>
                </div>
				<div class="card__wrapper">
                    <a href="search?eventType=Birthday">
                        <div class="card__item">
                            <div class="card__image"></div>
                            <div class="card__info">
                                <h3 class="card__title">Birthday</h3>
                            </div>
                        </div>
                    </a>
                </div>
				<div class="card__wrapper">
                    <a href="search?eventType=Seminar">
                        <div class="card__item">
                            <div class="card__image"></div>
                            <div class="card__info">
                                <h3 class="card__title">Seminar</h3>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </section>
		<%-- Browse by venue section --%>
		<section class="container venue">
			<h2 class="sectionHeading">Browse by venue</h2>
            <div class="card">
                <div class="card__wrapper">
                    <a href="search?eventVenue=Delhi">
                        <div class="card__item">
                            <div class="card__image"></div>
                            <div class="card__info">
                                <h3 class="card__title">Delhi</h3>
                            </div>
                        </div>
                    </a>
                </div>
				<div class="card__wrapper">
                    <a href="search?eventVenue=Hyderabad">
                        <div class="card__item">
                            <div class="card__image"></div>
                            <div class="card__info">
                                <h3 class="card__title">Hyderabad</h3>
                            </div>
                        </div>
                    </a>
                </div>
				<div class="card__wrapper">
                    <a href="search?eventVenue=Mumbai">
                        <div class="card__item">
                            <div class="card__image"></div>
                            <div class="card__info">
                                <h3 class="card__title">Mumbai</h3>
                            </div>
                        </div>
                    </a>
                </div>
				<div class="card__wrapper">
                    <a href="search?eventVenue=Banglore">
                        <div class="card__item">
                            <div class="card__image"></div>
                            <div class="card__info">
                                <h3 class="card__title">Banglore</h3>
                            </div>
                        </div>
                    </a>
                </div>
            </div>
        </section>
		<%-- E-invitation section --%>
		<section class="einvite">
			<div class="left">
				<img src="images/einvite.png" alt="E-invitation card">
			</div>
			<div class="right">
				<h2>Create Online Invitations</h2>
				<h3>Bring Everyone Together for Life's Precious Moments</h3>
				<a href="#">Browse E-Invitations</a>
			</div>
		</section>
		<section class="container">
            <h2 class="sectionHeading">Testimonials</h2>
            <div class="testimonials">
                <div class="testimonials-track">
                    <div class="testimonial">
                        <img src="images/testimonials/testimonial8.jpg" alt="testimonial1" />
                        <h3>Saurabh Kumar</h3>
                        <span>Ranchi, India</span>
                        <p><i class="fa fa-quote-left"></i>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Quae itaque, voluptatibus optio quas voluptas at, totam hic accusamus neque quam sed modi rerum adipisci deserunt aut, ratione omnis sequi repellat?<i class="fa fa-quote-right"></i></p>
                    </div>
                    <div class="testimonial">
                        <img src="images/testimonials/testimonial9.jpg" alt="testimonial2" />
                        <h3>Arvind Kumar</h3>
                        <span>Hyderabad, India</span>
                        <p><i class="fa fa-quote-left"></i>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex rerum, corporis itaque at nam voluptates. Iure est aut eaque inventore, commodi vel distinctio totam fugiat quaerat dolores maxime! Aspernatur, amet.<i class="fa fa-quote-right"></i></p>
                    </div>
                    <div class="testimonial">
                        <img src="images/testimonials/testimonial10.png" alt="testimonial3" />
                        <h3>Diksha Chhabra</h3>
                        <span>Yamunanagar, India</span>
                        <p><i class="fa fa-quote-left"></i>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quasi quos tempora rem modi consequuntur vitae hic, quo, recusandae amet odit animi sint aliquid dolorum tenetur accusantium enim temporibus cupiditate quidem.<i class="fa fa-quote-right"></i></p>
                    </div>
                    <div class="testimonial">
                        <img src="images/testimonials/testimonial11.jpg" alt="testimonial4" />
                        <h3>Ashish Raj</h3>
                        <span>Buxar, India</span>
                        <p><i class="fa fa-quote-left"></i>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Excepturi natus eveniet iusto incidunt tempora cumque doloribus itaque, quod consequatur veritatis odit fugit enim non quas ullam rerum? Qui, distinctio autem?<i class="fa fa-quote-right"></i></p>
                    </div>
                    <div class="testimonial">
                        <img src="images/testimonials/testimonial12.jpg" alt="testimonial5" />
                        <h3>Sandeep Singh</h3>
                        <span>Rourkela, India</span>
                        <p><i class="fa fa-quote-left"></i>Lorem ipsum dolor sit amet consectetur adipisicing elit. Doloribus voluptatum, veritatis nostrum nam sint, vero accusantium eligendi eum alias reiciendis ad assumenda, odit quidem voluptates officiis nemo perspiciatis esse inventore.<i class="fa fa-quote-right"></i></p>
                    </div>
                    <div class="testimonial">
                        <img src="images/testimonials/testimonial8.jpg" alt="testimonial1" />
                        <h3>Saurabh Kumar</h3>
                        <span>Ranchi, India</span>
                        <p><i class="fa fa-quote-left"></i>Lorem ipsum dolor, sit amet consectetur adipisicing elit. Quae itaque, voluptatibus optio quas voluptas at, totam hic accusamus neque quam sed modi rerum adipisci deserunt aut, ratione omnis sequi repellat?<i class="fa fa-quote-right"></i></p>
                    </div>
                    <div class="testimonial">
                        <img src="images/testimonials/testimonial9.jpg" alt="testimonial2" />
                        <h3>Arvind Kumar</h3>
                        <span>Hyderabad, India</span>
                        <p><i class="fa fa-quote-left"></i>Lorem ipsum dolor sit amet consectetur adipisicing elit. Ex rerum, corporis itaque at nam voluptates. Iure est aut eaque inventore, commodi vel distinctio totam fugiat quaerat dolores maxime! Aspernatur, amet.<i class="fa fa-quote-right"></i></p>
                    </div>
                    <div class="testimonial">
                        <img src="images/testimonials/testimonial10.png" alt="testimonial3" />
                        <h3>Diksha Chhabra</h3>
                        <span>Yamunanagar, India</span>
                        <p><i class="fa fa-quote-left"></i>Lorem ipsum dolor sit, amet consectetur adipisicing elit. Quasi quos tempora rem modi consequuntur vitae hic, quo, recusandae amet odit animi sint aliquid dolorum tenetur accusantium enim temporibus cupiditate quidem.<i class="fa fa-quote-right"></i></p>
                    </div>
                    <div class="testimonial">
                        <img src="images/testimonials/testimonial11.jpg" alt="testimonial4" />
                        <h3>Ashish Raj</h3>
                        <span>Buxar, India</span>
                        <p><i class="fa fa-quote-left"></i>Lorem ipsum dolor sit amet consectetur, adipisicing elit. Excepturi natus eveniet iusto incidunt tempora cumque doloribus itaque, quod consequatur veritatis odit fugit enim non quas ullam rerum? Qui, distinctio autem?<i class="fa fa-quote-right"></i></p>
                    </div>
                    <div class="testimonial">
                        <img src="images/testimonials/testimonial12.jpg" alt="testimonial5" />
                        <h3>Sandeep Singh</h3>
                        <span>Rourkela, India</span>
                        <p><i class="fa fa-quote-left"></i>Lorem ipsum dolor sit amet consectetur adipisicing elit. Doloribus voluptatum, veritatis nostrum nam sint, vero accusantium eligendi eum alias reiciendis ad assumenda, odit quidem voluptates officiis nemo perspiciatis esse inventore.<i class="fa fa-quote-right"></i></p>
                    </div>
                </div>
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