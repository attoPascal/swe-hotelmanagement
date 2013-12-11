<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
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
	<title>Alle Kategorien</title>
	<meta charset="US-ASCII">
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
	</script>
</head>

<body>
	<h1>Alle Kategorien</h1>
	
	<p>Sehr geehrter Kunde!</p>
	<p>Herzlich Willkommen bei der Hotelgruppe <i>Little Sharky Fish</i>!<br>
	Im Folgenden finden Sie alle Kategorien unserer Hotels, die f&uuml;r Sie zur
	Buchung bereit stehen.</p>
	
	<% for (Hotel h : hList) { %>
		<h2>Hotel '<%= h.getName() %>'</h2>
		
		<table>
			<tr>
				<th>Kategorie</th>
				<th>Preis/Nacht</th>
				<th>Ausstattung</th>
				<td></td>
			</tr>
		<%
			ArrayList<Kategorie> kList = h.getKategorien();
			for (Kategorie k : kList) {
		%>
			<tr>
				<th><%= k.getName() %></th>
				<td><%= k.getPreis() %></td>
				<td><%= k.getAusstattung() %></td>
				<td>
					<form action="zimmerbuchen2.jsp" method="get">
						<input type="hidden" name="hotel" value="<%= h.getName() %>">
						<input type="hidden" name="kategorie" value="<%= k.getName() %>">
						<input type="submit" value="Jetzt buchen">
					</form>
				</td>
			</tr>
				
		<% } %>
		</table>

	<% } %>
	
</body>

</html>