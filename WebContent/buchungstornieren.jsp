<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
		session.setAttribute("redirect", "zimmerstornieren.jsp");
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
			HotelGast gast = (HotelGast)user;
		%>
		
		<table class="table table-striped">
		
		<%
			for(Hotel hotel : dao.getHotelList()){
				
		%>
		
		<tr>
			<th><%=hotel.getName() %></th>
		</tr>
		<tr>
			<th> ID </th>
			<th> Zimmernummer </th>
			<th> Anfang </th>
			<th> Ende </th>
		</tr>
		<%
				List<Integer> List = gast.getBuchungsIDs();
				for (Integer id : List) {
					int i = id.intValue();
					for(Buchung buchung : hotel.getBuchungsList(gast))
						if (buchung.getId() == i){
		%>
		<tr>
			<td><%=buchung.getId() %></td>
			<td><%=buchung.getZimmernummer() %></td>
			<td><%=buchung.getAufenthalt().getAnfang()%></td>
			<td><%=buchung.getAufenthalt().getEnde()%></td>
			
		</tr>
		<% }
		} 
		}
		%>	
		</table>		
		
		<form action="Buchenstein" method="get" class="form-horizontal">
			<div class="form-group">
				<label for="idInput" class="col-sm-2 control-label">ID:</label>
				<div class="col-sm-10">
					<input type="text" name="id" class="form-control" id="idInput">
				</div>
			</div>
			<div class="form-group">
				<label for="hotelInput" class="col-sm-2 control-label">Hotel:</label>
				<div class="col-sm-10">
					<input type="text" name="hotel" class="form-control" id="hotelInput">
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