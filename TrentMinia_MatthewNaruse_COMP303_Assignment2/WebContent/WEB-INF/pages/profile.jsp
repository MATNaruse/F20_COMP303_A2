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

<div class="row d-flex justify-content-center">
	<div class="card profile-main col-lg-3 col-sm-10">
		<h2 class="text-center">Profile</h2>
		<c:if test="${not empty out_msg}">
			<c:choose>
				<c:when test="${out_msg == 'Profile Updated Successfully!' }">
					<div class="alert alert-success text-center" >${out_msg}</div>
				</c:when>
				<c:otherwise>
					<div class="alert alert-danger text-center" >${out_msg}</div>
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
						<td>Username</td><td>${cust.userName}</td>
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
		<c:when test="${not empty ordersList}">
			<div class="card profile-orders col-lg-8 col-sm-10">
				<h2>Your Orders</h2>
				<form:form method="post" action="/TrentMinia_MatthewNaruse_COMP303_Assignment2/view-order">
					<table class="table">
					<tr>
						<th>Order ID</th>
						<th>Delivery Date</th>
						<th>Order Status</th>
						<th></th>
					</tr>
						<c:forEach var="order" items="${ordersList}">
							<tr>
								<td>${order.orderId}</td>
								<td>${order.deliveryDate}</td>
								<td>${order.orderStatus}</td>
								<td><button type="submit" class="btn btn-primary" name="view-orderId" value="${order.orderId}">View</button></td>
							</tr>
						</c:forEach>
					</table>
				</form:form>
			</div>
		</c:when>
		<c:otherwise>
			<div class="card profile-main text-center col-lg-8 col-sm-10">
				<h2 class="font-italic">You have no orders!</h2>
			</div>
		</c:otherwise>
	</c:choose>
</div>
<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />