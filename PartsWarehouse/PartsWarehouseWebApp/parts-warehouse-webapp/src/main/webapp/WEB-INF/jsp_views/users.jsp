<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../view_template_layout/taglib.jsp"%>

<h1>Users</h1>
<br>

<script type="text/javascript">
$(document).ready(function(){
	$('.nav-tabs a:first').tab('show');									 
	$(".triggerDelete").click(function(e){  							 
		e.preventDefault();
		$("#modalDelete .deleteBtn").attr("href", $(this).attr("href")); 
		$("#modalDelete").modal(); 										 
	}); 	
});

</script>

	<!-- Nav tabs -->
	<ul class="nav nav-tabs" role="tablist">
		<c:forEach items="${users}" var="user">
			<li><a href="#user_${user.id}" data-toggle="tab"><c:out value="${user.name}"/></a></li>
		</c:forEach>
	</ul>

	<!-- Tab panes -->
	<div class="tab-content">
		<c:forEach items="${users}" var="user" varStatus="lp">
			<div class="tab-pane" id="user_${user.id}">
				<table class="table table-bordered table-hover table-striped">

					<thead>
						<tr>
							<th>#</th>
							<th>Name</th>
							<th>Email</th>
							<th>Warehouses</th>
							<th>Operations</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><c:out value="${lp.index+1}"/></td>
							<td><c:out value="${user.name}"/></td>
							<td><c:out value="${user.email}"/></td>
							<td>
							<c:forEach items="${user.warehouses}" var="warehouse">
							<c:out value="${warehouse.name},"/>
							</c:forEach>
							</td>
							<td width="200" align="center"><a href="<spring:url value="/user/delete/${user.id}.html"/>" class="btn btn-danger triggerDelete" >Delete</a></td>
						</tr>
					</tbody>
				</table>
			</div>
		</c:forEach>
	</div>

<!-- Modal Window used as confirmation to delete user -->
<div class="modal fade" id="modalDelete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Delete User</h4>
      </div>
      <div class="modal-body">
        Are you sure you want to delete this User ?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
        <a href="" class="btn btn-danger deleteBtn">Delete</a>
      </div>
    </div>
  </div>
</div>


