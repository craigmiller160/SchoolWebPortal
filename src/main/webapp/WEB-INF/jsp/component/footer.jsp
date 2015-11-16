<!-- THE FOOTER FOR PAGES OF THIS APPLICATION -->
<!-- IMPORTANT - The JSTL, Bootstrap, Spring Tags, and JavaScript imports 
	are required for this and all components -->

<% //TODO the style names should be more universal than just header %>

<c:if test="${pageName == 'welcome'}">
	<c:set var="headerStyle" value="header-welcome"/>
	<c:set var="headerTextStyle" value="header-welcome-text"/>
</c:if>

<c:if test="${pageName == 'login'}">
	<c:set var="headerStyle" value="header-login"/>
	<c:set var="headerTextStyle" value="header-login-text"/>
</c:if>

<footer class="footer ${headerStyle}">
	<div class="wrapper">
		<div class="container-fluid">
			<p class="${headerTextStyle}">
				<spring:message code="general.footer"/>
			</p>
		</div>
	</div>
</footer>