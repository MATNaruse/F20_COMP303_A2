<!-- 
	COMP303-001 Assignment 2
	Due Date: Nov 02, 2020
	Submitted: ??? ## 2020
	301 041 132 : Trent Minia
	300 549 638 : Matthew Naruse
 -->
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<jsp:include page="/WEB-INF/pages/segments/header.jsp" />
<jsp:include page="/WEB-INF/pages/segments/navbar.jsp" />

<form:form method="POST"
			action="/TrentMinia_MatthewNaruse_COMP303_Assignment2/newregister"
			modelAttribute="customer"
			class="card text-center form-signin">
	<h1>Register</h1>
	<form:input class="form-control" id="userName" path="userName" placeholder="Username"/>
	<form:password class="form-control" id="password" path="password" placeholder="Password"/>
	<form:input class="form-control" id="firstName" path="firstname" placeholder="First Name"/>
	<form:input class="form-control" id="lastName" path="lastname" placeholder="Last Name"/>
	<form:input class="form-control" id="address" path="address" placeholder="Address"/>
	<form:input class="form-control" id="city" path="city" placeholder="City"/>
	<form:input class="form-control" id="postalCode" path="postalCode" placeholder="Postal Code"/>
	<form:input class="form-control" id="country" path="country" placeholder="Country"/>
	
	<input class="btn btn-primary" type="submit" value="Register New Account"/>
</form:form>

<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />