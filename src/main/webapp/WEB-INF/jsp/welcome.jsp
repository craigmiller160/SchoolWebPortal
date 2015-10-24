<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="stub/jstl-stub.jsp" %>
<%@ include file="stub/css-stub.jsp" %>
<%@ include file="stub/spring-stub.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="welcome.page.title"/></title>
</head>
<body>

	<div class="wrapper">
		<header class="header-welcome">
			
			<div class="logo">
				<img src="<c:url value="/img/ehslogo.png"/>" height="70px"/>
			</div>
			
			<div class="header-title">
				<h1><spring:message code="welcome.header.title"/></h1>
			</div>
			
			<div class="user-status">
				<form:form method="get">
					<input class="btn-link" type="submit" 
						name="loginButton" 
						value="<spring:message code="welcome.header.login"/>"/>
				</form:form>
			</div>
		</header>
		
		<div class="content-container">
			<div>
				<img src="<c:url value="/img/highschoolbuilding.jpg"/>" width="100%"/>
			</div>
		</div>
		
		<footer class="footer-welcome">
			<h4>&copy; Copyright 2015 CraigMiller160</h4>
		</footer>
		
	</div>

	

</body>
</html>