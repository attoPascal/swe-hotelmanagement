<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Kategorie" %>
<%@ page import="hm.dao.DAO" %>
<%@ page import="hm.dao.SerializedDAO" %>

<%	
	DAO dao = new SerializedDAO("data.ser");
	ArrayList<Hotel> hList = dao.getHotelList();
%>

<!DOCTYPE html>

<html>
<head>
	<title>Zimmer buchen</title>
	<%@ include file="inc/head.jsp" %>
</head>

<body>
	<%@ include file="inc/nav.jsp" %>
  
	<main class="container">
		<h1>Zimmer buchen</h1>
		
		<p>Sehr geehrter Kunde!</p>
		<p>Herzlich Willkommen bei der Hotelgruppe <i>Little Sharky Fish</i>!<br>
		Im Folgenden finden Sie alle Kategorien unserer Hotels, die f&uuml;r Sie zur
		Buchung bereit stehen.</p>
		
		<% for (Hotel h : hList) { %>
			<h2>Hotel '<%= h.getName() %>'</h2>
			
			<table class="table hotel">
				<thead>
					<tr>
						<th class="kategorie">Kategorie</th>
						<th class="preis">Preis/Nacht</th>
						<th class="ausstattung">Ausstattung</th>
						<th class="buchen"></th>
					</tr>
				</thead>
				
				<tbody>
				<%
					ArrayList<Kategorie> kList = h.getKategorien();
					for (Kategorie k : kList) {
				%>
					<tr>
						<th class="kategorie"><%= k.getName() %></th>
						<td class="preis"><%= String.format("%.2f", k.getPreis()/100.0) %> &euro;</td>
						<td class="ausstattung"><%= k.getAusstattung() %></td>
						<td class="buchen">
							<form action="zimmerbuchen.jsp" method="get">
								<input type="hidden" name="hotel" value="<%= h.getName() %>">
								<input type="hidden" name="kategorie" value="<%= k.getName() %>">
								<input type="submit" value="Jetzt buchen" class="btn btn-primary">
							</form>
						</td>
					</tr>
				<% } %>
				</tbody>
			</table>
	
		<% } %>
	</main>
</body>

</html>