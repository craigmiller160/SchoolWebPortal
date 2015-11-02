<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="stub/jstl-stub.jsp" %> <!-- Include JSTL Tag Library -->
<%@ include file="stub/bootstrap-stub.jsp" %> <!-- Include Bootstrap Dependencies -->
<%@ include file="stub/css-stub.jsp" %> <!-- Include CSS Stylesheets -->
<%@ include file="stub/spring-stub.jsp" %> <!-- Include Spring Tag Library -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.course.page.title"/></title>
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
				<h1><spring:message code="admin.course.header.title"/></h1>
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
			<!-- Set the form method variable based 
			on if a course from DB has been supplied to page -->
			<c:choose>
				<c:when test="${course.courseId > 0}">
					<c:set var="formMethod" value="post" scope="page"/>
				</c:when>
				<c:otherwise>
					<c:set var="formMethod" value="put" scope="page"/>
				</c:otherwise>
			</c:choose>
			
			<!-- Form to set the attributes of a course object -->
			<form:form commandName="course" method="${formMethod}">
				<!-- The outer container of the form, defining the border -->
				<div class="entity-form-container">
					
					<!-- The caption for the form -->
					<h3 class="entity-form-caption">
						<spring:message code="admin.course.form.caption"/>
					</h3>
					
					<!-- A hidden input field with the course's ID -->
					<form:input type="hidden" path="courseId"/>
					
					<!-- Inner div holding the form itself -->
					<div class="entity-form">
					<!-- Table for the form's layout -->
					<table class="entity-form-table">
						<!-- Course Name row, with label and field -->
						<tr class="entity-form-row">
							<td>
								<spring:message code="admin.course.form.name"/>
							</td>
							<td>
								<form:input class="entity-form-input form-control" 
									type="text" path="courseName"/>
							</td>
						</tr>
						<!-- Course Subject row, with label and field -->
						<tr class="entity-form-row">
							<td><spring:message code="admin.course.form.subject"/></td>
							<td>
								<form:input class="entity-form-input form-control" 
									type="text" path="subject"/>
							</td>
						</tr>
						<!-- Course Teacher row, with label and field -->
						<tr class="entity-form-row">
							<td><spring:message code="admin.course.form.teacher"/></td>
							<td>
								<form:input class="entity-form-input form-control" 
									type="text" path="teacherLastName"/>
							</td>
						</tr>
						<!-- Course Period row, with label and select box -->
						<tr class="entity-form-row">
							<td><spring:message code="admin.course.form.period"/></td>
							<td>
								<% //TODO fix the height of this select, the numbers get cut off %>
								<form:select class="entity-form-input form-control" 
									path="period">
									<c:forEach begin="1" end="12" var="i">
										<option value="${i}" 
											<c:if test="${i == course.period}">
												selected
											</c:if>
										><c:out value="${i}"/></option>
									</c:forEach>
								</form:select>
							</td>
						</tr>
					</table>
					</div>
					
					<!-- Save/Cancel buttons in button bar -->
					<div class="entity-form-btns btn-toolbar">
						<div class="btn-group-lg">
							<input class="btn btn-default btn-lg btn-shadow" name="cancel" type="submit"
								value="<spring:message code="general.cancel"/>"/>
							<input class="btn btn-default btn-lg btn-shadow" name="save" type="submit" 
								value="<spring:message code="admin.course.form.save"/>"/>
						</div>
					</div>
				</div>
				
				
			</form:form>
			
			
			<% //TODO need to add list of students taking this course to this page %>
			
		</div>
		
		<!-- Page footer -->
		<footer class="footer-admin">
			<h4><spring:message code="general.footer"/></h4>
		</footer>
	
	</div>

</body>
</html>