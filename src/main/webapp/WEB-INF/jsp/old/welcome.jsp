<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../stub/jstl-stub.jsp" %> <!-- Include JSTL Tag Library -->
<%@ include file="../stub/js-stub.jsp" %> <!-- Include JQuery -->
<%@ include file="../stub/bootstrap-stub.jsp" %> <!-- Include Bootstrap Dependencies -->
<%@ include file="../stub/css-stub.jsp" %> <!-- Include CSS Stylesheets -->
<%@ include file="../stub/spring-stub.jsp" %> <!-- Include Spring Tag Library -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="welcome.page.title"/></title>
</head>
<body>
	
	<!-- Wrapper div for implemented FlexBox Layout -->
	<div class="wrapper">
		<!-- Page Header -->
		<header class="header-welcome">
			<!-- Logo in upper left of header -->
			<div class="logo">
				<img src="<c:url value="/img/ehslogo.png"/>" height="70px"/>
			</div>
			
			<!-- Page Title in header -->
			<div class="header-title">
				<h1><spring:message code="welcome.header.title"/></h1>
			</div>
			
			<!-- Option to Login to Web Portal -->
			<div class="user-status">
				<form:form method="get">
					<input class="btn-link" type="submit" 
						name="loginButton" 
						value="<spring:message code="welcome.header.login"/>"/>
				</form:form>
			</div>
		</header>
		
		<!-- Container holding the main content for the page -->
		<div class="content-container">
			<!-- Large image of a high school building -->
			<div>
				<img src="<c:url value="/img/highschoolbuilding.jpg"/>" width="100%"/>
			</div>
		</div>
		
		<!-- Page footer -->
		<footer class="footer-welcome">
			<h4><spring:message code="general.footer"/></h4>
		</footer>
		
	</div>
	
	<script>
		function docWidth(){
			var width = $(document).width();
			$('header').width(width);
			$('footer').width(width);
		};

		$(document).ready(docWidth());
		$(document).ready(function(){
			$(window).resize(docWidth());
		});
	</script>

</body>
</html>