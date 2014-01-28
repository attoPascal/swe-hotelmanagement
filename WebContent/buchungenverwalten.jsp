<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
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
	bm.instantiateDAO();
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
	DateFormat df = new SimpleDateFormat("EEEE, d. MMMM yyyy");
%>

<!DOCTYPE html>
<html>

<%@ include file="inc/head.jsp" %>

<body>
	<%@ include file="inc/nav.jsp" %>
	
	<main class="container">
		<h1>Buchungen verwalten</h1>
		
		<form>
			<table>
				<tr>
					<td style="width:100%">
		    			<label for="setHotelNoAjax">Hotel&nbsp;auswählen:</label>
		    			<select name="hotel" class="set-hotel-no-ajax form-control" id="setHotelNoAjax">
		    				<% for (Hotel h : hList) { 
							String selected = (h.getName().equals(hotel.getName())) ? "selected=\"selected\"" : "";
							%>
							<option value="<%= h.getName() %>"<%= selected %>><%= h.getName() %></option>
							<% } %>
						</select>
					</td>
					<td style="vertical-align:bottom">
						<button type="submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-ok"></span>
						</button>
					</td>
				</tr>
  			</table>
		</form>

		<div>&nbsp;</div>
		
		<div id="manage">
			
		<table>
			<tr>
				<th>ID</th>
				<th>Zimmer</th>
				<th>Anfang</th>
				<th>Ende</th>
				<th>Tage</th>
				<th>Kosten</th>
				<th>Kategorie</th>
				<th></th>
				<th></th>
			</tr>
			
			
				<% for (Zimmer z : zList) { %>
					<% for (Buchung b : z.getBuchungen()) { %>
						<form action="editBookings.jsp">
							<tr>
								<td><input type="hidden" name="id" value="<%= b.getId() %>"><%= b.getId() %></td>
								<td><%= b.getZimmernummer() %></td>
								<td><%= df.format(b.getAufenthalt().getAnfang()) %></td>
								<td><%= df.format(b.getAufenthalt().getEnde()) %></td>
								<td><%= b.getAufenthalt().getDays() %></td>
								<td><%= b.getKosten() %></td>
								<td><%= b.getKategorie().getName() %> </td>
								<td><input type="hidden" name="hotelName" value="<%= hotel.getName() %>"></td>
								<td><input type="submit" value="edit"></td>
							</tr>	
						</form>	
					<% } %>
				<% } %>
		</table>
	</main>
</body>
</html>
		
		
								
								
