<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Zimmer" %>
<%@ page import="hm.Kategorie" %>
<%@ page import="hm.servlets.ZimmerServlet" %>
<%@ page import="hm.dao.DAO" %>

<%
	ZimmerServlet zm = new ZimmerServlet();
	zm.getManagement().instantiateDAO();
	ArrayList<Hotel> hList = zm.getManagement().getDAO().getHotelList();
	
	String hotelName = request.getParameter("hotel");
	Hotel hotel = null;
	
	if (hotelName != null) {
		hotel = zm.getManagement().getDAO().getHotelByName(hotelName);
		
	} else {
		hotel = new Hotel();
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
		<h1>Zimmer verwalten</h1>
		
		<form>
			<div class="form-group">
    			<label for="setHotel">Hotel auswählen:</label>
    			<select size="1" name="hotel" class="set-hotel form-control input-lg" id="setHotel">
    				<% for (Hotel h : hList) { 
					String selected = (h.getName().equals(hotel.getName())) ? "selected=\"selected\"" : "";
					%>
					<option value="<%= h.getName() %>"<%= selected %>><%= h.getName() %></option>
					<% } %>
				</select>
  			</div>
  			
  			<textarea class="form-control" rows="1" id="response"></textarea>
		</form>
		
		<div id="managerooms">
			<table class="table">
				<tr>
					<th>Zimmer</th>
					<th>Kategorie</th>
					<th></th>
				</tr>
		
				<% for (Zimmer z : zList) { %>
				<tr>
					<td>
						<input type="text" class="form-control" value="<%= z.getNummer() %>" size="4" readonly="readonly">
					</td>
					<td>
						<select size="1" class="set-kategorie form-control" data-zimmer="<%= z.getNummer() %>">
							<% for (Kategorie k : kList) {
								String selected = (k.hasZimmer(z.getNummer())) ? "selected=\"selected\"" : "";
							%>
							<option value="<%= k.getName() %>"<%= selected %>><%= k.getName() %></option>
							<% } %>
						</select>
					</td>
					<td>
						<form action="ZimmerServlet" method="get">
							<input type="hidden" name="action" value="delete">
							<input type="hidden" name="hotel" value="<%= hotel.getName() %>">
							<input type="hidden" name="zimmer" value="<%= z.getNummer() %>">
							<input type="submit" class="form-control btn btn-primary" value="-">
						</form>
					</td>
				</tr>
				<% } %>
			</table>
			
			
			<form action="ZimmerServlet" method="get">
				<table>
					<tr>
						<td>
							<input type="hidden" name="action" value="create">
							<input type="hidden" name="hotel" value="<%= hotel.getName() %>">
							<input type="text" name="zimmer" size="4">
						</td>
						<td>
							<select name="kategorie" size="1">
								<option>- Kategorie -</option>
								<% for (Kategorie k : kList) { %>
								<option value="<%= k.getName() %>"><%= k.getName() %></option>
								<% } %>
							</select>
						</td>
						<td>
							<input type="submit" value="+">
						</td>
					</tr>
				</table>
			</form>
		</div>
	</main>
	
	<!-- <p>Hotel ausw&auml;hlen:
			<select size="1" name="hotel" class="set-hotel">
				<% for (Hotel h : hList) { 
					String selected = (h.getName().equals(hotel.getName())) ? "selected=\"selected\"" : "";
				%>
				<option value="<%= h.getName() %>"<%= selected %>><%= h.getName() %></option>
				<% } %>
			</select>
		</p>
	
		<textarea rows="1" cols="20" id="response"></textarea>
		
		<div id="managerooms">
			<table>
				<tr>
					<th>Zimmer</th>
					<th>Kategorie</th>
					<th></th>
				</tr>
		
				<% for (Zimmer z : zList) { %>
				<tr>
					<td>
						<input type="text" value="<%= z.getNummer() %>" size="4" readonly="readonly">
					</td>
					<td>
						<select size="1" class="set-kategorie" data-zimmer="<%= z.getNummer() %>">
							<% for (Kategorie k : kList) {
								String selected = (k.hasZimmer(z.getNummer())) ? "selected=\"selected\"" : "";
							%>
							<option value="<%= k.getName() %>"<%= selected %>><%= k.getName() %></option>
							<% } %>
		
						</select>
					</td>
					<td>
						<form action="ZimmerServlet" method="get">
							<input type="hidden" name="action" value="delete">
							<input type="hidden" name="hotel" value="<%= hotel.getName() %>">
							<input type="hidden" name="zimmer" value="<%= z.getNummer() %>">
							<input type="submit" value="-">
						</form>
					</td>
				</tr>
				<% } %>
			</table>
			
			
			<form action="ZimmerServlet" method="get">
				<table>
					<tr>
						<td>
							<input type="hidden" name="action" value="create">
							<input type="hidden" name="hotel" value="<%= hotel.getName() %>">
							<input type="text" name="zimmer" size="4">
						</td>
						<td>
							<select name="kategorie" size="1">
								<option>- Kategorie -</option>
								<% for (Kategorie k : kList) { %>
								<option value="<%= k.getName() %>"><%= k.getName() %></option>
								<% } %>
							</select>
						</td>
						<td>
							<input type="submit" value="+">
						</td>
					</tr>
				</table>
			</form>
		</div> -->
</body>

</html>