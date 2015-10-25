<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="stub/jstl-stub.jsp" %>
<%@ include file="stub/css-stub.jsp" %>
<%@ include file="stub/spring-stub.jsp" %>
<%@ include file="stub/js-stub.jsp" %>
<%@ include file="stub/bootstrap-tab-js-stub.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="login.page.title"/></title>
</head>
<body>

	<div class="wrapper">
		<header class="header-login">
			<div class="logo">
				<a href="<c:url value="/welcome.html"/>"><img src="<c:url value="/img/ehslogo.png"/>" height="70px"/></a>
			</div>
			
			<div class="header-title">
				<h1><spring:message code="login.header.title"/></h1>
			</div>
		
		</header>
		
		<div class="content-container">
		
			<h3 id="login-instruction"><spring:message code="login.instruction"/></h3>
		
			<form:form method="post">
				<div class="container">
					<div id="login-tabs">
						<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
							<li class="active"><a href="#admintab" data-toggle="tab"><spring:message code="login.tabs.admin.tab"/></a></li>
							<li><a href="#studenttab" data-toggle="tab"><spring:message code="login.tabs.student.tab"/></a>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active login-tab-content" id="admintab">
								<h4><spring:message code="login.tabs.admin.description"/></h4>
								<input class="btn login-btn" name="admin" type="submit" value="<spring:message code="login.tabs.loginbutton"/>" />
							</div>
							<div class="tab-pane login-tab-content" id="studenttab">
								<h4><spring:message code="login.tabs.student.description"/></h4>
								<input class="btn login-btn" name="student" type="submit" value="<spring:message code="login.tabs.loginbutton"/>" />
							</div>
						</div>
					</div>
				</div>
			</form:form>
		
		</div>
		
		<footer class="footer-login">
			<h4>&copy; Copyright 2015 CraigMiller160</h4>
		</footer>
	
	
	</div>

</body>
</html>