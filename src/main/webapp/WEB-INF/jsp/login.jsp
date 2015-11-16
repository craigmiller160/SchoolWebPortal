<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="stub/jstl-stub.jsp" %> <!-- Include JSTL Tag Library -->
<%@ include file="stub/spring-stub.jsp" %> <!-- Include Spring Tag Library -->

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<link rel="stylesheet" href="<c:url value="css/global-styles.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="css/login-styles.css"/>" type="text/css"/>

<link href="https://fonts.googleapis.com/css?family=Coda" rel="stylesheet" type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Limelight" rel="stylesheet" type='text/css'>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="login.page.title"/></title>
</head>
<body id="login-body">
	
	<%@ include file="component/header.jsp" %>
	
	<% //TODO background image??? %>
	
	<div class="wrapper">
		<div class="container-fluid main-content">
			
			<div id="login-title-row" class="row">
				<div class="col-md-12">
					<h3 class="login-banner">
						<spring:message code="login.banner.title"/>
					</h3>
				</div>
			</div>
			
			<div id="login-box-row" class="row">
				<div class="col-md-12">
					<c:if test="${not empty error}">
						<div id="login-error-div" class="action-box action-box-fail">
							<spring:message code="login.error"/>
						</div>
					</c:if>
				
				
					<table class="table-responsive" id="login-box-table">
						<tr>
							<td id="login-box-title-cell" colspan=2>
								<spring:message code="login.box.title"/>
							</td>
						</tr>
						<form:form action="login-process.html" method="post">
						<tr>
							<td class="login-label">
								<spring:message code="login.box.username"/>
							</td>
							<td class="login-field-cell">
								<input class="login-field form-control" type="text" 
									name="username"/>
							</td>
						</tr>
						<tr>
							<td class="login-label">
								<spring:message code="login.box.password"/>
							</td>
							<td class="login-field-cell">
								<input class="login-field form-control" 
									type="password" 
									name="password"/>
							</td>
						</tr>
						<tr>
							<td id="login-submit-cell" colspan=2>
								<input id="login-btn" class="btn btn-default" type="submit" 
									value="Login"/>
							</td>
						</tr>
						</form:form>
						<tr>
							<td class="login-link-cell" colspan=2>
								<a href="<c:url value="/login/forgot.html"/>">
									<spring:message code="login.box.forgot"/>
								</a>
							</td>
						</tr>
						<tr>
							<td class="login-link-cell" colspan=2>
								<a href="<c:url value="/login/create.html"/>">
									<spring:message code="login.box.create"/>
								</a>
							</td>
						</tr>
					</table>
					
					
				</div>
			</div>
			
		</div>
		<div class="push"></div>
	</div>
	
	<%@ include file="component/footer.jsp" %>
	
	<script>
	function resizeWindow(){
		var width = $(window).width();

		if(width < 1200){
			$('.wrapper').css('width', 100 + '%');
		}
		else{
			$('.wrapper').css('width', 1200 + 'px');
		}

		var headerHeight = $('.header').height();
		var mainContentHeight = $(window).height() 
			- headerHeight - $('.footer').height();
		$('.main-content').css({
			'margin-top' : headerHeight,
			'min-height' : mainContentHeight,
			'height' : mainContentHeight
			});
		$('#login-body').css('height', mainContentHeight);
		
	};


	$(document).ready(resizeWindow());
	$(document).ready(function(){
		$(window).on('resize', function(){
			resizeWindow();
		});
	});
	</script>


</body>
</html>