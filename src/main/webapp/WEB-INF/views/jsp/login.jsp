<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Security</title>
</head>
<body>

<jsp:include page="menu.jsp" />
<br />

<h1>Login</h1>

<div id="login">

	<c:if test="${ not empty error }">
		<div class="error">${error}</div>
	</c:if>
	
	<c:if test="${ not empty msg }">
		<div class="msg">${msg}</div>
	</c:if>
	
	<form name="loginForm" action="<c:url value='/login' />" method="POST">
		<table>
			<tr>
				<td>User:</td>
				<td><input name="principal" type="text" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input name="credentials" type="password" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
				  value="submit" /></td>
			</tr>
		</table>
		
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

	<br />
	<div>
		<a href="${pageContext.request.contextPath}/user/register">Register</a>
	</div>

</div>


</body>
</html>