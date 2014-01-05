<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Kategorie" %>
<%@ page import="hm.servlets.KategorieServlet" %>
<%@ page import="hm.dao.DAO" %>

<!DOCTYPE html>

<html>

<%@ include file="inc/head.jsp" %>

<body>
	<%@ include file="inc/nav.jsp" %>
	
	<main class="container">
		<h1>Login</h1>
		
		<%
		String alert = (String) session.getAttribute("alert");
		if (alert != null) {
			out.print("<p class='alert alert-warning'>" + alert + "</p>");
			session.removeAttribute("alert");
		}
		%>
		
		<div class="jumbotron">
			<form method="post" action="UserServlet">
				<div class="form-group">
					<label for="usernameInput" class="control-label">Username:</label>
	    			<input type="text" name="username" class="form-control" id="usernameInput" placeholder="Username">
	  			</div>
	  			<div class="form-group">
					<label for="passwordInput" class="control-label">Passwort:</label>
	    			<input type="password" name="password" class="form-control" id="passwordInput" placeholder="Passwort">
	  			</div>
	  			<button type="submit" name="action" value="login" class="btn btn-primary">Login</button>
			</form>
		</div>
	</main>
</body>

</html>