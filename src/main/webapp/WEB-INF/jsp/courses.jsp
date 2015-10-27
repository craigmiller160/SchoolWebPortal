<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="stub/jstl-stub.jsp" %> <!-- Include JSTL Tag Library -->
<%@ include file="stub/css-stub.jsp" %> <!-- Include CSS Stylesheets -->
<%@ include file="stub/spring-stub.jsp" %> <!-- Include Spring Tag Library -->
<%@ include file="stub/js-stub.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.courses.page.title"/></title>
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
				<h1><spring:message code="admin.courses.header.title"/></h1>
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
			<div id="courses-table-div">
				<!-- Table holding the list of courses -->
				<table id="courses-table" class="table table-condensed table-hover table-striped">
					<!-- Table Caption -->
					<caption id="courses-table-caption">
						<spring:message code="admin.courses.table.caption"/>
					</caption>
					
					<!-- Table header -->
					<thead id="courses-table-header">
						<tr>
							<!-- Action Fields Header -->
							<th class="action-fields" data-column-id="action" colspan="2">
								<spring:message code="admin.courses.table.header.action"/>
							</th>
							<!-- Course ID Header -->
							<th data-column-id="courseId">
								<spring:message code="admin.courses.table.header.id"/>
							</th>
							<!-- Course Name Header -->
							<th data-column-id="courseName">
								<spring:message code="admin.courses.table.header.name"/>
							</th>
							<!-- Course Subject Header -->
							<th data-column-id="subject">
								<spring:message code="admin.courses.table.header.subject"/>
							</th>
							<!-- Course Teacher Header -->
							<th data-column-id="teacherLastName">
								<spring:message code="admin.courses.table.header.teacher"/>
							</th>
							<!-- Course Period Header -->
							<th data-column-id="period">
								<spring:message code="admin.courses.table.header.period"/>
							</th>
						</tr>
					</thead>
					
					<!-- Table Body -->
					<tbody>
						<!-- JSTL For Loop to create a new row for 
						each entry in the courses list -->
						<c:forEach items="${courses}" var="course">
							<!-- Create the row -->
							<tr>
								<!-- Edit Button with a value of the Course ID -->
								<td class="action-fields">
									<form:form action="./${course.courseId}.html" method="get">
										<input class="btn btn-default" type="submit" value="Edit"/>
									</form:form>
								</td>
								<!-- Delete Button with a value of the Course ID -->
								<td class="action-fields">
									<form:form action="./${course.courseId}.html" method="delete">
										<input class="btn btn-default" type="submit" value="Delete"/>
									</form:form>
								</td>
								<td>${course.courseId}</td> <!-- Course ID field -->
								<td>${course.courseName}</td> <!-- Course Name field -->
								<td>${course.subject}</td> <!-- Course Subject field -->
								<td>${course.teacherLastName}</td> <!-- Course Teacher field -->
								<td>${course.period}</td> <!-- Course Period field -->
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
				<!-- Button toolbar with action buttons for courses table -->
				<div id="courses-btn-bar" class="btn-toolbar" role="toolbar">
					
					<!-- Next/Previous page buttons -->
					<div class="btn-group-lg page-btns" role="group">
						<!-- Only show previous page button if page > 1 -->
						<c:if test="${page > 1}"> 
							<form:form method="get">
								<input type="hidden" name="page" value="${page - 1}"/>
								<input class="btn-page btn btn-lg btn-default btn-shadow" type="submit"
									value="Previous Page"/>
							</form:form>
						</c:if>
						<% //TODO need to figure out how to kill the next button when there's no more pages %> 
						<form:form method="get">
							<input type="hidden" name="page" value="${page + 1}"/>
							<input class="btn-page btn btn-lg btn-default btn-shadow" type="submit"
								value="Next Page"/>
						</form:form>
						
					</div>
					
					<!-- Add Course Button -->
					<div id="add-btn" class="btn-group-lg" role="group">
						<form:form id="addForm" action="./new.html" method="get">
							<input class="btn btn-lg btn-default btn-shadow" type="submit" 
								value="Add New"/>
						</form:form>
					</div>
					
				</div>
			</div>
			
		</div>
		
		<!-- Page footer -->
		<footer class="footer-admin">
			<h4><spring:message code="general.footer"/></h4>
		</footer>
	
	</div>

</body>
</html>