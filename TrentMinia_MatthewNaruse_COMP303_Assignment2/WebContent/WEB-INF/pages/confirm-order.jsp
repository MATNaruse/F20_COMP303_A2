<!-- 
	COMP303-001 Assignment 2
	Due Date: Nov 04, 2020
	Submitted: Nov 04, 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/segments/header.jsp" />
<jsp:include page="/WEB-INF/pages/segments/navbar.jsp" />

<div class="container">
	<div class="card">
		<h1 class="text-center">Order Confirmation</h1>
		<h4 class="card-title">Thank you for your Order!</h4>
		<h5 class="card-body">We will process it as soon as possible.</h5>
		<h5 class="card-body">You have up to 24 hours to modify or delete your order.</h5>
		<a class="btn btn-primary" href="profile">View All Orders</a>
		<table class="table">
			<thead>
				<tr>
					<th>Product</th><th>Per Unit</th><th>Quantity</th><th>Total Price</th><th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cartItem" items="${cart}">
					<tr>
						<td>${cartItem.key}</td>
						<td>$${cartItem.value.productPrice }</td>
						<td>${cartItem.value.quantity}</td>
						<td>$${cartItem.value.totalPrice}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />