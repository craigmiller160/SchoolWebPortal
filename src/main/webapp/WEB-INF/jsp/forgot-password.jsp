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

<link rel="stylesheet" href="<c:url value="/css/global-styles.css"/>" type="text/css"/>
<link rel="stylesheet" href="<c:url value="/css/login-styles.css"/>" type="text/css"/>

<link href="https://fonts.googleapis.com/css?family=Coda" rel="stylesheet" type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Limelight" rel="stylesheet" type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="login.forgot.page.title"/></title>
</head>
<body id="login-body">
	
	<% //TODO this page does nothing at the moment, need to implement 
		//send token/reset functionality %>
	
	<%@ include file="component/header.jsp" %>
	
	<div class="wrapper">
		<div class="container-fluid main-content">
			<div id="login-title-row" class="row">
				<div class="col-md-12">
					<h3 class="login-banner">
						<spring:message code="login.forgot.banner.title"/>
					</h3>
				</div>
			</div>
			
			<div class="forgot-instructions row">
				<c:if test="${not empty codeSent}">
					<div id="reset-code-sent" class="action-box action-box-success">
						<spring:message code="login.forgot.code.success"/>
					</div>
				</c:if>
				
				<div class="col-md-12">
					<h4>
						<spring:message code="login.forgot.code.instruction"/>
					</h4>
				</div>
			</div>
			<form:form action="forgot.html" method="post">
				<div class="row">
					<div class="col-md-3">
						<!-- Empty div to assist column spacing -->
					</div>
					<div class="col-md-2 forgot-label-cell">
						<h4>
							<spring:message code="login.forgot.code.username"/>
						</h4>
					</div>
					<div class="col-md-2 forgot-field-cell">
						<input class="form-control" type="text" name="username"/>
					</div>
					<div class="col-md-2 forgot-submit-cell">
						<input class="btn btn-default" type="submit" 
							value="<spring:message code="login.forgot.code.send"/>"/>
					</div>
				</div>
			</form:form>
		
			
			
			<div class="row">
				<div class="forgot-instructions col-md-12">
					<h4>
						<spring:message code="login.forgot.reset.instruction"/>
					</h4>
				</div>
			</div>
			<form:form action="forgot.html" commandName="passReset" method="put">
			<div class="row">
				<div class="col-md-3">
				
				</div>
				
				<div class="col-md-3 forgot-label-cell">
					<h4 class="forgot-label">
						<spring:message code="login.forgot.reset.code"/>
					</h4>
				</div>
				
				<div class="col-md-3 forgot-field-cell">
					<input class="form-control" type="text" name="resetCode"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
				
				</div>
				
				<div class="col-md-3 forgot-label-cell">
					<h4 class="forgot-label">
						<spring:message code="login.forgot.reset.username"/>
					</h4>
				</div>
				
				<div class="col-md-3 forgot-field-cell">
					<input class="form-control" type="text" name="userName"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
				
				</div>
				
				<div class="col-md-3 forgot-label-cell">
					<h4 class="forgot-label">
						<spring:message code="login.forgot.reset.password1"/>
					</h4>
				</div>
				
				<div class="col-md-3 forgot-field-cell">
					<input class="form-control" 
						type="password" name="newPassword"/>
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
				
				</div>
				
				<div class="col-md-3 forgot-label-cell">
					<h4 class="forgot-label">
						<spring:message code="login.forgot.reset.password2"/>
					</h4>
				</div>
				
				<div class="col-md-3 forgot-field-cell">
					<input class="form-control" 
						type="password" name="newPasswordRepeat"/>
				</div>
			</div>
			<div id="forgot-last-row" class="row">
				<div class="forgot-submit-cell col-md-12">
					<input type="submit" class=" btn btn-default"
						value="<spring:message code="login.forgot.reset.reset"/>"/>
				</div>
			</div>
			</form:form>
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