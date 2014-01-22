<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Kategorie" %>
<%@ page import="hm.users.Analyst" %>
<%@ page import="hm.servlets.AnalyseServlet" %>
<%@ page import="hm.dao.DAO" %>

<%
	//Rechte überprüfen:
	//Hotelier mit canManageCategories
	Object user = session.getAttribute("user");
	if (!(user instanceof Analyst) || ((Analyst) user).hasNoRights()) {
		session.setAttribute("alert", "Zugriff verweigert. Bitte melden Sie sich als Analyst mit den nötigen Rechten an, um auf diese Seite zuzugreifen.");
		session.setAttribute("redirect", "statistikerstellen.jsp");
		response.sendRedirect("login.jsp");
	}

	AnalyseServlet am = new AnalyseServlet();
	am.getManagement().instantiateDAO();
	ArrayList<Hotel> hList = am.getManagement().getDAO().getHotelList();
	
	String hotelName = request.getParameter("hotel");
	Hotel hotel = null;
	
	if (hotelName != null) {
		hotel = am.getManagement().getDAO().getHotelByName(hotelName);
		
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
		<h1>Statistik erstellen</h1>
	
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
			
			<form action="AnalyseServlet" method="post" accept-charset="UTF-8">
						<input type="hidden" name="hotel" value="<%= hotel.getName() %>">
						<label for="anfang">Anfang</label>
						<input type="date" name="anfang" value="" placeholder="yyyy-mm-dd">
						<label for="tage">Tage</label>
						<input type="number" name="tage">	
						<button type="submit" name="action" value="create" class="btn btn-success">
						
						<span class="glyphicon glyphicon-search"></span>
								</button>

			</form>
		</div>
	</main>
</body>

</html>