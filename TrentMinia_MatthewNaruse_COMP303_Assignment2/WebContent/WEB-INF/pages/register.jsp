<!-- 
	COMP303-001 Assignment 2
	Due Date: Nov 04, 2020
	Submitted: Nov 04, 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
 -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/WEB-INF/pages/segments/header.jsp" />
<jsp:include page="/WEB-INF/pages/segments/navbar.jsp" />

<form:form method="POST"
			action="/TrentMinia_MatthewNaruse_COMP303_Assignment2/newregister"
			modelAttribute="customer"
			class="card form-signin">
	<h1 class="text-center">Register</h1>
	
	<c:if test="${not empty out_msg}">
		<div class="alert alert-danger" >${out_msg}</div>
	</c:if>
	
	<form:label for="userName" path="userName">User Name</form:label>
	<form:input class="form-control" id="userName" path="userName" placeholder="Username"/>
	
	<form:label for="password" path="password">Password</form:label>
	<form:password class="form-control" id="password" path="password" placeholder="Password"/>
	<hr>
	<form:label for="firstName" path="firstname">First Name</form:label>
	<form:input class="form-control" id="firstName" path="firstname" placeholder="First Name"/>
	
	<form:label for="lastName" path="lastname">Last Name</form:label>
	<form:input class="form-control" id="lastName" path="lastname" placeholder="Last Name"/>
	<hr>
	<form:label for="address" path="address">Address</form:label>
	<form:input class="form-control" id="address" path="address" placeholder="Address"/>
	
	<form:label for="city" path="city">City</form:label>
	<form:input class="form-control" id="city" path="city" placeholder="City"/>
	
	<form:label for="country" path="country">Country</form:label>
	<form:input class="form-control" id="country" path="country" placeholder="Country"/>
	
	<form:label for="postalCode" path="postalCode">Postal Code</form:label>
	<form:input class="form-control" id="postalCode" path="postalCode" placeholder="Postal Code"/>

	
	<input class="btn btn-primary" type="submit" value="Register New Account"/>
</form:form>

<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />