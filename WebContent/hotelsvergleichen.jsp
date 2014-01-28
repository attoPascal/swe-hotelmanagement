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
		session.setAttribute("redirect", "hotelsvergleichen.jsp");
		response.sendRedirect("login.jsp");
	}

	AnalyseServlet am = new AnalyseServlet();
	am.getManagement().instantiateDAO();
	ArrayList<Hotel> hList = am.getManagement().getDAO().getHotelList();

%>

<!DOCTYPE html>

<html>

<%@ include file="inc/head.jsp" %>

<body>
	<%@ include file="inc/nav.jsp" %>
	
	<main class="container">
		<h1>Hotels vergleichen</h1>
	
		<form>
			<div class="form-group">
    			 <label for="hotels">Verfügbare Hotels</label>
    			 <table name="hotels" class="kategorie table">
  				
  				<%for (Hotel h: hList) { %>
  					<tr>
   					 <td><%= h.getName()%></td>
   						
  					</tr>
  				<% } %>
				</table>
				
  			</div>
		</form>
		
		<div id="manage">	
			
			<form action="AnalyseServlet" method="post" accept-charset="UTF-8">
						
						<label for="hotel">Hotel 1</label>
						<select name="hotel">
							<%for (Hotel h: hList) { %>
 								 <option value="<%= h.getName()%>"><%= h.getName()%></option>
 							<% } %>		
						</select> 
						<label for="hotel2">Hotel 2</label>
						<select name="hotel2">
							<%for (Hotel h: hList) { %>
 								 <option value="<%= h.getName()%>"><%= h.getName()%></option>
 							<% } %>		
						</select> 
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