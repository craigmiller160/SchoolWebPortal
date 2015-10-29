<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="stub/jstl-stub.jsp" %> <!-- Include JSTL Tag Library -->
<%@ include file="stub/css-stub.jsp" %> <!-- Include CSS Stylesheets -->
<%@ include file="stub/spring-stub.jsp" %> <!-- Include Spring Tag Library -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.main.page.title"/></title>
</head>
<body>

	<!-- Wrapper div for implemented FlexBox Layout -->
	<div class="wrapper">
		<!-- Page Header -->
		<header class="header-admin">
			<!-- Logo and link to welcome page in upper left of header -->
			<div class="logo">
				<a href="<c:url value="/welcome.html"/>"><img src="<c:url value="/img/ehslogo.png"/>" height="70px"/></a>
			</div>
			
			<!-- Page Title in header -->
			<div class="header-title">
				<h1><spring:message code="admin.main.header.title"/></h1>
			</div>
			
			<!-- Option to logout of web portal -->
			<div class="user-status">
				<form:form action="admin/logout.html" method="get">
					<input id="logout-btn" class="btn-link" type="submit" 
						name="logoutButton" 
						value="<spring:message code="general.logout"/>"/>
				</form:form>
			</div>
		</header>
		
		<!-- Container holding the main content for the page -->
		<div class="content-container">
		
			<!-- Main menu div -->
			<div id="admin-menu">
				<!-- Title for main menu -->
				<h3><spring:message code="admin.main.options.title"/></h3>
				
				<!-- Form with button to open the courses section -->
				<form:form action="admin/course/all.html" method="get">
					<div>
						<input type="hidden" name="page" value="1"/>
						<button class="options-button" type="submit">
							<spring:message code="admin.main.options.courses"/>
						</button>
					</div>
				</form:form>
				
				<!-- Form with button to open the students section -->
				<form:form action="admin/student/all.html" method="get">
					<div>
						<input type="hidden" name="page" value="1"/>
						<button class="options-button" type="submit">
							<spring:message code="admin.main.options.students"/>
						</button>
					</div>
				</form:form>
				
			</div>
		
		</div>
		
		<!-- Page footer -->
		<footer class="footer-admin">
			<h4><spring:message code="general.footer"/></h4>
		</footer>
	</div>

</body>
</html>