<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="stub/jstl-stub.jsp" %> <!-- Include JSTL Tag Library -->
<%@ include file="stub/bootstrap-stub.jsp" %> <!-- Include Bootstrap Dependencies -->
<%@ include file="stub/css-stub.jsp" %> <!-- Include CSS Stylesheets -->
<%@ include file="stub/spring-stub.jsp" %> <!-- Include Spring Tag Library -->
<%@ include file="stub/js-stub.jsp" %> <!-- Include JQuery -->
<%@ include file="stub/bootstrap-tab-stub.jsp" %> <!-- Include Bootstrap Tab Dependencies -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="login.page.title"/></title>
</head>
<body>

	<!-- Wrapper div for implemented FlexBox Layout -->
	<div class="wrapper">
		<!-- Page Header -->
		<header class="header-login">
			<!-- Logo and link to welcome page in upper left of header -->
			<div class="logo">
				<a href="<c:url value="/welcome.html"/>"><img src="<c:url value="/img/ehslogo.png"/>" height="70px"/></a>
			</div>
			
			<!-- Page Title in header -->
			<div class="header-title">
				<h1><spring:message code="login.header.title"/></h1>
			</div>
		
		</header>
		
		<!-- Container holding the main content for the page -->
		<div class="content-container">
			
			<!-- Instructions for logging in -->
			<h3 id="login-instruction"><spring:message code="login.instruction"/></h3>
		
			<!-- Form for handling the login request -->
			<form:form method="post">
				<!-- Bootstrap tab pane, with some modifications, 
				for switching between admin/student login options -->
				<div class="container">
					<div id="login-tabs">
						<!-- The actual tabs in the bootstrap tab pane -->
						<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
							<!-- Admin Tab, default active -->
							<li class="active">
								<a href="#admintab" data-toggle="tab">
									<spring:message code="login.tabs.admin.tab"/>
								</a>
							</li>
							<!-- Student Tab -->
							<li>
								<a href="#studenttab" data-toggle="tab">
									<spring:message code="login.tabs.student.tab"/>
								</a>
							</li>
						</ul>
						<!-- The content in each tab -->
						<div class="tab-content">
							<!-- Admin Tab Content, default active -->
							<div class="tab-pane active login-tab-content" id="admintab">
								<h4><spring:message code="login.tabs.admin.description"/></h4>
								<input class="btn login-btn" name="admin" type="submit" 
									value="<spring:message code="login.tabs.loginbutton"/>" />
							</div>
							<!-- Student Tab Content -->
							<div class="tab-pane login-tab-content" id="studenttab">
								<h4><spring:message code="login.tabs.student.description"/></h4>
								<input class="btn login-btn" name="student" type="submit" 
									value="<spring:message code="login.tabs.loginbutton"/>" />
							</div>
						</div>
					</div>
				</div>
			</form:form>
		
		</div>
		
		<!-- Page footer -->
		<footer class="footer-login">
			<h4><spring:message code="general.footer"/></h4>
		</footer>
	
	
	</div>

</body>
</html>