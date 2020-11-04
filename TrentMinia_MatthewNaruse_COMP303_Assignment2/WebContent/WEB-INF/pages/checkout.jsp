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

<div class="container">
	<div class="row d-flex justify-content-center">
		<!-- List of Cart Items -->
		<div class="card profile-main col-lg-6 col-sm-10">
			<h1 class="text-center">Confirm Your Order</h1>
			<h4 class="card-title">Shopping Cart</h4>
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
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<a href="/TrentMinia_MatthewNaruse_COMP303_Assignment2/order" class="btn btn-primary">Modify Cart</a>
		</div>
	
		<!-- Credit Card Form -->
		<form method="POST"	action="/TrentMinia_MatthewNaruse_COMP303_Assignment2/confirmPayment"
					class="card profile-orders col-lg-5 col-sm-10 text-left">
			<h4 class="text-center">Payment Information</h4>
			<c:if test="${not empty out_msg}">
				<div class="alert alert-danger text-center" >${out_msg}</div>
			</c:if>
			<table id="ccForm" class="d-flex justify-content-center">
				<tr>
					<td><label for="ccNumber" >Credit Card Number</label></td>
					<td><input class="form-control" id="ccNumber" name="ccNumber" placeholder="0000-0000-0000-0000" required="required"/></td>
				</tr>
				<tr>
					<td><label for="ccSecurity" >Card Security Code</label></td>
					<td><input class="form-control" id="ccSecurity" name="ccSecurity" placeholder="000" required="required"/></td>
				</tr>
				<tr>
					<td><label for="ccName" >Card Holder Name</label></td>
					<td><input class="form-control" id="ccName" name="ccName" value="${cust.getFullName()}" required="required"/></td>
				</tr>
				<tr>
					<td><label for="deliveryDate" >Delivery Date</label></td>
					<td><input type="date" class="form-control" id="deliveryDate" name="deliveryDate" required="required"/></td>
				</tr>
			</table>
			<button type="submit" class="btn btn-primary"/>Submit</button>
		
		</form>
	</div>
</div>


<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />