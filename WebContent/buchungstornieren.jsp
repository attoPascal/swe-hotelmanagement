<%@page import="java.util.ArrayList"%>
<%@page import="hm.Hotel"%>
<%@page import="hm.Zimmer"%>
<%@page import="hm.Buchung"%>
<%@page import="hm.users.HotelGast"%>
<%@page import="hm.dao.SerializedDAO"%>
<%@page import="hm.dao.DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	//Rechte überprüfen:
	//Hotelgast
	Object user = session.getAttribute("user");
	if (!(user instanceof HotelGast)) {
		session.setAttribute("alert", "Zugriff verweigert. Bitte melden Sie sich als HotelGast an, um auf diese Seite zuzugreifen.");
		session.setAttribute("redirect", "zimmerstornieren.jsp?id=" + id + "&nummer=" + nummer + "&hotel=" + hotelName);
		response.sendRedirect("login.jsp");
	}
%>

<!DOCTYPE html>
<html>

<%@ include file="inc/head.jsp" %>

<body>
	<%@ include file="inc/nav.jsp" %>
	
	<main class="container">
		<h1>Zimmer stornieren</h1>		
		<%
			DAO dao = SerializedDAO.getInstance();	   	
		   	Hotel hotel = dao.getHotelByName(hotelName);					
		%>
		
		<tbody>
		<%
			ArrayList<Integer> List = HotelGast.getBuchungsIDs();
				for (id id : List) {
		%>
		<tr>
			<th class="buchung #:"><%= id %></th>
		</tr>
		<% } %>			
		</tbody>
		
		<form action="BuchungsStornierungsServlet" method="get" class="form-horizontal">
			<div class="form-group">
				<label for="idInput" class="col-sm-2 control-label">ID:</label>
				<div class="col-sm-10">
					<input type="text" name="id" value="<%= id %>" class="form-control" id="idInput" readonly>
				</div>
			</div>
			<div class="form-group">
				<label for="nummerInput" class="col-sm-2 control-label">Zimmer-Nummer:</label>
				<div class="col-sm-10">
					<input type="text" name="nummer" value="<%= id %>" class="form-control" nummer="nummerInput" readonly>
				</div>
			</div>
			<div class="form-group">
				<label for="hotelInput" class="col-sm-2 control-label">Hotel:</label>
				<div class="col-sm-10">
					<input type="text" name="hotel" value="<%= hotelName %>" class="form-control" id="hotelInput" readonly>
				</div>
			</div>
		    <div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">  
		    		<input type="submit" name="submit" value="Buchung stornieren" class="btn btn-primary">
		    	</div>
		    </div>
		</form>
	</main>
</body>

</html>