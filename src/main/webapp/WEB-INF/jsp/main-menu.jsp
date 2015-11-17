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
<link rel="stylesheet" href="<c:url value="css/admin-styles.css"/>" type="text/css"/>

<link href="https://fonts.googleapis.com/css?family=Coda" rel="stylesheet" type='text/css'>
<link href="https://fonts.googleapis.com/css?family=Limelight" rel="stylesheet" type='text/css'>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="menu.page.title"/></title>
</head>
<body class="admin-body">
	
	<%@ include file="component/header.jsp" %>
	
	<div class="wrapper">
		<div class="container-fluid main-content">
			<div class="row admin-title-row">
				<h3 class="admin-banner">
					<% //TODO Internationalize text %>
					Web Portal Main Menu
				</h3>
			</div>
			
			<h1 style="text-align: center; margin-top: 200px">
				There will be an image here
			</h1>
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
		$('.admin-body').css('height', mainContentHeight);
		
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