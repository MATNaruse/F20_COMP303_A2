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
		<!-- Order Details -->
		<h1 class="text-center">View Order</h1>
		<table class="table">
			<tr>
				<th>Customer</th>
				<td>${cust.getFullName()}</td>
			</tr>
			<tr>
				<th>Delivery Address</th>
				<td>${cust.getFullAddress()}</td>
			</tr>
			<tr>
				<th>Order Date</th>
				<td>${orderInfo.getPlainCreationDate()}</td>
			</tr>
			<tr>
				<th>Expected Delivery Date</th>
				<td>${orderInfo.deliveryDate}</td>
			</tr>
			<tr>
				<th>Delivery Status</th>
				<td>${orderInfo.orderStatus}</td>
			</tr>
		</table>
	</div>	
			
	<div class="card">
		<!-- Order Items -->
		<table class="table">
			<thead>
				<tr>
					<th>Product</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total Price</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="orderItem" items="${orderItems}">
					<tr>
						<td>${orderItem.productName}</td>
						<td>$${orderItem.productPrice}</td>
						<td>${orderItem.quantity}</td>
						<td>$${orderItem.totalPrice}</td>
					</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<th>Grand Total:</th>
					<td>$${orderTotal}</td>
				</tr>
			</tbody>
		</table>
	</div>
<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />