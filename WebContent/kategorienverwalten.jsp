<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Kategorie" %>
<%@ page import="hm.servlets.KategorieServlet" %>
<%@ page import="hm.dao.DAO" %>

<%
	KategorieServlet km = new KategorieServlet();
	km.getManagement().instantiateDAO();
	ArrayList<Hotel> hList = km.getManagement().getDAO().getHotelList();
	
	String hotelName = request.getParameter("hotel");
	Hotel hotel = null;
	
	if (hotelName != null) {
		hotel = km.getManagement().getDAO().getHotelByName(hotelName);
		
	} else {
		//wenn kein Hotel spezifiziert: erstes aus Liste wählen
		hotel = hList.get(0);
	}
	
	ArrayList<Kategorie> kList = hotel.getKategorien();
%>

<!DOCTYPE html>

<html>

<%@ include file="inc/head.jsp" %>

<body>
	<%@ include file="inc/nav.jsp" %>
	
	<main class="container">
		<h1>Kategorien verwalten</h1>
	
		<form>
			<div class="form-group">
    			<label for="setHotel">Hotel auswählen:</label>
    			<select name="hotel" class="set-hotel form-control" id="setHotel">
    				<% for (Hotel h : hList) { 
					String selected = (h.getName().equals(hotel.getName())) ? "selected=\"selected\"" : "";
					%>
					<option value="<%= h.getName() %>"<%= selected %>><%= h.getName() %></option>
					<% } %>
				</select>
  			</div>
		</form>
		
		<div id="manage">	
			<table class="kategorie table">
				<thead>
					<tr>
						<th class="kategorie">Name</th>
						<th class="preis">Preis/Nacht</th>
						<th class="ausstattung">Ausstattung</th>
						<th class="button1"></th>
						<th class="button2"></th>
					</tr>
				</thead>
				
				<tbody>
					<% for (Kategorie k : kList) { %>
					<tr>
						<form action="KategorieServlet" method="get">
							<td class="kategorie">
								<input type="hidden" name="name"  value="<%= k.getName() %>">
								<input type="text" name="newname" value="<%= k.getName() %>" class="form-control">
							</td>
							<td class="preis">
								<input type="number" name="preis" value="<%= k.getPreis() %>" class="form-control">
							</td>
							<td class="ausstattung">
								<input type="text" name="ausstattung" value="<%= k.getAusstattung() %>" class="form-control">
							</td>
							<td class="button1">
								<button type="submit" name="action" value="edit" class="btn btn-primary">
									<span class="glyphicon glyphicon-ok"></span>
								</button>
							</td>
							<td class="button2">
								<input type="hidden" name="hotel" value="<%= hotel.getName() %>">
								<button type="submit" name="action" value="delete" class="btn btn-danger">
									<span class="glyphicon glyphicon-remove"></span>
								</button>
	
							</td>
						</form>
					</tr>
					<% } %>
				</tbody>
			</table>
			
			<form action="KategorieServlet" method="get" accept-charset="UTF-8">
				<table class="kategorie table">
					<tbody>
						<tr>
							<td class="name">
								<input type="hidden" name="hotel" value="<%= hotel.getName() %>">
								<input type="text" name="name" class="form-control" placeholder="Name">
							</td>
							<td class="preis">
								<input type="number" name="preis" value="" class="form-control" placeholder="Preis/Nacht">
							</td>
							<td class="ausstattung">
								<input type="text" name="ausstattung" value="" class="form-control" placeholder="Ausstattung">
							</td>
							<td class="button1">
								<button type="submit" name="action" value="create" class="btn btn-success">
									<span class="glyphicon glyphicon-plus"></span>
								</button>
							</td>
							<td class="button2"></td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</main>
</body>

</html>