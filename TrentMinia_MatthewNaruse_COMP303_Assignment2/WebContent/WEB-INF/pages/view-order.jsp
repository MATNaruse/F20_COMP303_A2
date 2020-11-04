<!-- 
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/pages/segments/header.jsp" />
<jsp:include page="/WEB-INF/pages/segments/navbar.jsp" />
	<h1 class="card profile-main text-center">View Order</h1>
	<div class="row d-flex justify-content-center"> 
		<div class="card profile-main col-lg-4 col-sm-10">
			<!-- Order Details -->
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
				
		<div class="card profile-main col-lg-6 col-sm-10">
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
			<c:if test="${orderInfo.isCancelable()}">
				<form:form method="post" action="/TrentMinia_MatthewNaruse_COMP303_Assignment2/modify-order">
					<button type="submit" class="btn btn-primary" name="modifyOrderId" value="${orderInfo.orderId}">Modify Order</button>
					<button type="submit" class="btn btn-danger" name="deleteOrder" value="${orderInfo.orderId}">Delete Order</button>
				</form:form>
			</c:if>
		</div>
	</div>
<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />