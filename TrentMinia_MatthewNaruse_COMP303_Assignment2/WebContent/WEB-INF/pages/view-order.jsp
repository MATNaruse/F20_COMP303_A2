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
		<h1 class="text-center">View Order</h1>

			<table class="table">
			<thead>
				<tr>
					<th>Product</th><th>Quantity</th><th>Price</th><th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="orderItem" items="${orderItems}">
					<tr>
						<td>${orderItem.productName}</td>
						<td>${orderItem.quantity}</td>
						<td>${orderItem.totalPrice}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	    <a href="/TrentMinia_MatthewNaruse_COMP303_Assignment2/checkout" class="btn btn-primary">Checkout</a>
	</div>
<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />