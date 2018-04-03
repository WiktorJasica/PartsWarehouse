<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Dodajemy biblioteki TagLib -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="../view_template_layout/taglib.jsp"%>

<h1><c:out value="${userName}"/></h1>

<script type="text/javascript">
$(document).ready(function(){
	$(".triggerDelete").click(function(e){  							 // 1)
		e.preventDefault();
		$("#modalDelete .deleteBtn").attr("href", $(this).attr("href")); // 2)
		$("#modalDelete").modal(); 										 // 3) 
	}); 
	$(".warehouseForm").validate(
			{
				rules:{													 // 4)
					name:{
						required : true,
						minlength: 1,
						maxlength: 30
					}
				},
				highlight: function(element){
					$(element).closest('.form-group').removeClass('has-success').addClass('has-error'); // 5)
				},
				unhighlight: function(element){
					$(element).closest('.form-group').removeClass('has-error').addClass('has-success');
				}
			}
	);
});

/*
 * 	
 	1) Szukamy Przycisku o nzawie "triggerDelete" usuwajacego uzytkownika, 
 	   kiedy nacisniemy przycisk wywolay funkcje, odpowiada za to metoda "click"
 	2) Szukamy diva "modalDelete" i przycisku "deleteBtn" wewnatrz tego diva. 
 	   Wstawiamy do atrybutu "href" z diva "modalDelete" atrybut "href" z przycisku "triggerDelete" ktory oznaczony jest przez "this"
 	3) Wyswietlamy modal window "modalDelete"
 	4) Validacja pola name
 	5) Gdy wpisane dane nie sa zgodne z regulami valdacji pole podswietla sie na czerwono, gdy wpisane dane sa poprawne 
 	   pole podswietla sie na zielono 
 */
</script>

<!-- Button - trigger to modal window "Add Warehouse"  -->
<button type="button" class="btn btn-primary btn-sm" data-toggle="modal"
	data-target="#myModal">New Warehouse</button>
<br>

<!-- Modal Window "Add Warehouse" -->
<form:form commandName="warehouse" cssClass="form-horizontal warehouseForm">
	<!-- Modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">Add Warehouse</h4>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">Name:</label>
						<div class="col-sm-10">
							<form:input path="name" cssClass="form-control" />
							<form:errors path="name"/>
						</div>
					</div>

				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
					<input type="submit" class="btn btn-primary" value="Save" />
				</div>
			</div>
		</div>
	</div>
</form:form>

<!-- Modal Window "modalDelete" used as confirmation to delete user -->
<div class="modal fade" id="modalDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Delete Warehouse</h4>
      </div>
      <div class="modal-body">
        Are you sure you want to delete this Warehouse ?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger deleteBtn">Delete</a>
      </div>
    </div>
  </div>
</div>

<br>
<br>

<!-- Table of user's warehouses -->
<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th>Warehouse Name</th>
			<th>Operations</th>
		</tr>
	</thead>
	<tbody>

		<c:forEach items="${warehousesList}" var="warehouse">
			<tr>
				<td>
					<a href="<spring:url value="/user-parts-warehouse/${warehouse.id}.html"/>">
					<c:out value="${warehouse.name}"/>
					</a>
				</td>
				<td width="200" align="center">
					<a href="<spring:url value="/my-warehouses/delete/${warehouse.id}.html"/>" class="btn btn-danger triggerDelete" >Delete</a>
				</td>
			</tr>

		</c:forEach>
	</tbody>
</table>

