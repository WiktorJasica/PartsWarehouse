<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Dodajemy biblioteki TagLib -->
<%@ include file="../view_template_layout/taglib.jsp"%>

<form:form commandName="user" cssClass="form-horizontal registrationForm">

	<!-- Wyswietlenie komunikatu o tym ze rejestracja przebiegla poprawnie -->
	<c:if test="${success eq true}">
		<div class="alert alert-success">Registration successfull</div>
	</c:if>

	<div class="form-group">
		<label for="name" class="col-sm-2 control-label">Name:</label>
		<div class="col-sm-10">
			<form:input path="name" cssClass="form-control" />
			<form:errors path="name"/>
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">Email:</label>
		<div class="col-sm-10">
			<form:input path="email" cssClass="form-control" />
			<form:errors path="email"/>
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">Password:</label>
		<div class="col-sm-10">
			<form:password path="password" cssClass="form-control" />
			<form:errors path="password"/>
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-2">
			<input type="submit" value="Save" class="btn btn-lg btn-primary" />
		</div>
	</div>

</form:form>

<script type="text/javascript">
$(document).ready(function(){      		//When dovument ready call this funtion
	$(".registrationForm").validate(		//Find form with cals name "registrationForm and "validate()"-this is function from validation plugin
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