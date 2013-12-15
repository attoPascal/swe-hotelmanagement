<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Kategorie" %>
<%@ page import="hm.servlets.KategorieServlet" %>
<%@ page import="hm.dao.DAO" %>

<%
	KategorieServlet km = new KategorieServlet();
	km.getManagement().instantiateDAO("data.ser");
	ArrayList<Hotel> hList = km.getManagement().getDAO().getHotelList();
%>

<!DOCTYPE html>

<html>

<head>
	<title>Kategorien verwalten</title>
	<meta charset="UTF-8">
</head>

<body>

	<h1>Kategorien verwalten</h1>

	<% for (Hotel h : hList) { 
			ArrayList<Kategorie> kList = h.getKategorien(); 
	%>
	<h3> <%= h.getName() %> </h3>
	
	<table>
		<thead>
			<tr>
				<th class="kategorie">Name</th>
				<th class="preis">Preis/Nacht</th>
				<th class="ausstattung">Ausstattung</th>
			</tr>
		</thead>
		
		<tbody>
			<% for (Kategorie k : kList) { %>
			<tr>
				<td class="kategorie"><%= k.getName() %></td>
				<td class="preis"><%= String.format("%.2f", k.getPreis()/100.0) %> &euro;</td>
				<td class="ausstattung"><%= k.getAusstattung() %></td>
				<td>
					<form action="http://localhost:8080/LittleSharkyFish/KategorieServlet" method="get">
						<input type="submit" name="action" value="delete">
						<input type="hidden" name="name" value="<%= k.getName() %>">
						<input type="hidden" name="hotel" value="<%= h.getName() %>">
					</form>
				</td>
			</tr>
			<% } %>
		</tbody>
	</table>	
	<br><br>
	<% } %>
	
	
	
	<!--
	<form action="http://localhost:8080/LittleSharkyFish/KategorieServlet" method="get">
	
		<table>
			<tr>
    			<td>Preis:</td>
    			<td><input type="text" name="preis" value=""></td>
  			</tr>
  			<tr>
    			<td>Neuer Name:</td>
    			<td><input type="text" name="newname"></td>
  			</tr>
		</table>

		<div>Ausstattung:<br>
  			<textarea cols="50" rows="5" name="ausstattung"></textarea>
		</div>

	
    	<input type="submit" name="action" value="create">
		<input type="submit" name="action" value="edit">
		
	
	</form>
	-->

</body>

</html>