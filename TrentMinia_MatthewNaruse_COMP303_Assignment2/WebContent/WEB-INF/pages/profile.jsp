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

<script src="js/profile.js"></script>

<div class="card-group">
	<div class="card profile-main">
		<h2 class="text-center">Profile</h2>
		<c:if test="${not empty out_msg}">
			<c:choose>
				<c:when test="${out_msg == 'Profile Updated Successfully!' }">
					<div class="alert alert-success" >${out_msg}</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger" >${out_msg}</div>
				</c:otherwise>
			</c:choose>
		</c:if>
		<div id="profileDetails" class="text-center">
			<table class="table text-left">
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
			<button class="btn btn-primary" onClick="EditProfileTOGG()">Edit</button>
		</div>
		<div id="profileEditDetails" style="display:none;" class="text-center">
			<form:form method="POST" modelAttribute="cust" action="/TrentMinia_MatthewNaruse_COMP303_Assignment2/save-profile">
				<table class="table text-left">
					<tr>
						<td><form:label for="userName" path="userName">User Name</form:label></td>
						<td><form:input class="form-control" id="userName" path="userName" placeholder="Username" value="${cust.userName}"/></td>
					</tr>
					<tr>
						<td><form:label for="password" path="password">Password</form:label></td>
						<td><form:password class="form-control" id="password" path="password" placeholder="Password" value="${cust.password}"/></td>
					</tr>
					<tr>
						<td><form:label for="firstName" path="firstname">First Name</form:label></td>
						<td><form:input class="form-control" id="firstName" path="firstname" placeholder="First Name" value="${cust.getFirstname()}"/></td>
					</tr>				
					<tr>
						<td><form:label for="lastName" path="lastname">Last Name</form:label></td>
						<td><form:input class="form-control" id="lastName" path="lastname" placeholder="Last Name" value="${cust.getLastname()}"/></td>
					</tr>
					<tr>
						<td><form:label for="address" path="address">Address</form:label></td>
						<td><form:input class="form-control" id="address" path="address" placeholder="Address" value="${cust.getAddress()}"/></td>
					</tr>
					<tr>
						<td><form:label for="city" path="city">City</form:label></td>
						<td><form:input class="form-control" id="city" path="city" placeholder="City" value=""/></td>
					</tr>
					<tr>
						<td><form:label for="country" path="country">Country</form:label></td>
						<td><form:input class="form-control" id="country" path="country" placeholder="Country" value="${cust.getCountry()}"/></td>
					</tr>
					<tr>
						<td><form:label for="postalCode" path="postalCode">Postal Code</form:label></td>
						<td><form:input class="form-control" id="postalCode" path="postalCode" placeholder="Postal Code" value="${cust.getPostalCode()}"/></td>
					</tr>
					
				</table>
				<input class="btn btn-primary" type="submit" value="Save Changes"/>
			</form:form>
		</div>
	</div>
	<c:choose>
		<c:when test="${empty ordersList}">
			<div class="card profile-orders text-center">
				<h2>Your Orders</h2>
				<table class="table text-left">
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
		</c:when>
		<c:otherwise>
		<div class="card profile-main text-center">
			<h2 class="font-italic">You have no orders!</h2>
		</div>
		</c:otherwise>
	</c:choose>
</div>
<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />