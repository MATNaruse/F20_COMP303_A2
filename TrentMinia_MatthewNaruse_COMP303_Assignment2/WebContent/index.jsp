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

<h1>Login</h1>

<form:form method="POST">
	
	<form:label class="sr-only" path="userName">User Name</form:label>
	<form:input class="form-control" path="userName"/>
	
	<form:label class="sr-only" path="password">Password</form:label>
	<form:input class="form-control" path="password"/>
	
	<input type="submit" value="Log In"/>
</form:form>

<jsp:include page="/WEB-INF/pages/segments/footer.jsp" />