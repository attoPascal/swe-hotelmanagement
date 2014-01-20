<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Kategorie" %>
<%@ page import="hm.users.HotelGast" %>
<%@ page import="hm.dao.SerializedDAO" %>

<%
Object o = request.getSession().getAttribute("user");

if (o instanceof HotelGast) {
	HotelGast gast = (HotelGast) o;
	
	out.print("<h1>Meine Buchungen:</h1>");

	for (int i : gast.getBuchungsIDs()) {
		out.print(i + "<br>");
	}
} else {
	out.print("Kein Gast<br>");
}


%>