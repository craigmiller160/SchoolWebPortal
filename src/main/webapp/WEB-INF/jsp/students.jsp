<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="stub/jstl-stub.jsp" %> <!-- Include JSTL Tag Library -->
<%@ include file="stub/css-stub.jsp" %> <!-- Include CSS Stylesheets -->
<%@ include file="stub/spring-stub.jsp" %> <!-- Include Spring Tag Library -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.students.page.title"/></title>
</head>
<body>

	<!-- Wrapper div for implemented FlexBox Layout -->
	<div class="wrapper">
	
		<!-- Page Header -->
		<header class="header-admin">
			<!-- Logo and link to welcome page in upper left of header -->
			<div class="logo">
				<a href="<c:url value="/welcome.html"/>">
					<img src="<c:url value="/img/ehslogo.png"/>" height="70px"/>
				</a>
			</div>
			
			<!-- Page Title in header -->
			<div class="header-title">
				<h1><spring:message code="admin.students.header.title"/></h1>
			</div>
			
			<!-- Option to logout of web portal -->
			<div class="user-status">
				<form:form action="../logout.html" method="get">
					<input id="logout-btn" class="btn-link" type="submit" 
						name="logoutButton" 
						value="<spring:message code="general.logout"/>"/>
				</form:form>
			</div>
		</header>
		
		<!-- Container holding the main content for the page -->
		<div class="content-container">
		
			
			<!-- Div for the courses list table -->
			<div class="entities-table-div">
				<!-- Table holding the list of courses -->
				<table class="entities-table table table-condensed table-hover table-striped">
					<!-- Table Caption -->
					<caption class="entities-table-caption">
						<spring:message code="admin.students.table.caption"/>
					</caption>
					
					<!-- Table header -->
					<thead id="courses-table-header">
						<tr>
							<!-- Action Fields Header -->
							<th class="action-fields" data-column-id="action" colspan="2">
								<spring:message code="general.table.header.action"/>
							</th>
							<!-- Student ID Header -->
							<th data-column-id="studentId">
								<spring:message code="admin.students.table.header.id"/>
							</th>
							<!-- First Name Header -->
							<th data-column-id="firstName">
								<spring:message code="admin.students.table.header.firstName"/>
							</th>
							<!-- Last Name Header -->
							<th data-column-id="lastName">
								<spring:message code="admin.students.table.header.lastName"/>
							</th>
							<!-- Age Header -->
							<th data-column-id="age">
								<spring:message code="admin.students.table.header.age"/>
							</th>
							<!-- Gender Header -->
							<th data-column-id="gender">
								<spring:message code="admin.students.table.header.gender"/>
							</th>
							<!-- Grade Header -->
							<th data-column-id="grade">
								<spring:message code="admin.students.table.header.grade"/>
							</th>
						</tr>
					</thead>
					
					
					<!-- Table Body -->
					<tbody>
						<!-- JSTL For Loop to create a new row for 
						each entry in the students list -->
					</tbody>
				
				</table>
			
			
			</div>
		
		
		</div>
		
		<!-- Page footer -->
		<footer class="footer-admin">
			<h4><spring:message code="general.footer"/></h4>
		</footer>
	
	</div>

</body>
</html>