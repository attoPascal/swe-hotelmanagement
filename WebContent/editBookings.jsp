<%@page import="hm.Kategorie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hm.Hotel"%>
<%@page import="hm.Buchung"%>
<%@page import="hm.users.Hotelier"%>
<%@page import="hm.dao.SerializedDAO"%>
<%@page import="hm.dao.DAO"%>
<%@page import="java.util.Date" %>
<%@page import="java.util.Calendar" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String hotelName = request.getParameter("hotelName");
	String id = request.getParameter("id");

	//Rechte überprüfen:
	//Hotelgast
	Object user = session.getAttribute("user");
	if (!(user instanceof Hotelier)) {
		session.setAttribute("alert", "Zugriff verweigert. Bitte melden Sie sich als HotelGast an, um auf diese Seite zuzugreifen.");
		//session.setAttribute("redirect", "zimmerbuchen.jsp?hotel=" + hotelName + "&kategorie=" + katName);
		response.sendRedirect("login.jsp");
	}
%>

<!DOCTYPE html>
<html>

<%@ include file="inc/head.jsp" %>

<body>
	<%@ include file="inc/nav.jsp" %>
	
	<main class="container">
		<h1>Buchung editieren</h1>		
		<%
			DAO dao = SerializedDAO.getInstance();
		   	
		   	Hotel hotel = dao.getHotelByName(hotelName);	
			Buchung buchung = hotel.getBuchungByID(Integer.parseInt(id));
			
			Date date = buchung.getAufenthalt().getAnfang();
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(date);
			
			int year = cal.get(Calendar.YEAR);
			int month = cal.get(Calendar.MONTH);
			int day = cal.get(Calendar.DAY_OF_MONTH);
		%>
		
		<form action="BuchungsVerwaltungsServlet" method="get" class="form-horizontal">
			<div class="form-group">
				<label for="hotelInput" class="col-sm-2 control-label">Hotel:</label>
				<div class="col-sm-10">
					<input type="text" name="hotel" value="<%= hotelName %>" class="form-control" id="hotelInput" readonly>
				</div>
			</div>
			
			<div class="form-group">
				<label for="IDInput" class="col-sm-2 control-label">Buchungs-ID:</label>
				<div class="col-sm-10">
					<input type="text" name="id" value="<%= id %>" class="form-control" id="IDInput" readonly>
				</div>
			</div>
			<div class="form-group">
				<label for="tagInput" class="col-sm-2 control-label">Beginndatum:</label>
		    	<div class="col-sm-2">
		    		<input type="number" name="day" value="<%= day %>" placeholder="Tag" class="form-control" id="tagInput"> 
				</div>
				<div class="col-sm-4">
					<select name="months" class="form-control">
						<option value="01">Jänner</option>
						<option value="02">Februar</option>
						<option value="03">März</option>
						<option value="04">April</option>
						<option value="05">Mai</option>
						<option value="06">Juni</option>
						<option value="07">Juli</option>
						<option value="08">August</option>
						<option value="09">September</option>
						<option value="10">Oktober</option>
						<option value="11">November</option>
						<option value="12">Dezember</option>
					</select>
				</div>
				<div class="col-sm-4">
		    		<input type="number" name="year" value="<%= year %>" placeholder="Jahr" class="form-control">
		    	</div>
		    </div>
		    <div class="form-group">    
		    	<label for="durationInput" class="col-sm-2 control-label">Aufenthaltsdauer:</label>
		    	<div class="col-sm-2">
		    		<input type="number" name="duration" value="<%= buchung.getAufenthalt().getDays() %>" placeholder="Tage" class="form-control" id="durationInput">
		    	</div>
		    </div>
		    <div class="form-group">
    			<div class="col-sm-offset-2 col-sm-10">  
		    		<input type="submit" name="submit" value="Fertig" class="btn btn-primary">
		    	</div>
		    </div>
		</form>
	</main>
</body>

</html>