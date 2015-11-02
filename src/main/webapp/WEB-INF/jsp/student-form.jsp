<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="stub/jstl-stub.jsp" %> <!-- Include JSTL Tag Library -->
<%@ include file="stub/js-stub.jsp" %> <!-- Include JQuery Library -->
<%@ include file="stub/bootstrap-stub.jsp" %> <!-- Include Bootstrap Dependencies -->
<%@ include file="stub/css-stub.jsp" %> <!-- Include CSS Stylesheets -->
<%@ include file="stub/spring-stub.jsp" %> <!-- Include Spring Tag Library -->
<%@ include file="stub/bootstrap-datepicker-stub.jsp" %> <!-- Include Bootstrap Datepicker Dependencies -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="admin.student.page.title"/></title>
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
				<h1><spring:message code="admin.student.header.title"/></h1>
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
			on if a student from DB has been supplied to page -->
			<c:choose>
				<c:when test="${student.studentId > 0}">
					<c:set var="formMethod" value="post" scope="page"/>
				</c:when>
				<c:otherwise>
					<c:set var="formMethod" value="put" scope="page"/>
				</c:otherwise>
			</c:choose>
			
			<!-- Form to set the attributes of a student object -->
			<form:form commandName="student" method="${formMethod}">
				<!-- The outer container of the form, defining the border -->
				<div class="entity-form-container">
					<!-- The caption for the form -->
					<h3 class="entity-form-caption">
						<spring:message code="admin.student.form.caption"/>
					</h3>
					
					<!-- A hidden input field with the students's ID -->
					<form:input type="hidden" path="studentId"/>
					
					<!-- Inner div holding the form itself -->
					<div class="entity-form">
					<!-- Table for the form's layout -->
					<table class="entity-form-table">
						<!-- First Name row, with label and field -->
						<tr class="entity-form-row">
							<td>
								<spring:message code="admin.student.form.firstName"/>
							</td>
							<td>
								<form:input class="entity-form-input form-control" path="firstName"/>
							</td>
						</tr>
						<!-- Last Name row, with label and field -->
						<tr class="entity-form-row">
							<td><spring:message code="admin.student.form.lastName"/></td>
							<td>
								<form:input class="entity-form-input form-control" type="text" path="lastName"/>
							</td>
						</tr>
						<!-- Birth Date row, with label and field -->
						<tr class="entity-form-row">
							<td><spring:message code="admin.student.form.birthDate"/></td>
							<td>
								<% //TODO Datepicker value causes error with controller's expected type %>
								<form:input id="datepicker" class="entity-form-input form-control" 
									type="text" path="birthDate"/>
							</td>
						</tr>
						<!-- Gender row, with label and select box -->
						<tr class="entity-form-row">
							<td><spring:message code="admin.student.form.gender"/></td>
							<td>
								<% //TODO fix the height of this select, the numbers get cut off %>
								<form:select class="entity-form-input form-control" path="gender">
									<% //TODO set it so that the selected item is the entity's gender %>
									<option value="M">M</option>
									<option value="F">F</option>
									<option value="U">U</option>
								</form:select>
							</td>
						</tr>
						<!-- Grade row, with label and select box -->
						<tr class="entity-form-row">
							<td><spring:message code="admin.student.form.grade"/></td>
							<td>
								<% //TODO fix the height of this select, the numbers get cut off %>
								<form:select class="entity-form-input form-control" path="gender">
									<% //TODO set it so that the selected item is the entity's grade %>
									<c:forEach begin="1" end="12" var="i">
										<option value="${i}"><c:out value="${i}"/></option>
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
								value="<spring:message code="admin.student.form.save"/>"/>
						</div>
					</div>
				
				</div>
			
			
			</form:form>
		
		</div>
		
		<!-- Page footer -->
		<footer class="footer-admin">
			<h4><spring:message code="general.footer"/></h4>
		</footer>
		
	
	</div>
	
	<!-- Bootstrap Datepicker JQuery Script -->
	<script>
	$(document).ready(function(){
		$('#datepicker').datepicker({
			startDate: "-50y",
			endDate: "+50y"
		});
	});
	</script>

</body>
</html>