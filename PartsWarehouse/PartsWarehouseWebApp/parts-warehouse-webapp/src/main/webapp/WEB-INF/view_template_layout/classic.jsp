<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@	taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="textras"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!DOCTYPE html>

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

<!-- jquary validation plugin - enable to validate in JavaScript -->
<script type="text/javascript"
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.11.1/jquery.validate.min.js"></script>

<!-- Latest compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<!-- Inserting title of the page -->
<title><tiles:getAsString name="title"></tiles:getAsString></title>
</head>

<textras:useAttribute name="current" />

<!-- NavBar -->
<body>
	<div class="container">
		<!-- Static navbar -->
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed"
						data-toggle="collapse" data-target="#navbar" aria-expanded="false"
						aria-controls="navbar">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="<spring:url value="/" />">Home</a>
				</div>
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav">

						<security:authorize access="isAuthenticated()">
							<li class="${current == 'user-parts-warehouse' ? 'active' : '' }"><a
								href="<spring:url value="/my-warehouses"/>">My Warehouses</a></li>
						</security:authorize>

						<security:authorize access="hasRole('ROLE_ADMIN')">
							<li class="${current == 'users' ? 'active' : '' }"><a
								href="<spring:url value= "/users.html" />"> Users </a></li>
						</security:authorize>

						<security:authorize access="!isAuthenticated()">
							<li class="${current=='login'?'active':'' }"><a
								href="<spring:url value="/login"/>">Login</a></li>
						</security:authorize>

						<security:authorize access="isAuthenticated()">
							<li><a href="<spring:url value="/logout"/>">Logout</a></li>
						</security:authorize>
					</ul>
					<ul class="nav navbar-nav navbar-right">
						<security:authorize access="!isAuthenticated()">
							<li class="${current=='user-register'?'active':''}"><a
								href="<spring:url value="/register"/>">Register</a></li>
						</security:authorize>
					</ul>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</nav>

		<!-- Inserting body -->
		<div>
			<table class="table">
				<tr>
					<c:if test="${current=='user-parts-warehouse'}">
						<td><tiles:insertAttribute name="menu" /></td>
					</c:if>
					<td><tiles:insertAttribute name="body" /></td>
				</tr>
			</table>
		</div>
		<div>
		
			<!-- Inserting footer -->
			<br> <br>
			<footer class="text-center">
				<tiles:insertAttribute name="footer" />
			</footer>
		</div>
	</div>
</body>
</html>