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
<link rel="stylesheet" href="<c:url value="css/welcome-styles.css"/>" type="text/css"/>

<link href="https://fonts.googleapis.com/css?family=Coda" rel="stylesheet" type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Limelight" rel="stylesheet" type='text/css'>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="welcome.page.title"/></title>
</head>
<body id="welcome-body">
	<%@ include file="component/header.jsp" %>
	
	<div class="welcome-background-img">
		<img src="<c:url value="img/student-background.jpg"/>"
			id="student-background"/>
	</div>

	<div class="wrapper">
		
		<div class="container-fluid main-content">
			
			<div id="welcome-title-row" class="row">
				<div class="col-md-6">
					<h3 class="welcome-banner">
						<spring:message code="welcome.banner.title1"/>
					</h3>
				</div>
				<div class="col-md-6">
					<h3 class="welcome-banner">
						<spring:message code="welcome.banner.title2"/>
					</h3>
				</div>
			</div>
			
			<div class="row">
				<img src="<c:url value="img/highschoolbuilding.jpg"/>"
					class="img-responsive" id="building-img"/>
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<div class="page-header welcome-page-header">
						<h3 class="welcome-banner">
							<spring:message code="welcome.academic.title"/>
						</h3>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-4 welcome-details">
					<img src="<c:url value="img/classroom1.jpg"/>"
						class="img-responsive welcome-img"/>
					<h4>
						<spring:message code="welcome.academic.class"/>
					</h4>
				</div>
				<div class="col-md-4 welcome-details">
					<img src="<c:url value="img/classroom2.jpg"/>"
						class="img-responsive welcome-img"/>
					<h4>
						<spring:message code="welcome.academic.students"/>
					</h4>
				</div>
				<div class="col-md-4 welcome-details">
					<img src="<c:url value="img/classroom3.jpg"/>"
						class="img-responsive welcome-img"/>
					<h4>
						<spring:message code="welcome.academic.instructors"/>
					</h4>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-12">
					<div class="page-header welcome-page-header">
						<h3 class="welcome-banner">
							<spring:message code="welcome.athletic.title"/>
						</h3>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-4 welcome-details">
					<img src="<c:url value="img/athletics-track.jpg"/>"
						class="img-responsive welcome-img"/>
					<h4>
						<spring:message code="welcome.athletic.fitness"/>
					</h4>
				</div>
				<div class="col-md-4 welcome-details">
					<img src="<c:url value="img/athletics-football.jpg"/>"
						class="img-responsive welcome-img"/>
					<h4>
						<spring:message code="welcome.athletic.football"/>
					</h4>
				</div>
				<div class="col-md-4 welcome-details">
					<img src="<c:url value="img/athletics-women.jpg"/>"
						class="img-responsive welcome-img"/>
					<h4>
						<spring:message code="welcome.athletic.team"/>
					</h4>
				</div>
			</div>
			
			<div class="push"></div>
		</div>
		
		
	
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