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
			<form:form commandName="course" >
				<form:input type="hidden" path="courseId"/>
				<table>
					<tr>
						<td>
							<spring:message code="admin.course.form.name"/>
						</td>
						<td>
							<input path="courseName"/>
						</td>
					</tr>
					<tr>
						<td><spring:message code="admin.course.form.subject"/></td>
						<td>
							<input type="text" path="subject" 
								name="subject"/>
						</td>
					</tr>
					<tr>
						<td><spring:message code="admin.course.form.teacher"/></td>
						<td>
							<input type="text" path="teacherLastName" 
								name="teacherLastName"/>
						</td>
					</tr>
					<tr>
						<td><spring:message code="admin.course.form.period"/></td>
						<td>
							<select name="period">
								<c:forEach begin="1" end="12" var="i">
									<option value="${i}"><c:out value="${i}"/></option>
								</c:forEach>
							</select>
						</td>
					</tr>
				</table>
			</form:form>
		</div>
		
		<footer class="footer-admin">
			<h4>&copy; Copyright 2015 CraigMiller160</h4>
		</footer>
	
	</div>

</body>
</html>