<!-- THE HEADER FOR PAGES OF THIS APPLICATION -->
<!-- IMPORTANT - The JSTL, Bootstrap, Spring Tags, and JavaScript imports 
	are required for this and all components -->

<link rel="stylesheet" href="<c:url value="css/header-styles.css"/>" type="text/css"/>

<c:if test="${pageName == 'welcome'}">
	<c:set var="headerWelcome" value="header-welcome"/>
	<c:set var="headerWelcomeText" value="header-welcome-text"/>
</c:if>

<!-- Bootstrap navbar as the header -->

<nav class="navbar navbar-fixed-top navbar-default header ${headerWelcome}">
	<div class="wrapper">
		<div class="container-fluid">
			<!-- The header portion of the navbar -->
			<div class="navbar-header">
				<!-- The toggle for expand/collapse navbar on small screen sizes -->
				<button type="button" class="navbar-toggle"
					data-toggle="collapse" data-target="#mainNavBar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<!-- The logo for the school, plus a link back to this page -->
				<% //TODO consider changing the value of this URL based on
					//if logged in or not%>
				<div>
					<a href="<c:url value="/welcome.html"/>" class="${headerWelcomeText}">
						<img id="school-logo" src="<c:url value="img/ehslogo.png"/>" 
							height="50px" class="img-circle"/>
					</a>
					<label id="logo-text" for="school-logo">
						<span id="logo-title1">
							<spring:message code="general.header.logo.title1"/><br/>
						</span>
						<span id="logo-title2">
							<spring:message code="general.header.logo.title2"/>
						</span>
					</label>
				</div>
					
			</div>
			
			<div class="collapse navbar-collapse" id="mainNavBar">
				<c:if test="${pageName == 'welcome'}">
					<%@ include file="welcome-header.jsp" %>
				</c:if>
				
				
				<ul class="nav navbar-nav navbar-right">
					<li>
						<% //TODO this won't always be login, needs to be adjustable %>
						<a href="<c:url value="login.html"/>" class="${headerWelcomeText}">
							<spring:message code="welcome.header.login"/>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</nav>