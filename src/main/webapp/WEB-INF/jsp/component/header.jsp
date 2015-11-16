<!-- THE HEADER FOR PAGES OF THIS APPLICATION -->
<!-- IMPORTANT - The JSTL, Bootstrap, Spring Tags, and JavaScript imports 
	are required for this and all components -->

<link rel="stylesheet" href="<c:url value="/css/header-styles.css"/>" type="text/css"/>

<c:if test="${pageName == 'welcome'}">
	<c:set var="headerStyle" value="header-welcome"/>
	<c:set var="headerTextStyle" value="header-welcome-text"/>
</c:if>

<c:if test="${pageName == 'login'}">
	<c:set var="headerStyle" value="header-login"/>
	<c:set var="headerTextStyle" value="header-login-text"/>
</c:if>

<!-- Bootstrap navbar as the header -->

<nav class="navbar navbar-fixed-top navbar-default header ${headerStyle}">
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
					<a href="<c:url value="/welcome.html"/>" class="${headerTextStyle}">
						<img id="school-logo" src="<c:url value="/img/ehslogo.png"/>" 
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
					<c:if test="${pageName == 'welcome'}">
						<li>
							<a href="<c:url value="portal.html"/>" class="${headerTextStyle}">
								<spring:message code="welcome.header.login"/>
							</a>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</nav>