<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="stub/jstl-stub.jsp" %>
<%@ include file="stub/css-stub.jsp" %>
<%@ include file="stub/spring-stub.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.course.page.title"/></title>
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
				<form:form action="../logout.html" method="get">
					<input id="logout-btn" class="btn-link" type="submit" 
						name="logoutButton" 
						value="<spring:message code="general.logout"/>"/>
				</form:form>
			</div>
		</header>
	
		<div class="content-container">
			<% //TODO need to figure out a way to dynamically switch between post and put here %>
			
			<!-- Set the form method variable based 
			on if a course from DB has been supplied to page -->
			<c:choose>
				<c:when test="${course.courseId > 0}">
					<c:set var="formMethod" value="post"/>
				</c:when>
				<c:otherwise>
					<c:set var="formMethod" value="put"/>
				</c:otherwise>
			</c:choose>
			
			<form:form commandName="course" method="${formMethod}">
				<div id="course-form-container">
					<h3 id="course-form-caption">Course Info</h3>
					<form:input type="hidden" path="courseId"/>
					<div id="course-form">
					<table id="course-form-table">
						<tr class="course-form-row">
							<td>
								<spring:message code="admin.course.form.name"/>
							</td>
							<td>
								<form:input path="courseName"/>
							</td>
						</tr>
						<tr class="course-form-row">
							<td><spring:message code="admin.course.form.subject"/></td>
							<td>
								<form:input type="text" path="subject" 
									name="subject"/>
							</td>
						</tr>
						<tr class="course-form-row">
							<td><spring:message code="admin.course.form.teacher"/></td>
							<td>
								<form:input type="text" path="teacherLastName" 
									name="teacherLastName"/>
							</td>
						</tr>
						<tr class="course-form-row">
							<td><spring:message code="admin.course.form.period"/></td>
							<td>
								<% //TODO fix the height of this select, the numbers get cut off %>
								<select id="period-select" class="form-control" name="period">
									<c:forEach begin="1" end="12" var="i">
										<option value="${i}" 
											<c:if test="${i == course.period}">
												selected
											</c:if>
										><c:out value="${i}"/></option>
									</c:forEach>
								</select>
							</td>
						</tr>
					</table>
					</div>
					
					<div id="course-btn-div">
						<input class="btn btn-default btn-lg btn-shadow" name="cancel" type="submit"
							value="<spring:message code="admin.course.form.cancel"/>"/>
						<input class="btn btn-default btn-lg btn-shadow" name="save" type="submit" 
							value="<spring:message code="admin.course.form.save"/>"/>
					</div>
				</div>
				
				
			</form:form>
			
			
			<% //TODO need to add list of students taking this course to this page %>
			
		</div>
		
		<footer class="footer-admin">
			<h4>&copy; Copyright 2015 CraigMiller160</h4>
		</footer>
	
	</div>

</body>
</html>