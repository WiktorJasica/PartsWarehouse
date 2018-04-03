<%@page import="org.apache.tomcat.util.descriptor.tld.TaglibXml"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Dodajemy biblioteki TagLib -->
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="../view_template_layout/taglib.jsp"%>

<!-- Wygląd strony do logowania -->
<style>

.form-signin {
  width: 100%;
  max-width: 330px;
  padding: 15px;
  margin: 0 auto;
}
.form-signin .form-control {
  position: relative;
  box-sizing: border-box;
  height: auto;
  padding: 10px;
  font-size: 16px;
}
.form-signin .form-control:focus {
  z-index: 2;
}
.form-signin input[type="email"] {
  margin-bottom: -1px;
  border-bottom-right-radius: 0;
  border-bottom-left-radius: 0;
}
.form-signin input[type="password"] {
  margin-bottom: 10px;
  border-top-left-radius: 0;
  border-top-right-radius: 0;
}
</style>

<!-- Formularz uzupełniony danymi ze strony logowania SpringSecurity -->
<!-- Zostały dodane parametry takie jak: action="/parts-warehouse-webapp/j_spring_security_check" method="POS;
 	type="text" id="j_username" name="j_username"; type="password" id = "j_password" name="j_password"-->
 	
 <body class="text-center" >
    <form class="form-signin" action="/parts-warehouse-webapp/j_spring_security_check" method="POST">
      <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
      <label for="j_username" class="sr-only">Name</label>
      <input type="text" id="j_username" name="j_username" class="form-control" placeholder="Name" required autofocus>
      <label for="j_password" class="sr-only">Password</label>
      <input type="password" id = "j_password" name="j_password" class="form-control" placeholder="Password" required>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
    </form>
  </body>
</html>