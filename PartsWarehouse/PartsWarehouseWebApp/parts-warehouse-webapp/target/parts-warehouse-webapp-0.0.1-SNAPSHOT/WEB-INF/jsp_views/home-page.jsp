<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<%@ include file="../view_template_layout/taglib.jsp" %>

<security:authorize access="!isAuthenticated()">
WELCOME, 
<br>
THIS IS WAREHOUSES MANAGER
<br>
Please login or register if you don't have an account yet 
</security:authorize>

<security:authorize access="isAuthenticated()">
WELCOME, <c:out value="${userName}"/>
<br>
</security:authorize>

<security:authorize  >
<c:if test="${success eq true}">
		<div class="alert alert-success">YOU LOGIN SUCCESSFULLY</div>
</c:if>
</security:authorize>
