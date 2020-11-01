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

<h1>Order</h1>

<!-- Shopping Cart List -->

<div class="container">
	<div class="card">
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
			<!--
			<c:forEach var="order" items="${ordersList}">
			<tr>
				<td>${productname}</td><td>${quantity}</td><td>${price}</td><td><span class="btn btn-danger">Remove</span></td>
			</tr>
			</c:forEach>
			  -->
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

<div class="container">
	<div class="row">

		<!-- Apple iPhone 12  -->
		
		<div class="col">
			<div class="card" style="width: 18rem;">
			  <img class="card-img-top" 
			  	   src="https://store.storeimages.cdn-apple.com/4982/as-images.apple.com/is/iphone-12-pro-family-hero?wid=926&amp;hei=1112&amp;fmt=jpeg&amp;qlt=80&amp;op_usm=0.5,0.5&amp;.v=1602088412000" 
			  	   alt="Apple iPhone 12">
			  <div class="card-body">
			    <h5 class="card-title">Apple iPhone 12</h5>
			    <p class="card-text">Never worry about your phone breaking with Ceramic Shield technology.
			    					 Features an A14 Bionic chip that makes it the most powerful smartphone
			    					 on the market.</p>
			  </div>
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">Brand: Apple</li>
			    <li class="list-group-item">Model: iPhone 12 (D53GAP)</li>
			    <li class="list-group-item">Price: $1399.99</li>
			  </ul>
			  <div class="card-body">
			  	<label for="quantity">Quantity: </label>
			  	<input type="number" id="quantity" class="form-control" size="1" value="0">
			    <button type="button" class="btn btn-primary">Add to Order</button>
			  </div>
			</div>
		</div>
		
		<!-- Samsung Galaxy S20 -->
		
		<div class="col">
			<div class="card" style="width: 18rem;">
			  <img class="card-img-top" 
			  	   src="https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcRJWB_Uh3w8D270TQubPgVUlWV62hZeXlWAiFpFpmkHWsUtiK27_Q&usqp=CAc" 
			  	   style="max-height: 400px"
			  	   alt="Samsung Galaxy S20">
			  <div class="card-body">
			    <h5 class="card-title">Samsung Galaxy S20</h5>
			    <p class="card-text">Features revolutionary 8K Video Snap technology, 5G mobile capability,
			    					 Samsung Knox security, long-lasting battery, a powerful processor, and
			    					 massive storage.</p>
			  </div>
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">Brand: Samsung</li>
			    <li class="list-group-item">Model: Galaxy S20</li>
			    <li class="list-group-item">Price: $1399.99</li>
			  </ul>
			  <div class="card-body">
			  	<label for="quantity">Quantity: </label>
			  	<input type="number" id="quantity" class="form-control" size="1" value="0">
			    <button type="button" class="btn btn-primary">Add to Order</button>
			  </div>
			</div>
		</div>
		
		<!-- Samsung Galaxy A71 -->
		
		<div class="col">
			<div class="card" style="width: 18rem;">
			  <img class="card-img-top" 
			  	   src="https://images-na.ssl-images-amazon.com/images/I/61G9VC33fsL._AC_SL1500_.jpg" 
			  	   style="max-height: 400px"
			  	   alt="Samsung Galaxy A71">
			  <div class="card-body">
			    <h5 class="card-title">Samsung Galaxy A71</h5>
			    <p class="card-text">Awesome screen. Awesome camera. Long lasting battery life.</p>
			  </div>
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">Brand: Samsung</li>
			    <li class="list-group-item">Model: Galaxy A71</li>
			    <li class="list-group-item">Price: $539.99</li>
			  </ul>
			  <div class="card-body">
			  	<label for="quantity">Quantity: </label>
			  	<input type="number" id="quantity" class="form-control" size="1" value="0">
			    <button type="button" class="btn btn-primary">Add to Order</button>
			  </div>
			</div>
		</div>
		
		<!-- LG G7-One -->
		
		<div class="col">
			<div class="card" style="width: 18rem;">
			  <img class="card-img-top" 
			  	   src="https://fdn2.gsmarena.com/vv/bigpic/lg-g7-one.jpg" 
			  	   style="max-height: 400px"
			  	   alt="LG G7 One">
			  <div class="card-body">
			    <h5 class="card-title">LG G7 One</h5>
			    <p class="card-text">Take full control over your phone with a bloatware-free Android. Stay 
			    					 up to date with monthly Android security updates. Listen to your music
			    					 at a whole new level with DTS: X 3D Surround Sound.</p>
			  </div>
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">Brand: LG</li>
			    <li class="list-group-item">Model: G7 One (LMQ910UM)</li>
			    <li class="list-group-item">Price: $799.99</li>
			  </ul>
			  <div class="card-body">
			  	<label for="quantity">Quantity: </label>
			  	<input type="number" id="quantity" class="form-control" size="1" value="0">
			    <button type="button" class="btn btn-primary">Add to Order</button>
			  </div>
			</div>
		</div>
		
		<!-- Apple iPhone 11 -->
		
		<div class="col">
			<div class="card" style="width: 18rem;">
			  <img class="card-img-top" 
			  	   src="https://boltmobile.ca/wp-content/uploads/2019/09/iphone11-website-device-images-iphone11-purple.jpg" 
			  	   alt="Apple iPhone 11">
			  <div class="card-body">
			    <h5 class="card-title">Apple iPhone 11</h5>
			    <p class="card-text">A dual-camera system allows you to take photos from wide to ultra wide.
			    					 Take professional 4K, 60fps videos.</p>
			  </div>
			  <ul class="list-group list-group-flush">
			    <li class="list-group-item">Brand: Apple</li>
			    <li class="list-group-item">Model: iPhone 11</li>
			    <li class="list-group-item">Price: $849.99</li>
			  </ul>
			  <div class="card-body">
			  	<label for="quantity">Quantity: </label>
			  	<input type="number" id="quantity" class="form-control" size="1" value="0">
			    <button type="button" class="btn btn-primary">Add to Order</button>
			  </div>
			</div>
		</div>
	
	</div>

</div>

<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />