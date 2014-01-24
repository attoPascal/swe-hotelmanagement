<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Kategorie" %>
<%@ page import="hm.Buchung" %>
<%@ page import="hm.users.HotelGast" %>
<%@ page import="hm.dao.DAO" %>
<%@ page import="hm.dao.SerializedDAO" %>

<%
Object user = session.getAttribute("user");
if (!(user instanceof HotelGast)) {
	session.setAttribute("alert", "Zugriff verweigert. Bitte melden Sie sich als Gast an, um auf diese Seite zuzugreifen.");
	session.setAttribute("redirect", "meinebuchungen.jsp");
	response.sendRedirect("login.jsp");
} else {
	List<Hotel> hList = SerializedDAO.getInstance().getHotelList();
%>
	
<!DOCTYPE html>

<html>

<%@ include file="inc/head.jsp" %>

<body>
	<%@ include file="inc/nav.jsp" %>
	
	<main class="container">
		<h1>Meine Buchungen</h1>
	
	<%
	for (Hotel h : hList) { 
		List<Buchung> bList = h.getBuchungsList((HotelGast) user);
		if (!bList.isEmpty()) {
	%>
	
			<h2><%= h.getName() %></h2>
			<table class="table">
				<tr>
					<th>ID</th>
					<th>Zimmer</th>
					<th>Anfang</th>
					<th>Ende</th>
					<th>&nbsp;</th>
				</tr>
				
			<% for (Buchung b : bList) { %>
				<tr>
					<td><%= b.getId() %></td>
					<td><%= b.getZimmernummer() %></td>
					<td><%= b.getAufenthalt().getAnfang() %></td>
					<td><%= b.getAufenthalt().getEnde() %></td>
					<td>
						<form action="servicebuchen.jsp" method="get">
							<input type="hidden" name="hotelName" value="<%= h.getName() %>">
							<input type="hidden" name="buchungsID" value="<%= b.getId() %>">
							<input type="submit" value="Service buchen" class="btn btn-primary">
						</form>
					</td>
				</tr>
			<% } %>
			</table>
		<% } %>
	<% } %>
	</main>
</body>

</html>
<% } %>