<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Zimmer" %>
<%@ page import="hm.Buchung" %>
<%@ page import="hm.Kategorie" %>
<%@ page import="hm.users.Hotelier" %>
<%@ page import="hm.servlets.BuchungsVerwaltungsServlet" %>
<%@ page import="hm.managers.BuchungsManagement" %>
<%@ page import="hm.dao.DAO" %>

<%
	//Rechte überprüfen:
	//Hotelier mit canManageRooms
	Object user = session.getAttribute("user");
	if (!(user instanceof Hotelier) || !((Hotelier) user).isCanManageBookings()) {
		session.setAttribute("alert", "Zugriff verweigert. Bitte melden Sie sich als Hotelier mit den nötigen Rechten an, um auf diese Seite zuzugreifen.");
		session.setAttribute("redirect", "buchungenverwalten.jsp");
		response.sendRedirect("login.jsp");
	}

	BuchungsManagement bm = new BuchungsManagement();
	bm.instantiateDAO("data.ser");
	ArrayList<Hotel> hList = bm.getDAO().getHotelList();
	
	String hotelName = request.getParameter("hotel");
	Hotel hotel = bm.getDAO().getHotelByName(hotelName);
	
	if (hotelName != null) {
		hotel = bm.getDAO().getHotelByName(hotelName);
		
	} else {
		//wenn kein Hotel spezifiziert: erstes aus Liste wählen
		hotel = hList.get(0);
	}
	
	ArrayList<Zimmer> zList = hotel.getZimmerList();
	ArrayList<Kategorie> kList = hotel.getKategorien();
	
%>

<!DOCTYPE html>
<html>

<%@ include file="inc/head.jsp" %>

<body>
	<%@ include file="inc/nav.jsp" %>
	
	<main class="container">
		<h1>Buchungen verwalten</h1>
		
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
			<table>
				<tr>
					<th>ID</th>
					<th>Zimmer</th>
					<th>Anfang</th>
					<th>Ende</th>
				</tr>
				<tr>
			
				<% for (Zimmer z : zList) { %>
					<% for (Buchung b : z.getBuchungen()) { %>
						<tr>
							<td><%= b.getId() %></td>
							<td><%= b.getZimmernummer() %></td>
							<td><%= b.getAufenthalt().getAnfang() %></td>
							<td><%= b.getAufenthalt().getEnde() %></td>
						</tr>
					<% } %>
				<% } %>
			</table>
			<!--<table class="zimmer table">
				<tr>
					<th class="zimmer">Zimmer</th>
					<th class="kategorie">Kategorie</th>
					<th class="button"></th>
				</tr>
		
				<% for (Zimmer z : zList) { %>
				<tr>
					<td class="zimmer">
						<input type="text" class="form-control" value="<%= z.getNummer() %>" readonly>
					</td>
					<td class="kategorie">
						<select class="set-kategorie form-control" data-zimmer="<%= z.getNummer() %>">
							<% for (Kategorie k : kList) {
								String selected = (k.hasZimmer(z.getNummer())) ? " selected" : "";
							%>
							<option value="<%= k.getName() %>"<%= selected %>><%= k.getName() %></option>
							<% } %>
						</select>
					</td>
					<td class="button">
						<form action="ZimmerServlet" method="get">
							<input type="hidden" name="action" value="delete">
							<input type="hidden" name="hotel" value="<%= hotel.getName() %>">
							<input type="hidden" name="zimmer" value="<%= z.getNummer() %>">
							
							<button type="submit" class="btn btn-danger">
								<span class="glyphicon glyphicon-remove"></span>
							</button>
						</form>
					</td>
				</tr>
				<% } %>
			</table>
			
			<form action="ZimmerServlet" method="get">
				<table class="zimmer table">
					<tr>
						<td class="zimmer">
							<input type="hidden" name="action" value="create">
							<input type="hidden" name="hotel" value="<%= hotel.getName() %>">
							<input type="text" name="zimmer" class="form-control">
						</td>
						<td class="kategorie">
							<select name="kategorie" class="form-control">
								<option value="" selected disabled>Bitte auswählen:</option>
								<% for (Kategorie k : kList) { %>
								<option value="<%= k.getName() %>"><%= k.getName() %></option>
								<% } %>
							</select>
						</td>
						<td class="button">
							<button type="submit" class="btn btn-success">
								<span class="glyphicon glyphicon-plus"></span>
							</button>
							<button type="submit" class="aufenthalt">
								<span class="glyphicon glyphicon-plus"></span>
							</button>
						</td>
					</tr>
				</table>
			</form>-->
		</div>
	</main>
</body>

</html>