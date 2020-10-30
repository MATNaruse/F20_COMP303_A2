<!-- 
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<span class="navbar-brand">DigiTel</span>
	
	<!-- Adds "Mobile" toggle button -->
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent">
		<span class="navbar-toggler-icon"></span>
	</button>
	
	<div class="collapse navbar-collapse" id="navbarSupportedContent"> <!-- Shrinks if viewport is small -->
		<ul class="navbar-nav">
			<li class="nav-item">
				<a class="nav-link" href="index.jsp">Home</a>
			</li>
			<% if (session.getAttribute("currentCustomer") != null) { %>
				<li class="nav-item">
					<a class="nav-link" href="order">Order</a>
				</li>
				<!-- Won't be normally accessed via NavBar
					<li class="nav-item">
						<a class="nav-link" href="checkout">Checkout</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="confirm-order">Confirm Order</a>
					</li> 
				-->
				<li class="nav-item">
					<a class="nav-link" href="profile">Profile</a>
				</li>
				<!-- Won't be normally accessed via NavBar
					<li class="nav-item">
						<a class="nav-link" href="register.html">Register</a>
					</li> 
					<li class="nav-item">
						<a class="nav-link" href="view-order">View Order</a>
					</li> 
				-->
				<li class="nav-item">
					<a class="nav-link" href="/TrentMinia_MatthewNaruse_COMP303_Assignment2/logout">Logout</a>
				</li>
			<% } %>
		</ul>
	</div>
</nav>