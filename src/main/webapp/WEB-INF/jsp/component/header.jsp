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

<% //TODO ultimately test to make sure all access level color schemes work %>

<sec:authorize access="hasRole('USER')">
	<c:set var="headerStyle" value="header-welcome"/>
	<c:set var="headerTextStyle" value="header-welcome-text"/>
</sec:authorize>

<sec:authorize access="hasRole('STUDENT')">
	<% //TODO add student page color scheme here %>
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
	<c:set var="headerStyle" value="header-admin"/>
	<c:set var="headerTextStyle" value="header-admin-text"/>
</sec:authorize>

<sec:authorize access="hasRole('MASTER')">
	<% //TODO add master page color scheme here %>
</sec:authorize>

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
				
				<c:if test="${pageName != 'welcome' && pageName != 'login'}">
					<%@ include file="portal-header.jsp" %>
				</c:if>
				
				<ul class="nav navbar-nav navbar-right">
					<c:if test="${pageName == 'welcome'}">
						<li>
							<a href="<c:url value="portal.html"/>" class="${headerTextStyle}">
								<spring:message code="welcome.header.login"/>
							</a>
						</li>
					</c:if>
					
					<c:if test="${pageName != 'welcome' && pageName != 'login'}">
						<li class="dropdown">
							<a href="#" class="${headerTextStyle} dropdown-toggle"
								data-toggle="dropdown">
								${userName} <span class="caret"></span>
							</a>
							<ul class="dropdown-menu" id="user-dropdown">
								<li>
									<a href="#">
										<spring:message code="portal.header.user.profile"/>
									</a>
								</li>
								<li>
									<a id="logout-link" href="#">
										<spring:message code="portal.header.user.logout"/>
									</a>
									<form:form id="logout-form" action="logout.html" 
										method="post">
									</form:form>
								</li>
							</ul>
						</li>
					</c:if>
				</ul>
			</div>
		</div>
	</div>
</nav>

<script>
$(document).ready(function(){
	$('#logout-link').click(function(){
		$('#logout-form').submit();
	});
});
</script>