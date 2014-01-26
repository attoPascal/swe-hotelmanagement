<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="hm.Hotel" %>
<%@ page import="hm.Zimmer" %>
<%@ page import="hm.Service" %>
<%@ page import="hm.Buchung" %>
<%@ page import="hm.users.Hotelier" %>
<%@ page import="hm.managers.ServiceManagement" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>


<%
	Object user = session.getAttribute("user");
	if (!(user instanceof Hotelier) || !((Hotelier) user).isCanManageServices()) {
		session.setAttribute("alert", "Zugriff verweigert. Bitte melden Sie sich als Hotelier mit den nötigen Rechten an, um auf diese Seite zuzugreifen.");
		session.setAttribute("redirect", "servicesverwalten.jsp");
		response.sendRedirect("login.jsp");
	}

	String hotelName = request.getParameter("hotel");
	Hotel hotel;
	List<Hotel> hList = ServiceManagement.getDAO().getHotelList();
	
	if (hotelName != null) {
		hotel = ServiceManagement.getDAO().getHotelByName(hotelName);
	} else {
		//wenn kein Hotel spezifiziert: erstes aus Liste wählen
		hotel = hList.get(0);
	}
	ArrayList<Zimmer> rooms = hotel.getZimmerList();
	ArrayList<Buchung> buchungen = new ArrayList<Buchung>();
	for (Zimmer zimmer : rooms){
		buchungen.addAll(zimmer.getBuchungen());
	}

	List<Service> sList = hotel.getServiceList();
	DateFormat df = new SimpleDateFormat("EEEE, d. MMMM yyyy");
%>

<!DOCTYPE html>

<html>

<%@ include file="inc/head.jsp" %>

<body>
	<%@ include file="inc/nav.jsp" %>
	
	<main class="container">
		<h1>Services verwalten</h1>
	
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
			<table class="service table">
				<thead>
					<tr>
						<th class="service">Name</th>
						<th class="preis">Preis</th>
						<th class="beschreibung">Beschreibung</th>
						<th class="button1"></th>
						<th class="button2"></th>
					</tr>
				</thead>
				
				<!-- edit/remove -->
				<tbody>
					<% for (Service s : sList) { %>
					<tr>
						<form action="ServiceServlet" method="get">
							<td class="name">
								<input type="hidden" name="serviceName"  value="<%= s.getName() %>">
								<input type="text" name="newName" value="<%= s.getName() %>" class="form-control">
							</td>
							<td class="preis">
								<input type="number" name="preis" value="<%= s.getPreis() %>" class="form-control">
							</td>
							<td class="beschreibung">
								<input type="text" name="beschreibung" value="<%= s.getBeschreibung() %>" class="form-control">
							</td>
							<td class="button1">
								<button type="submit" name="action" value="edit" class="btn btn-primary">
									<span class="glyphicon glyphicon-ok"></span>
								</button>
							</td>
							<td class="button2">
								<input type="hidden" name="hotelName" value="<%= hotel.getName() %>">
								<button type="submit" name="action" value="remove" class="btn btn-danger">
									<span class="glyphicon glyphicon-remove"></span>
								</button>
							</td>
						</form>
					</tr>
					<% } %>
				</tbody>
			</table>
			
			<!-- create -->
			<form action="ServiceServlet" method="get" accept-charset="UTF-8">
				<table class="service table">
					<tbody>
						<tr>
							<td class="name">
								<input type="hidden" name="hotelName" value="<%= hotel.getName() %>">
								<input type="text" name="serviceName" class="form-control" placeholder="Name">
							</td>
							<td class="preis">
								<input type="number" name="preis" value="" class="form-control" placeholder="Preis">
							</td>
							<td class="beschreibung">
								<input type="text" name="beschreibung" value="" class="form-control" placeholder="Beschreibung">
							</td>
							<td class="button1">
								<button type="submit" name="action" value="create" class="btn btn-success">
									<span class="glyphicon glyphicon-plus"></span>
								</button>
							</td>
							<td class="button2"></td>
						</tr>
					</tbody>
				</table>
			</form>

		<h1>Gebuchte Services</h1>
		
		<table class="service table">
			<thead>
				<tr>
					<th> Service </th>
					<th> Zimmernummer </th>
					<th> Preis </th>
					<th> Datum </th>

				</tr>
			</thead>
		<% for(Buchung b : buchungen){ 
				HashMap<Date, Service> services = b.getServices();
				Iterator it = services.entrySet().iterator();
				    while (it.hasNext()) {
				        Map.Entry pairs = (Map.Entry)it.next(); 
				        Service s = (Service)pairs.getValue(); %>
			 <tr>
			    <td> <%=s.getName() %> </td>
				<td> <%=b.getZimmernummer() %> </td>
				<td> <%=String.format("%.2f", s.getPreis()/100.0) %> &euro; </td>
				<td> <%= df.format(pairs.getKey()) %> </td>
			</tr>
		<% 
				        it.remove(); 
				    }
		%>
		
			
			
		<% } %>
		
		</table>
		
		</div>
	</main>
</body>

</html>