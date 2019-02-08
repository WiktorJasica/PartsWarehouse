<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- TagLib libraries -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="../view_template_layout/taglib.jsp"%>

<!-- Registration Form -->
<div class="container-fluid">
	<div class="row">
		<form:form commandName="user" cssClass="form-horizontal registrationForm">

			<!-- Displaying a message that the registration proceeded correctly -->
			<c:if test="${success eq true}">
				<div class="alert alert-success">Registration successfull</div>
			</c:if>

			<div class="form-group">
				<label for="name" class="col-sm-3 control-label">Name:</label>
				<div class="col-sm-6">
					<form:input path="name" cssClass="form-control" />
					<form:errors path="name"/>
				</div>
			</div>
	
			<div class="form-group">
				<label for="email" class="col-sm-3 control-label">Email:</label>
				<div class="col-sm-6">
					<form:input path="email" cssClass="form-control" />
					<form:errors path="email"/>
				</div>
			</div>
	
			<div class="form-group">
				<label for="password" class="col-sm-3 control-label">Password:</label>
				<div class="col-sm-6">
					<form:password path="password" cssClass="form-control" />
					<form:errors path="password"/>
				</div>
			</div>
	
			<div class="form-group">
				<label for="password" class="col-sm-3 control-label"></label>
				<div class="col-sm-2">
					<input type="submit" value="Save" class="btn btn-lg btn-primary" />
				</div>
			</div>

		</form:form>
	</div>
</div>

<script type="text/javascript">
$(document).ready(function(){      		
	$(".registrationForm").validate(		
		{
			rules:{
				name:{
					required : true,
					minlength: 3
				},
				
				email:{
					required : true,
					email: true
				},
				
				password:{
					required : true,
					minlength: 5
				}
			},
			highlight: function(element){
				$(element).closest('.form-group').removeClass('has-success').addClass('has-error');
			},
			unhighlight: function(element){
				$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
			}
		}
	);		
	
});
</script>