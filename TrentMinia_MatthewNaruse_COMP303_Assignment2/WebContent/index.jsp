<!-- 
	COMP303-001 Assignment 2
	Due Date: Nov 04, 2020
	Submitted: Nov 04, 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/pages/segments/header.jsp" />

<% if (session.getAttribute("currentCustomer") != null) { %>
	<jsp:include page="/WEB-INF/pages/segments/navbar.jsp" />
<% } %>

<div class="form-signin card">
	<h1>Welcome to Digi Tel</h1>
	<% if (session.getAttribute("currentCustomer") == null) { %>
		<a class="btn btn-primary" href="/TrentMinia_MatthewNaruse_COMP303_Assignment2/login">Log In</a>
		<a class="btn btn-primary" href="/TrentMinia_MatthewNaruse_COMP303_Assignment2/register">Register</a>
	<% } else { %>
		<div class="alert alert-success text-center">You are currently logged in.</div>
		<a class="btn btn-primary" href="order">Place an Order</a>
	<% } %>
</div>
<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />