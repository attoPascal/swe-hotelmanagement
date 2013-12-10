<%@page import="hm.Kategorie"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hm.Hotel"%>
<%@page import="hm.dao.SerializedDAO"%>
<%@page import="hm.dao.DAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title> Hotelmanagement  </title>
	</head>	
	<body>		
			
		<%
		
		DAO dao = new SerializedDAO("data.ser");
    	
    	Hotel hotel = dao.getHotelByName("CrazySharkyFish");		
		ArrayList<Kategorie> klist = hotel.getKategorien();
		%>
		
		<%= hotel.toString() %>
		

						
		<form action="BuchungsManagement">			
			 <h2>Bitte suchen sie eine Kategorien aus</h2> <br>

				<select name= "select" size="1" >
					<% for (Kategorie k : klist) { %>
					<option value="<%= k.getName() %>"><%= k.getName() %></option>
					<% } %>

				</select>
				<br>
			<input type="text" name="day">
			
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
      
      <input type="text" name="year">
            


			<input type="submit" name= "submit" value="submit" tabindex="2">						
		</form>	
		
		<form action="BuchungsManagement">			
			 Please enter a Hotel <br>
			<input type="text" name="name" size="20px">
			<input type="submit" value="submit">						
		</form>	
		
	</body>	
</html>