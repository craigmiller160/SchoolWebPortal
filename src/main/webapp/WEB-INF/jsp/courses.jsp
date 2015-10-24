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
			
			<div id="courses-table-div">
			<table id="courses-table" class="table table-condensed table-hover table-striped">
				<caption id="courses-table-caption">
					<spring:message code="admin.courses.table.caption"/>
				</caption>
				<thead id="courses-table-header">
					<tr>
						<th data-column-id="select">
							<spring:message code="admin.courses.table.header.select"/>
						</th>
						<th data-column-id="courseId">
							<spring:message code="admin.courses.table.header.id"/>
						</th>
						<th data-column-id="courseName">
							<spring:message code="admin.courses.table.header.name"/>
						</th>
						<th data-column-id="subject">
							<spring:message code="admin.courses.table.header.subject"/>
						</th>
						<th data-column-id="teacherLastName">
							<spring:message code="admin.courses.table.header.teacher"/>
						</th>
						<th data-column-id="period">
							<spring:message code="admin.courses.table.header.period"/>
						</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${courses}" var="course">
						<tr>
							<td id="select-radio">
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
			
			<div class="btn-toolbar" role="toolbar">
				<div class="btn-group-lg" role="group">
					<input class="btn btn-default btn-shadow" type="submit" 
						name="addCourse" value="Add New"/>
					<input class="btn btn-default btn-shadow" type="submit"
						name="editCourse" value="Edit"/>
					<input class="btn btn-default btn-shadow" type="submit"
						name="deleteCourse" value="Delete"/>
				</div>
				<div id="page-btns" class="btn-group-lg" role="group">
					<input class="btn btn-default btn-shadow" type="submit"
						name="previousPage" value="Previous Page"/>
					<input class="btn btn-default btn-shadow" type="submit"
						name="nextPage" value="Next Page"/>
				</div>
			</div>
			</div>
			
		</div>
		
		<footer class="footer-admin">
			<h4>&copy; Copyright 2015 CraigMiller160</h4>
		</footer>
	
	</div>

</body>
</html>