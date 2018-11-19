<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- TagLib libraries -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="../view_template_layout/taglib.jsp"%>

<script type="text/javascript">
	$(document).ready(
			function() { 
				$(".addForm").validate(
						
						{
							rules : {
								name : {
									required : true,
									minlength : 1,
									maxlength : 20
								},

								manufacturer : {
									required : true,
									minlength : 1,
									maxlength : 20
								},

								price : {
									required : true,
									number: true,
									min : 0,
									max : 100000000,
								},

								quantity : {
									required : true,
									number: true,
									min : 0,
									max : 2147483647,
									digits: true
								}
							},
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							}
						});
				$(".searchPart").validate(
						{
							rules : {
								key : {
									required : true,
									minlength : 1
								}
							},
							highlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-success').addClass('has-error');
							},
							unhighlight : function(element) {
								$(element).closest('.form-group').removeClass(
										'has-error').addClass('has-success');
							}

						});
			});
</script>

<!-- "Add Part" form-->
<div class="container-fluid">
	<div class="row">
		<form:form action="/parts-warehouse-webapp/user-parts-warehouse/add/${warehouse.id}" commandName="part" cssClass="form-horizontal addForm" method="post">
	
			<div class="form-group">
				<label for="name" class="col-sm-2 control-label">Name:</label>
				<div class="col-sm-7">
					<form:input path="name" cssClass="form-control" />
					<form:errors path="name" />
				</div>
			</div>
		
			<div class="form-group">
				<label for="manufacturer" class="col-sm-2 control-label">Manufacturer:
				</label>
				<div class="col-sm-7">
					<form:input path="manufacturer" cssClass="form-control" />
					<form:errors path="manufacturer" />
				</div>
			</div>
		
			<div class="form-group">
				<label for="price" class="col-sm-2 control-label">Price:</label>
				<div class="col-sm-4">
					<form:input path="price" cssClass="form-control" />
					<form:errors path="price">Price value is not a number or is less than 0 </form:errors>
				</div>
			</div>
		
			<div class="form-group">
				<label for="quantity" class="col-sm-2 control-label">Quantity:</label>
				<div class="col-sm-4">
					<form:input path="quantity" cssClass="form-control" />
					<form:errors path="quantity">Quantity value is not an integer number or is less than 0</form:errors>
				</div>
			</div>
		
			<div class="form-group">
				<div  class="col-sm-2">
					<input  type="submit" value="Add" class="col-sm-11 btn btn-default btn-primary btn-success" />
				</div>
			</div>
	
		</form:form>
	</div>
</div>

<!-- Serch and Sort options -->
<div class="container-fluid">
	<div class="row">
		<form:form action="/parts-warehouse-webapp/user-parts-warehouse/${warehouse.id}/search"  commandName="key" cssClass="form-horizontal searchPart">
						
			<div class="form-group">
				<div class="col-sm-2">
					<input type="submit" value="Search"	class="col-sm-11 btn btn-default btn-primary" />
				</div>
								
				<div class="col-sm-6">
					<form:input path="key" cssClass="form-control" />
					<form:errors path="key" />
				</div>
								
				<div class="btn-group col-sm-4" >
					<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" 
							aria-haspopup="true" aria-expanded="false">	Sort Warehouse by: <span class="caret"></span>
					</button>
					<ul class="dropdown-menu">
						<li><a href="<spring:url value="/user-parts-warehouse/sort-by-name/${warehouse.id}"/>">Name</a></li>
						<li><a href="<spring:url value="/user-parts-warehouse/sort-by-manufacturer/${warehouse.id}"/>">Manufacturer</a></li>
						<li><a href="<spring:url value="/user-parts-warehouse/sort-by-price/${warehouse.id}"/>">Price</a></li>
						<li><a href="<spring:url value="/user-parts-warehouse/sort-by-quantity/${warehouse.id}"/>">Quantity</a></li>
					</ul>
				</div>
			</div>
			
		</form:form>				
	</div>
</div>
