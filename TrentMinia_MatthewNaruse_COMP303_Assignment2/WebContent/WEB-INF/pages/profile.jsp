<!-- 
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<td>Name</td><td>${cust.getFullName()}</td>
		</tr>
		<tr>
			<td>Address</td><td>${cust.getFullAddress()}</td>
		</tr>
	</table>
</div>

<div class="card">
	<table class="table">
	<tr>
		<th>Order ID</th><th>Product ID</th><th>Quantity</th><th>Delivery Date</th><th>Order Status</th><th></th>
	</tr>
	<c:forEach var="order" items="${ordersList}">
		<tr>
		<th>${order.orderId}</th><th>${order.productId}</th><th>${order.quantity}</th><th>${order.deliveryDate}</th><th>${order.orderStatus}</th><th><span class="btn btn-primary">View</span></th>
	</tr>
	</c:forEach>
	</table>
</div>
<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />