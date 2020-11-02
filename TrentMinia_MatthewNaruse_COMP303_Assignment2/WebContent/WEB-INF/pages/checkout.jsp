<!-- 
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/segments/header.jsp" />
<jsp:include page="/WEB-INF/pages/segments/navbar.jsp" />
<h1>Checkout</h1>

<div class="container">
	<!-- List of Cart Items -->
	<div class="card">
		<h1 class="text-center">Order</h1>
		<h4 class="card-title">Shopping Cart</h4>
		<form id="remForm" method="POST"
			   action="/TrentMinia_MatthewNaruse_COMP303_Assignment2/remFromCart">
			<table class="table">
			<thead>
				<tr>
					<th>Product</th><th>Quantity</th><th>Price</th><th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="cartItem" items="${cart}">
					<tr>
						<td>${cartItem.key}</td>
						<td>${cartItem.value.quantity}</td>
						<td>${cartItem.value.totalPrice}</td>
						<td><button type="submit" class="btn btn-primary" name="removeItem" value="${cartItem.key}">Remove</button></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		</form>
	</div>

	<!-- Credit Card Form -->
	<form method="POST"	action="/TrentMinia_MatthewNaruse_COMP303_Assignment2/confirmPayment"
				class="card">
		<h4>Payment Information</h4>
		<table>
			<tr>
				<td><label for="ccNumber" >Credit Card Number</label></td>
				<td><input class="form-control" id="ccNumber" name="ccNumber" placeholder="0000-0000-0000-0000"/></td>
			</tr>
			<tr>
				<td><label for="ccSecurity" >Credit Card Security Code</label></td>
				<td><input class="form-control" id="ccSecurity" name="ccSecurtiy" placeholder="000"/></td>
			</tr>
			<tr>
				<td><label for="ccName" >Card Holder Name</label></td>
				<td><input class="form-control" id="ccName" name="ccName"/></td>
			</tr>
			<tr>
				<td><label for="deliveryDate" >Delivery Date</label></td>
				<td><input type="date" class="form-control" id="deliveryDate" name="deliveryDate"/></td>
			</tr>
			<tr>
				<td><button type="submit" class="btn btn-primary"/>Submit</button></td>
			</tr>
		</table>
	
	</form>
</div>


<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />