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



<!-- Shopping Cart List -->

<div class="container">
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
	    <a href="/TrentMinia_MatthewNaruse_COMP303_Assignment2/checkout" class="btn btn-primary">Checkout</a>
	</div>
</div>

<!-- Product Items -->


		   
<div class="container">
	<div class="card-columns">
		<c:forEach var="prod" items="${products}">
			<form:form method="POST"
			   action="/TrentMinia_MatthewNaruse_COMP303_Assignment2/addToCart"
			   modelAttribute="product">
				<div id="phoneCard" class="card">
					<img class="card-img-top" src="${prod.imgSrc}" alt="phone placeholder image">
					<h5 class="card-title">${prod.modelName}</h5>
					<h6 class="card-subtitle">${prod.brandName}</h6>
					<p class="text">$${prod.price} per unit.</p>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text">Quantity</span>
						</div>
						<input name="quantity" class="form-control" type="number" min="1" value="1"/>
					</div>
					<button name="product" class="btn btn-primary" type="submit" value="${prod.productId}">Add to Cart</button>
				</div>
			</form:form>
		</c:forEach>
	</div>
<!-- 	<div class="card">
	  <div class="card-body">
	    <h4 class="card-title">Items in Cart</h4>
	    <p class="card-text">
	      Don't forget to checkout to complete your order!
	    </p>
		<table class="table">
		<thead>
			<tr>
				<th>Product</th><th>Quantity</th><th>Price</th><th></th>
			</tr>
			
			<c:forEach var="order" items="${ordersList}">
			<tr>
				<td>${productname}</td><td>${quantity}</td><td>${price}</td><td><span class="btn btn-danger">Remove</span></td>
			</tr>
			</c:forEach>
			 
		</thead>
		<tbody>
			<tr>
				<td>Apple iPhone 12</td><td>1</td><td>$1399.99</td><td><span class="btn btn-danger">Remove</span></td>
			</tr>
			<tr>
				<td>Samsung Galaxy A71</td><td>1</td><td>$539.99</td><td><span class="btn btn-danger">Remove</span></td>
			</tr>
		</tbody>
		</table>
	    <a href="#!" class="btn btn-primary">Checkout</a>
	  </div>
	</div>
</div>
-->



<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />