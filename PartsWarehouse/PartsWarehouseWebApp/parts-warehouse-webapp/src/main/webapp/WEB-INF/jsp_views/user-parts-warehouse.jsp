<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- TagLib libraries -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="../view_template_layout/taglib.jsp"%>

<script type="text/javascript">
$(document).ready(function(){
	$(".triggerEdit").click(function(e){  	
		e.preventDefault();
		$("#modalEdit .deleteBtn").attr("href", $(this).attr("href")+"/delete.html");
		$(".editForm").attr("action",$(this).attr("href")+"/edit");
		$("#valName").val($(this).attr("data-name"));
		$("#valManufacturer").val($(this).attr("data-manu"));
		$("#valPrice").val($(this).attr("data-price"));
		$("#valQuantity").val($(this).attr("data-quantity"));
		$("#modalEdit").modal(); 										 
	}); 	
	$(".editForm").validate(				
			{
				rules:{
					name:{
						required : true,
						minlength: 1,
						maxlength : 20
					},
					
					manufacturer:{
						required : true,
						minlength: 1,
						maxlength : 20
					},
					
					price:{
						required : true,
						number: true,
						min: 0,
						max: 100000000
					},
					
					quantity:{
						required : true,
						number: true,
						min: 0,
						max: 2147483647,
						digits: true
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

<!-- Warehouse Table -->
<table class="table table-bordered table-hover table-striped">

	<thead>
		<tr>
			<th>#</th>
			<th>Name</th>
			<th>Manufacturer</th>
			<th>Price</th>
			<th>Quantity</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${warehouse.parts}" var="part" varStatus="lp">
			<tr>
				<td><c:out value="${lp.index+1}"/></td>
				<td><a href="<spring:url value="/user-parts-warehouse/edit-delete/${warehouse.id}/part/${part.id}"/>"
					class="triggerEdit" 
					data-name=<c:out value="${part.name}"/> data-manu=<c:out value="${part.manufacturer}"/> 
					data-price=<c:out value="${part.price}"/> data-quantity=<c:out value="${part.quantity}"/>>
					<c:out value="${part.name}"/></a></td>
				<td><c:out value="${part.manufacturer}"/></td>
				<td><c:out value="${part.price}"/></td>
				<td><c:out value="${part.quantity}"/>szt.</td>
			</tr>
		</c:forEach>
	</tbody>

</table>

<!-- Modal Window "Edit Warehouse" -->

<form:form commandName="editedPart" cssClass="form-horizontal editForm">
	<!-- Modal -->
	<div class="modal fade" id="modalEdit" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">Edit or Delete Part</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Name:</label>
						<div class="col-sm-10">
							<form:input  path="name" cssClass="form-control" id="valName" value=""/>
							<form:errors path="name" />
						</div>
					</div>

					<div class="form-group">
						<label for="manufacturer" class="col-sm-2 control-label">Manufacturer:</label>
						<div class="col-sm-10">
							<form:input path="manufacturer" cssClass="form-control" id="valManufacturer" value="" />
							<form:errors path="manufacturer" />
						</div>
					</div>

					<div class="form-group">
						<label for="price" class="col-sm-2 control-label">Price:</label>
						<div class="col-sm-4">
							<form:input path="price" cssClass="form-control" id="valPrice" value=""/>
							<form:errors path="price">Price value is not a number or is less than 0 </form:errors>
						</div>
					</div>
					<div class="form-group">
						<label for="quantity" class="col-sm-2 control-label">Quantity:</label>
						<div class="col-sm-4">
							<form:input path="quantity" cssClass="form-control" id="valQuantity" value=""/>
							<form:errors path="quantity">Quantity value is not a number or is less than 0 </form:errors>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<a href="" class="btn btn-danger deleteBtn">Delete</a>
					<input type="submit" class="btn btn-primary" value="Save" />
				</div>
			</div>
		</div>
	</div>
</form:form>

