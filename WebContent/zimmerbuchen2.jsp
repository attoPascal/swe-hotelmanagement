<%@page import="hm.Kategorie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hm.Hotel"%>
<%@page import="hm.dao.SerializedDAO"%>
<%@page import="hm.dao.DAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Hotelmanagement</title>
</head>

<body>			
	<%
		DAO dao = new SerializedDAO("data.ser");
		
		String hotelName = request.getParameter("hotel");
		String katName   = request.getParameter("kategorie");
	   	
	   	Hotel hotel = dao.getHotelByName(hotelName);		
		Kategorie kategorie = hotel.getKategorie(katName);
	%>
	
	<form action="BuchungsManagement" method="get">	
		<input type="text" name="hotel" value="<%= hotelName %>" readonly="readonly">
		<input type="text" name="kategorie" value="<%= katName %>" readonly="readonly"><br>
	    <input type="text" name="day" title="Tag" style="color:#888;" value="Tag" onfocus="inputFocus(this)" onblur="inputBlur(this)"> 
		<select name="months" size="1"  tabindex="1">
			<option value="1">Jänner</option>
			<option value="2">Feber</option>
			<option value="3">März</option>
			<option value="4">April</option>
			<option value="5">Mai</option>
			<option value="6">Juni</option>
			<option value="7">Juli</option>
			<option value="8">August</option>
			<option value="9">September</option>
			<option value="10">Oktober</option>
			<option value="11">November</option>
			<option value="12">Dezember</option>
		</select>
	    <input type="text" name="year" title="Jahr" style="color:#888;" value="Jahr" onfocus="inputFocus(this)" onblur="inputBlur(this)" />      
	    <input type="submit" name="submit" value="submit" tabindex="2">						
	</form>	
	
	
</body>

</html>