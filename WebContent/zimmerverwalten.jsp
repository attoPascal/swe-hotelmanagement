<%@ page language="java" contentType="text/html; charset=US-ASCII" pageEncoding="US-ASCII" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Zimmer" %>
<%@ page import="hm.Kategorie" %>
<%@ page import="hm.servlets.ZimmerManagement" %>
<%@ page import="hm.dao.DAO" %>

<%
	ZimmerManagement zm = new ZimmerManagement();
	Hotel hotel = zm.getDAO().getHotelByName("CrazySharkyFish");
	ArrayList<Zimmer> zList = hotel.getZimmerList();
	ArrayList<Kategorie> kList = hotel.getKategorien();
%>

<!DOCTYPE html>

<html>
<head>
	<title>Zimmer verwalten</title>
	<meta charset="US-ASCII">
	<script src="js/jquery.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("select").change(function() {
				$("#response").load(
					"ZimmerManagement?action=set&zimmer=" + $(this).attr("data-zimmer") +
					"&kategorie=" + $(this).val() +
					"&hotel=BigSharkyFish"
				);
			});
		});
	</script>
</head>

<body>

	<h1>Zimmer verwalten</h1>

	<textarea rows="6" cols="50" id="response"></textarea>

	<table>
		<tr>
			<th>Zimmer</th>
			<th>Kategorie</th>
			<th></th>
		</tr>

		<% for (Zimmer z : zList) { %>
		<tr>
			<td><%= z.getNummer() %></td>
			<td>
				<select size="1" data-zimmer="<%= z.getNummer() %>">
					<% for (Kategorie k : kList) { %>
					<option value="<%= k.getName() %>"><%= k.getName() %></option>
					<% } %>

				</select>
			</td>
			<td><input type="button" value="-"></td>
		</tr>
		<% } %>

	</table>

</body>

</html>