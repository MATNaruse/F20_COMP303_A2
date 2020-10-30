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
			action="/TrentMinia_MatthewNaruse_COMP303_Assignment2/trylogin"
			modelAttribute="customer"
			class="card text-center form-signin">

	<h1 class="card-title">Login</h1>
	<form:label for="userName" path="userName">User Name</form:label> 
	<form:input id="userName" class="form-control" placeholder="Username" path="userName" required="required"/>
	<form:label for="password" path="password">Password</form:label>
	<form:password id="password" class="form-control" placeholder="Password" path="password" required="required"/>
	
	<input class="btn btn-primary" type="submit" value="Log In"/>
</form:form>

<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />