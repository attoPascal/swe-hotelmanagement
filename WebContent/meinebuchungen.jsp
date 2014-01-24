<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Kategorie" %>
<%@ page import="hm.Buchung" %>
<%@ page import="hm.users.HotelGast" %>
<%@ page import="hm.dao.DAO" %>
<%@ page import="hm.dao.SerializedDAO" %>

<%
Object user = session.getAttribute("user");
if (!(user instanceof HotelGast)) {
	session.setAttribute("alert", "Zugriff verweigert. Bitte melden Sie sich als Gast an, um auf diese Seite zuzugreifen.");
	session.setAttribute("redirect", "meinebuchungen.jsp");
	response.sendRedirect("login.jsp");
} else {
	ArrayList<Hotel> hList = SerializedDAO.getInstance().getHotelList();
%>
	
<!DOCTYPE html>

<html>

<%@ include file="inc/head.jsp" %>

<body>
	<%@ include file="inc/nav.jsp" %>
	
	<main class="container">
		<h1>Meine Buchungen</h1>
	
	<% for (Hotel h : hList) { %>
			<h2><%= h.getName() %></h2>
			<table class="table">
				<tr>
					<th>ID</th>
					<th>Zimmer</th>
					<th>Anfang</th>
					<th>Ende</th>
				</tr>
				
		<% for (Buchung b : h.getBuchungsList((HotelGast) user)) { %>
				<tr>
					<td><%= b.getId() %></td>
					<td><%= b.getZimmernummer() %></td>
					<td><%= b.getAufenthalt().getAnfang() %></td>
					<td><%= b.getAufenthalt().getEnde() %></td>
				</tr>
		<% } %>
			</table>
	<% } %>
<% } %>