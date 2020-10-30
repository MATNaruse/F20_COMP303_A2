<!-- 
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
 -->
 
<jsp:include page="/WEB-INF/pages/segments/header.jsp" />
<jsp:include page="/WEB-INF/pages/segments/navbar.jsp" />

<div class="card">
	<h1>Profile</h1>
	<table class="table">
		<tr>
			<td>Username</td><td>${cust.userName}</td>
		</tr>
		<tr>
			<td>Password</td><td>${cust.password}</td>
		</tr>
		<tr>
			<td>Name</td><td>${cust.fullname}</td>
		</tr>
		<tr>
			<td>Address</td><td>${cust.fulladdress}</td>
		</tr>
	</table>
</div>
<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />