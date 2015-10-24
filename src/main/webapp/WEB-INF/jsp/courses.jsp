<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="stub/jstl-stub.jsp" %>
<%@ include file="stub/css-stub.jsp" %>
<%@ include file="stub/spring-stub.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.courses.page.title"/></title>
</head>
<body>

	<div class="wrapper">
	
		<header class="header-admin">
			<div class="logo">
				<a href="<c:url value="/welcome.html"/>">
					<img src="<c:url value="/img/ehslogo.png"/>" height="70px"/>
				</a>
			</div>
			
			<div class="header-title">
				<h1><spring:message code="admin.courses.header.title"/></h1>
			</div>
			
			<div class="user-status">
				<form:form action="admin/logout.html" method="get">
					<input id="logout-btn" class="btn-link" type="submit" 
						name="logoutButton" 
						value="<spring:message code="general.logout"/>"/>
				</form:form>
			</div>
		</header>
		
		<div class="content-container">
			
			<table id="courses-table" class="table table-condensed table-hover table-striped">
				<thead>
					<tr>
						<th data-column-id="select">Select</th>
						<th data-column-id="courseId">Course ID</th>
						<th data-column-id="courseName">Course Name</th>
						<th data-column-id="subject">Subject</th>
						<th data-column-id="teacherLastName">Teacher</th>
						<th data-column-id="period">Period</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${courses}" var="course">
						<tr>
							<td>
								<input type="radio" name="courseId" 
									value="${course.courseId}"/>
							</td>
							<td>${course.courseId}</td>
							<td>${course.courseName}</td>
							<td>${course.subject}</td>
							<td>${course.teacherLastName}</td>
							<td>${course.period}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			
		</div>
		
		<footer class="footer-admin">
			<h4>&copy; Copyright 2015 CraigMiller160</h4>
		</footer>
	
	</div>

</body>
</html>