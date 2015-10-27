<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="stub/jstl-stub.jsp" %>
<%@ include file="stub/css-stub.jsp" %>
<%@ include file="stub/spring-stub.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.main.page.title"/></title>
</head>
<body>

	<div class="wrapper">
		<header class="header-admin">
			<div class="logo">
				<a href="<c:url value="/welcome.html"/>"><img src="<c:url value="/img/ehslogo.png"/>" height="70px"/></a>
			</div>
			
			<div class="header-title">
				<h1><spring:message code="admin.main.header.title"/></h1>
			</div>
			
			<div class="user-status">
				<form:form action="admin/logout.html" method="get">
					<input id="logout-btn" class="btn-link" type="submit" 
						name="logoutButton" 
						value="<spring:message code="general.logout"/>"/>
				</form:form>
			</div>
		</header>
		
		<div class="content-container">
		
			<div id="admin-menu">
				
				<h3><spring:message code="admin.main.options.title"/></h3>
				
				<form:form action="admin/course/all.html" method="get">
					<div>
						<input type="hidden" name="page" value="1"/>
						<button class="options-button" type="submit">
							<spring:message code="admin.main.options.courses"/>
						</button>
					</div>
				</form:form>
				
				<form:form action="admin/student/all.html" method="get">
					<div>
						<button class="options-button" type="submit"
							name="students" value="all">
							<spring:message code="admin.main.options.students"/>
						</button>
					</div>
				</form:form>
				
			</div>
		
		</div>
		
		<footer class="footer-admin">
			<h4><spring:message code="general.footer"/></h4>
		</footer>
	</div>

</body>
</html>