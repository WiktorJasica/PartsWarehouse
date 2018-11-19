<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- TagLib libraries -->
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="../view_template_layout/taglib.jsp"%>


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
