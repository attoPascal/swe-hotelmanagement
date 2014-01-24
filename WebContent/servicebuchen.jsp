<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Date" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Service" %>
<%@ page import="hm.Buchung" %>
<%@ page import="hm.users.HotelGast" %>
<%@ page import="hm.dao.SerializedDAO" %>

<%
String hotelName = request.getParameter("hotelName");
int buchungsID = Integer.parseInt(request.getParameter("buchungsID"));

Object user = session.getAttribute("user");
if (!(user instanceof HotelGast)) {
	session.setAttribute("alert", "Zugriff verweigert. Bitte melden Sie sich als Gast an, um auf diese Seite zuzugreifen.");
	session.setAttribute("redirect", "servicebuchen.jsp?hotelName=" + hotelName + "&buchungsID=" + buchungsID);
	response.sendRedirect("login.jsp");

} else {	
	Hotel hotel = SerializedDAO.getInstance().getHotelByName(hotelName);
	List<Service> sList = hotel.getServiceList();
	Buchung buchung = hotel.getBuchungByID(buchungsID);
	Map<Date,Service> sMap = buchung.getServices();
%>
	
<!DOCTYPE html>

<html>

<%@ include file="inc/head.jsp" %>

<body>
	<%@ include file="inc/nav.jsp" %>
	
	<main class="container">
		<h1>Services buchen</h1>
		
	<% if (!sList.isEmpty()) { %>
		<table class="table">
			<tr>
				<th>Name</th>
				<th>Beschreibung</th>
				<th>Preis</th>
				<th>Datum</th>
			</tr>
		
		<% for (Service s: sList) { %>
			<tr>
				<td><%= s.getName() %></td>
				<td><%= s.getBeschreibung() %></td>
				<td><%= s.getPreis() %></td>
				<td>
					<form action="ServiceServlet" method="get">
						<input type="date" name="dateString" placeholder="JJJJ-MM-TT">
						<input type="hidden" name="serviceName" value="<%= s.getName() %>">
						<input type="hidden" name="hotelName" value="<%= hotelName %>">
						<input type="hidden" name="buchungsID" value="<%= buchungsID %>">
						<button type="submit" name="action" value="order" class="btn btn-primary">Jetzt buchen</button>
					</form>
				</td>
			</tr>
		<% } %>
		</table>
	<% } else { %>
		<p class="alert alert-warning">Dieses Hotel bietet leider derzeit keine Services an.</p>
	<% } %>
	
	<h2>Meine gebuchten Services</h2>
		
	<% if (!sMap.isEmpty()) { %>
		<table class="table">
			<tr>
				<th>Name</th>
				<th>Beschreibung</th>
				<th>Preis</th>
				<th>Datum</th>
			</tr>
		
		<% for (Map.Entry<Date,Service> e : sMap.entrySet()) { %>
			<tr>
				<td><%= e.getValue().getName() %></td>
				<td><%= e.getValue().getBeschreibung() %></td>
				<td><%= e.getValue().getPreis() %></td>
				<td><%= e.getKey().toString() %></td>
			</tr>
		<% } %>
		</table>
	<% } else { %>
		<p class="alert alert-warning">Sie haben hier noch keine Services gebucht.</p>
	<% } %>
	</main>
</body>

</html>
<% } %>