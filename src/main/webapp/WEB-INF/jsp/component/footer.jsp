<!-- THE FOOTER FOR PAGES OF THIS APPLICATION -->
<!-- IMPORTANT - The JSTL, Bootstrap, Spring Tags, and JavaScript imports 
	are required for this and all components -->

<c:if test="${pageName == 'welcome'}">
	<c:set var="headerWelcome" value="header-welcome"/>
	<c:set var="headerWelcomeText" value="header-welcome-text"/>
</c:if>

<footer class="footer ${headerWelcome}">
	<div class="wrapper">
		<div class="container-fluid">
			<p class="${headerWelcomeText}">
				<spring:message code="general.footer"/>
			</p>
		</div>
	</div>
</footer>