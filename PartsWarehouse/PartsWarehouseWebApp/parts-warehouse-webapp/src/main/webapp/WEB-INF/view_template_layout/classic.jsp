<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@	taglib uri="http://tiles.apache.org/tags-tiles-extras"	prefix="textras"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<!DOCTYPE html>
<html lang="pl">
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

<body>
		<!-- Inserting static navigation bar -->
		<tiles:insertAttribute name="nav_bar" />
	

	<!-- Inserting body -->
	<div class="container-fluid">
		<div class="row">
		
				<c:if test="${current=='user-parts-warehouse'}">
					<div class="col-xs-12 col-md-6" >
						<tiles:insertAttribute name="menu" />
					</div>
				</c:if>
				
				<div class="col-xs-12 ${current=='user-parts-warehouse' ? 'col-md-6' : ''}">
					<tiles:insertAttribute name="body" />
				</div>	
		</div>
	</div>
		
	<!-- Inserting footer -->
	<div class="container-fluid">
		<div class="row">
			<br> <br>
				<footer class="text-center">
					<tiles:insertAttribute name="footer" />
				</footer>
		</div>
	</div>

</body>
</html>